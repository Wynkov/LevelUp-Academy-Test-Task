package com.levelup.task.backend;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BackendAPI {
	private int amexStart1 = 34; // Starting digits (option 1) of an Amex card
	private int amexStart2 = 37; // Starting digits (option 2) of an Amex card
	private int numMin = 16; // Minimum number of digits of a card number
	private int numMax = 19; // Maximum number of digits of a card number
	private int cvvDigits; // Number of digits of the CVV code

	/** 
	 * Method for verifying the credit card information
	 * 
	 * Returns false if verification failed, true otherwise 
	 * 
	 * **/
	@ResponseBody
	@GetMapping("/verify")
	public APIResult verifyInformation(@RequestParam("type") String card_type, @RequestParam("num") String card_number, @RequestParam("date") String card_date, @RequestParam("cvv") String card_cvv) {
		APIResult result = new APIResult();

		// Set number of digits according to card type
		switch(card_type) {
			case "amex":
				cvvDigits = 4;

				break;
			case "master":
			case "visa":
				cvvDigits = 3;

				break;
			default:
				result.setError(APIErrors.INVALID_CARD_TYPE);

				break;
		}

		String date[] = card_date.split("/");

		int cNum, cMonth, cYear, cCvv;

		try {
			// Check if inputs are the correct types of variables
			cNum = Integer.parseInt(card_number);
			cCvv = Integer.parseInt(card_cvv);

			cMonth = Integer.parseInt(date[0]);
			cYear = Integer.parseInt(date[1]);

			if(cNum < 0 || cMonth < 0 || cYear < 0 || cCvv < 0) result.setError(APIErrors.INVALID_INPUT);

			// Check expiration date
			int month = LocalDate.now().getMonthValue();
			int year = LocalDate.now().getYear();

			if(cYear >= year) {
				if(cMonth < month) result.setError(APIErrors.EXPIRED_CARD);
			} else result.setError(APIErrors.EXPIRED_CARD);
		} catch(NumberFormatException e) {
			result.setError(APIErrors.INVALID_INPUT);
		}

		return result;
	}
}