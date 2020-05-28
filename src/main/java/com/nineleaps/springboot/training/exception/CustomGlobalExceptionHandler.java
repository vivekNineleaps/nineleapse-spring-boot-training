package com.nineleaps.springboot.training.exception;

import java.util.Date;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		CustomExceptionResponse customExceptionResponse = new CustomExceptionResponse(new Date(),
				"From MethdArgumentNotValid Exception in GEH", ex.getMessage());
		return new ResponseEntity<Object>(customExceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		CustomExceptionResponse customExceptionResponse = new CustomExceptionResponse(new Date(),
				"RequestMethodNotSupported Exception-Method Not Allowed. ", ex.getMessage());
		return new ResponseEntity<Object>(customExceptionResponse, HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
		CustomExceptionResponse customExceptionResponse = new CustomExceptionResponse(new Date(),
				"User Not Found Exception->{}", ex.getMessage());
		return new ResponseEntity<Object>(customExceptionResponse, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(UsernameNotFoundException.class)
    public final ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException ex, WebRequest request) {
		CustomExceptionResponse customExceptionResponse = new CustomExceptionResponse(new Date(),
				ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<Object>(customExceptionResponse, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
		CustomExceptionResponse customExceptionResponse = new CustomExceptionResponse(new Date(),
				ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<Object>(customExceptionResponse, HttpStatus.BAD_REQUEST);
    }
 

}
