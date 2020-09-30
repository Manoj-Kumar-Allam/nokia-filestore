package com.nokia.ece.dto.exception;

public class InvalidFileException extends ESPRuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2847965713489212606L;

	private final String message;

	public InvalidFileException(String message) {
		super(message);
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
