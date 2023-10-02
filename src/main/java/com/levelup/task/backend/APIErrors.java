package com.levelup.task.backend;

import java.security.InvalidParameterException;

public enum APIErrors {
	INVALID_CARD_TYPE(0, "Invalid card type"), INVALID_INPUT(1, "Invalid input"), EXPIRED_CARD(2, "Expired card"), INVALID_CARD_NUMBER(3, "Invalid card number"), INVALID_CVV(4, "Invalid CVV code"), INVALID_DATE_FORMAT(5, "Invalid date format"), INVALID_DATE(6, "Invalid date"), LUHN_ALGORYTHM_FAIL(7, "Luhn algorythm failed");

	private String message;

	private int value;

	private APIErrors(int value, String message) {
		this.message = message;
		this.value = value;
	}

	public static String getText(int index) {
		for(APIErrors err : APIErrors.class.getEnumConstants()) {
			if(err.getValue() == index) return err.message;
		}

		throw new InvalidParameterException("Error in getText(): 'unknown API error index!");
	}

	public int getValue() {
		return value;
	}
}