package com.nokia.esp.core.aspect;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.nokia.ece.constants.ErrorMessages;
import com.nokia.ece.dto.ExceptionResponseDTO;
import com.nokia.ece.dto.exception.FileNotFoundException;
import com.nokia.ece.dto.exception.InvalidFileException;

/**
 * The Global Logger for FileStore Microservice
 */
@ControllerAdvice
@RestController
public class ExceptionHandlerAdvice {

	/** The Logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

	/**
	 * Handles the Custom Exception {@link com.nokia.ece.dto.exception.InvalidFileException}
	 * 
	 * @param ex      The Exception
	 * @param request The Web Request
	 * @return The Handling of the Exception that is Response Entity
	 * 
	 * @throws Exception
	 */
	@ExceptionHandler(InvalidFileException.class)
	public final ResponseEntity<Object> handleInvalidFileException(Exception ex, WebRequest request) throws Exception {
		LOGGER.error(ErrorMessages.ERROR, ex);
		ExceptionResponseDTO exceptionResponse = new ExceptionResponseDTO(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handles the Custom Exception {@link com.nokia.ece.dto.exception.FileNotFoundException}
	 * 
	 * @param ex 	The Exception
	 * @param request The Web Request
	 * @return The Handling of the Exception that is Response Entity
	 * 
	 * @throws Exception
	 */
	@ExceptionHandler(FileNotFoundException.class)
	public final ResponseEntity<Object> handleFileNotFoundException(Exception ex, WebRequest request) throws Exception {
		LOGGER.error(ErrorMessages.ERROR, ex);
		ExceptionResponseDTO exceptionResponse = new ExceptionResponseDTO(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	/**
	 * Handles the all the Exceptions {@link java.lang.Exception}
	 * 
	 * @param ex      The Exception
	 * @param request The Web Request
	 * @return The Handling of the Exception that is Response Entity
	 * 
	 * @throws Exception
	 */
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) throws Exception {
		LOGGER.error(ErrorMessages.ERROR, ex);
		ExceptionResponseDTO exceptionResponse = new ExceptionResponseDTO(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
