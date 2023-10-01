package com.levelup.task.backend;

import java.security.InvalidParameterException;

public class APIResult {
	public String activeErrors = "";

	public boolean errors;

	private boolean errorList[] = new boolean[APIErrors.values().length];

	public void finalize() {
		for(int i = 0; i < errorList.length; i++) {
			if(errorList[i]) {
				if(errors) activeErrors += ", ";

				activeErrors += APIErrors.getText(i);
				errors = true;
			}
		}

		if(!errors) activeErrors = "NONE";
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