package com.nokia.ece.dto.exception;

public class ESPRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6802689837457554540L;

	private final String message;

	public ESPRuntimeException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
