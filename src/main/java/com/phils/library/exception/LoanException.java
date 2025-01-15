package com.phils.library.exception;

public class LoanException extends RuntimeException  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3486048321528498780L;

	public LoanException() {
        super();
    }
	
	public LoanException(String message) {
        super(message);
    }

    public LoanException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoanException(Throwable cause) {
        super(cause);
    }
}
