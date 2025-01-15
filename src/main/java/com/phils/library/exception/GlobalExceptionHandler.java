package com.phils.library.exception;

import java.time.LocalDateTime;

import java.util.logging.Logger;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class.getName());

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // Get the first validation error message
        FieldError fieldError = ex.getBindingResult().getFieldErrors().get(0);
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        
        exceptionResponse.setTimeStamp(LocalDateTime.now());
        exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        exceptionResponse.setError("Bad Request");
        exceptionResponse.setMessage(fieldError.getDefaultMessage());
        
        logger.warning("Validation Error: ");
        logger.warning("Field: " + fieldError.getField());
        logger.warning("Rejected Value: " + fieldError.getRejectedValue());
        logger.warning("Message: " + fieldError.getDefaultMessage());
        
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleBookNotFoundException(BookNotFoundException ex) {
    	
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setTimeStamp(LocalDateTime.now());
        exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        exceptionResponse.setError("Bad Request");
        exceptionResponse.setMessage(ex.getMessage());
        
        
    	return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(UsernameAlreadyExistExeption.class)
    public ResponseEntity<ExceptionResponse> handleUsernameAlreadyExistExeption(UsernameAlreadyExistExeption ex) {
    	
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setTimeStamp(LocalDateTime.now());
        exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        exceptionResponse.setError("Bad Request");
        exceptionResponse.setMessage(ex.getMessage());
        
        
    	return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(LoanException.class)
    public ResponseEntity<ExceptionResponse> handleLoanException(LoanException ex) {
    	
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setTimeStamp(LocalDateTime.now());
        exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        exceptionResponse.setError("Bad Request");
        exceptionResponse.setMessage(ex.getMessage());
        
        
    	return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleOtherException(Exception ex) {
    	
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        
        exceptionResponse.setTimeStamp(LocalDateTime.now());
        exceptionResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        exceptionResponse.setError("Internal Server Error");
        exceptionResponse.setMessage("Please contact your administrator..");
        logger.warning("Other Exception - " + ex.getMessage());
    	return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
