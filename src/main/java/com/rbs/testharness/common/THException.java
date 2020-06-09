package com.rbs.testharness.common;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

public class THException extends RuntimeException{
	
	private static final long serialVersionUID = 12L;
	
	private HttpStatus status;
	private String message;
	private List<String> errors;
	private LocalDateTime timestamp;
	
	public THException() {
		//User defined default constructor
	}
	
	public THException(HttpStatus status, String message, List<String> errors) {
	    super(message);
	    this.status = status;
	    this.message = message;
	    this.errors = errors;
	}
	
	public THException(HttpStatus status, String message, String error) {
	    super(message);
	    this.status = status;
	    this.message = message;
	    errors = Arrays.asList(error);
	}
 
	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public List<String> getErrors() {
		return errors;
	}
	
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
}
