package com.levelup.task.backend;

public enum APIErrors {
	INVALID_CARD_TYPE(0), INVALID_INPUT(1), EXPIRED_CARD(2);

	private int value;

	private APIErrors(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}