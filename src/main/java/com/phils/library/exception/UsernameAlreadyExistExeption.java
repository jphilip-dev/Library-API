package com.phils.library.exception;

public class UsernameAlreadyExistExeption extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8156915077415686998L;

	public UsernameAlreadyExistExeption() {
        super();
    }
	
	public UsernameAlreadyExistExeption(String message) {
        super(message);
    }

    public UsernameAlreadyExistExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameAlreadyExistExeption(Throwable cause) {
        super(cause);
    }
}
