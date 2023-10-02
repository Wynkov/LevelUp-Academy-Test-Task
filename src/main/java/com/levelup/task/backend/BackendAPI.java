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
	private String amex1 = "34"; // Starting digits (option 1) of an Amex card
	private String amex2 = "37"; // Starting digits (option 2) of an Amex card

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
		String date[] = card_date.split("/");

		int cMonth, cYear, cCvv;
		long cNum;

		try {
			// Check if inputs are the correct types of variables
			cNum = Long.parseLong(card_number);
			cCvv = Integer.parseInt(card_cvv);

			if(date.length != 2) {
				result.setError(APIErrors.INVALID_DATE);
			} else {
				cMonth = Integer.parseInt(date[0]);
				cYear = Integer.parseInt(date[1]);

				// Check if expiration date is within limit
				if(cNum < 0 || cMonth < 1 || cYear < 0 || cCvv < 0 || cMonth > 12 || cYear > 99) result.setError(APIErrors.INVALID_DATE);

				// Check if expiration date is after present time
				int month = LocalDate.now().getMonthValue();
				int year = LocalDate.now().getYear() - 2000;

				if(cYear >= year) {
					if(cYear == year && cMonth < month) result.setError(APIErrors.EXPIRED_CARD);
				} else result.setError(APIErrors.EXPIRED_CARD);
			}
		} catch(NumberFormatException e) {
			result.setError(APIErrors.INVALID_INPUT);
		}

		// Set number of CVV digits according to card type
		setCvvDigits(card_type, card_number, result);

		// Check Amex starting digits
		if(card_type.equals("amex")) if(!card_number.startsWith(amex1) && !card_number.startsWith(amex2)) result.setError(APIErrors.INVALID_CARD_NUMBER);

		// Check card number length
		if(card_number.length() < numMin || card_number.length() > numMax) result.setError(APIErrors.INVALID_CARD_NUMBER);

		// Check card CVV number length
		if(card_cvv.length() != cvvDigits) result.setError(APIErrors.INVALID_CVV);

		// Run the Luhn algorythm
		if(!runLuhnAlgorythm(card_number)) result.setError(APIErrors.LUHN_ALGORYTHM_FAIL);

		result.finalize();

		return result;
	}

	private void setCvvDigits(String type, String num, APIResult res) {
		switch(type) {
			case "amex":
				cvvDigits = 4;

				break;
			case "master":
			case "visa":
				cvvDigits = 3;

				break;
			default:
				res.setError(APIErrors.INVALID_CARD_TYPE);

				break;
		}
	}

	private boolean runLuhnAlgorythm(String cardNumber) {
		int sum = 0, digits = cardNumber.length();
		// int lastDigit = cardNumber.charAt(digits - 1) - '0';

		boolean isOdd = false;

		for(int i = digits - 1; i >= 0; i--) {
			int d = cardNumber.charAt(i) - '0';

			if(isOdd) d *= 2;

			sum += d / 10;
			sum += d % 10;

			isOdd = !isOdd;
		}

		return(sum % 10 == 0); // lastDigit);
	}
}