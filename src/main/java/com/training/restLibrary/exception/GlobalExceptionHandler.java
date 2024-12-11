package com.training.restLibrary.exception;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.training.restLibrary.utils.ResponseFormat;



@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	
	// Handle BookNotFoundException
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ResponseFormat> handleBookNotFound(BookNotFoundException ex) {
    	logger.warn("Book not found: {}", nullCheck(ex.getMessage()));
    	
    	// Return a custom error message with HTTP status 404 (Not Found)
        return ResponseFormat.buildResponse(HttpStatus.NOT_FOUND, nullCheck(ex.getMessage()));
    }
    
    // Handle User name already exists
    @ExceptionHandler(UsernameTakenException.class)
    public ResponseEntity<ResponseFormat> handleUsernameTakenException(UsernameTakenException ex) {
    	logger.info("Username conflict: {}", nullCheck(ex.getMessage()));
    	// Handle UsernameTakenException (HTTP 409 - Conflict)
        return ResponseFormat.buildResponse(HttpStatus.CONFLICT, nullCheck(ex.getMessage()));
    }
    
    // Handle User name not Found
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ResponseFormat> handleUserNotFoundException(UserNotFoundException ex) {
    	logger.warn("User not found: {}", nullCheck(ex.getMessage()));
        return ResponseFormat.buildResponse(HttpStatus.NOT_FOUND, nullCheck(ex.getMessage()));
    }

    // Handle any other generic exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseFormat> handleGenericException(Exception ex) {
        
    	// Log the error and return a generic message to the user.
    	logger.error("Unexpected error occurred: {}", ex.getMessage(), ex);
    	
    	String message = "An unexpected error occurred. Please try again later.";
    	
    	// Return a custom error message with HTTP status 500 (Internal Server Error)
        return ResponseFormat.buildResponse(HttpStatus.INTERNAL_SERVER_ERROR,message);
    }
    
    private String nullCheck(String message) {
    	if (!StringUtils.hasText(message)) {
    		return "An error occurred. Please contact the administrator.";
    	}
    	return message;
    }
}




	

