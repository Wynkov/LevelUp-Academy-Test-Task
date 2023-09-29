package com.levelup.backend;

public class BackendAPI {
	/** 
	 * Method for verifying the credit card information
	 * 
	 * Returns false if verification failed, true otherwise 
	 * 
	 * **/
	
	public boolean checkInformation(String card_type, int card_number, int card_date, int card_cvv) {
		int cvvDigits = 0;

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