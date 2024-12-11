package com.training.restLibrary.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

//To format Response as a JSON body
public class ResponseFormat {
	private int status;
	private String message;
	private long timeStamp;

	public static ResponseEntity<ResponseFormat> buildResponse(HttpStatus status, String message) {
		var response = new ResponseFormat(status.value(), message, System.currentTimeMillis());
		return new ResponseEntity<ResponseFormat>(response, status);
	}

	public ResponseFormat() {

	}

	public ResponseFormat(int status, String message, long timeStamp) {
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
