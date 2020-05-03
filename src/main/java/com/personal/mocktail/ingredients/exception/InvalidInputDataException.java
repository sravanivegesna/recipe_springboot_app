package com.personal.mocktail.ingredients.exception;

public class InvalidInputDataException extends Exception {

	private static final long serialVersionUID = -8612300245521815614L;

	public InvalidInputDataException() {
		super();
	}

	public InvalidInputDataException(String message) {
		super(message);
	}

	public InvalidInputDataException(String message, Throwable cause) {
		super(message, cause);
	}

}
