package com.phils.library.exception;

public class BookNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4008616951764915189L;

	public BookNotFoundException() {
        super();
    }
	
	public BookNotFoundException(String message) {
        super(message);
    }

    public BookNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookNotFoundException(Throwable cause) {
        super(cause);
    }
}
