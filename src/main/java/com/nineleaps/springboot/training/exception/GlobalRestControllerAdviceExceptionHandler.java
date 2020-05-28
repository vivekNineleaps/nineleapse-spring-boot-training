package com.nineleaps.springboot.training.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalRestControllerAdviceExceptionHandler {
	
	@ExceptionHandler(UsernameNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public CustomExceptionResponse usernameNotFound(UsernameNotFoundException ex) {
		
		return new CustomExceptionResponse(new Date(),"From GlobalRestControllerAdviceExceptionHandler",ex.getMessage());
		
	}

}
