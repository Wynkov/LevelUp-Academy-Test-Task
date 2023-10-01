package com.levelup.task.backend;

import java.security.InvalidParameterException;

public class APIResult {
	private boolean errorList[] = new boolean[8];
	private boolean errors;

	public APIResult() {

		for(boolean err : errorList) {
			if(err) errors = true;
		}
	}

	public void setError(APIErrors err) {
		int error = checkError(err, "APIResult.setError()");

		errorList[error] = true;
	}

	public boolean getError(APIErrors err) {
		int error = checkError(err, "APIResult.getError()");

		return errorList[error];
	}

	private int checkError(APIErrors err, String method) {
		int error = err.getValue();

		if(error < 0 || error >= errorList.length) {
			errors = true;

			throw new InvalidParameterException("Error in " + method + ": '" + error + "' is an invalid API error!");
		}

		return error;
	}
}