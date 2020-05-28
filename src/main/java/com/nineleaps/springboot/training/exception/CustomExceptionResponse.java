package com.nineleaps.springboot.training.exception;

import java.util.Date;

import lombok.Data;

public @Data class CustomExceptionResponse {
	
	private Date timestamp;
	private String message;
	private String errordetails;
	
	public CustomExceptionResponse(Date timestamp, String message, String errordetails) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.errordetails = errordetails;
	}

	
}
