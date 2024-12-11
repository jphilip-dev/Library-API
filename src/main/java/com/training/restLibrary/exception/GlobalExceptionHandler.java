package com.training.restLibrary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {
	
	// Handle BookNotFoundException
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorResponseFormat> handleBookNotFound(BookNotFoundException ex) {
        // Return a custom error message with HTTP status 404 (Not Found)
    	var error = new ErrorResponseFormat();
    	
    	error.setStatus(HttpStatus.NOT_FOUND.value());
    	error.setMessage(ex.getMessage());
    	error.setTimeStamp(System.currentTimeMillis());
    	
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Handle any other generic exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseFormat> handleGenericException(Exception ex) {
        // Return a custom error message with HTTP status 500 (Internal Server Error)
        var error = new ErrorResponseFormat();
        
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());  
        error.setMessage("An unexpected error occurred. Please try again later.");
        error.setTimeStamp(System.currentTimeMillis());
        
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


// To format error message as JSON body
class ErrorResponseFormat{
	private int status;
	private String message;
	private long timeStamp;
	
	public ErrorResponseFormat() {

	}

	public ErrorResponseFormat(int status, String message, long timeStamp) {
		this.status = status;
		this.message = message;
		this.timeStamp = timeStamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	
}
