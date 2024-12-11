package com.training.restLibrary.exception;

public class UsernameTakenException extends RuntimeException {
	public UsernameTakenException(String message) {
		super(message);
	}
}
