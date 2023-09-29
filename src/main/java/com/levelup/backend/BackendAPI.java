package com.levelup.backend;

public class BackendAPI {
	private int amexStart1 = 34;	// Starting digits (option 1) of an Amex card
	private int amexStart2 = 37;	// Starting digits (option 2) of an Amex card
	private int numMin = 16;		// Minimum number of digits of a card number
	private int numMax = 19;		// Maximum number of digits of a card number
	private int cvvDigits;			// Number of digits of the CVV code

	/** 
	 * Method for verifying the credit card information
	 * 
	 * Returns false if verification failed, true otherwise 
	 * 
	 * **/
	public boolean checkInformation(String card_type, int card_number, int card_date, int card_cvv) {
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

		return true;
	}
}