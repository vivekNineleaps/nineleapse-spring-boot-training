package com.nineleaps.springboot.training.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.IM_USED)
public class UserExistsException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public UserExistsException(String message) {
		super(message);
	}
	

}
