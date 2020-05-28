package com.nineleaps.springboot.training.exception;

public class UsernameNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public UsernameNotFoundException(String message) {
		super(message);
	}

	
	
}
