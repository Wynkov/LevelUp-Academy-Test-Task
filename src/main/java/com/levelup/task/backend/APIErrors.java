package com.levelup.task.backend;

import java.security.InvalidParameterException;

public enum APIErrors {
	INVALID_CARD_TYPE(0), INVALID_INPUT(1), EXPIRED_CARD(2), INVALID_CARD_NUMBER(3), INVALID_CVV(4), INVALID_DATE_FORMAT(5), INVALID_DATE(6), LUHN_ALGORYTHM_FAIL(7);

	private int value;

	private APIErrors(int value) {
		this.value = value;
	}

	public static String getText(int index) {
		for(APIErrors err : APIErrors.class.getEnumConstants()) {
			if(err.getValue() == index) return err.toString();
		}

		throw new InvalidParameterException("Error in getText(): 'unknown API error index!");
	}

	public int getValue() {
		return value;
	}
}