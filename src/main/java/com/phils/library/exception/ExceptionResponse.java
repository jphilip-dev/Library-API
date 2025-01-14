package com.phils.library.exception;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ExceptionResponse {
	
	private LocalDateTime timeStamp;
	private int status;
	private String error;
	private String message;
	
}
