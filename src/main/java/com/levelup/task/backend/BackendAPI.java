package com.levelup.task.backend;

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
	public boolean checkInformation(@RequestParam("type") String card_type, @RequestParam("num") String card_number, @RequestParam("date") String card_date, @RequestParam("cvv") String card_cvv) {
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
				return false;
		}

		int cNum, cDate, cCvv;

		// Check if inputs are integers
		try {
			cNum = Integer.parseInt(card_number);
			cDate = Integer.parseInt(card_date);
			cCvv = Integer.parseInt(card_cvv);
		} catch(NumberFormatException e) {
			return false;
		}
		
		// Check ...

		return true;
	}
}