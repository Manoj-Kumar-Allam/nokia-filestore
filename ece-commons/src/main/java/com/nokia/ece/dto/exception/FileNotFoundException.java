package com.nokia.ece.dto.exception;

public class FileNotFoundException extends ESPRuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2186786388215293290L;

	private final String message;

	public FileNotFoundException(String message) {
		super(message);
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
