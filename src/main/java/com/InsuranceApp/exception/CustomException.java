package com.InsuranceApp.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.InsuranceApp.Utils.Response;


@RestController
@ControllerAdvice
public class CustomException extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return getResponseEntity(ex.getBindingResult().getFieldError().getDefaultMessage());
	}
	
	private ResponseEntity<Object> getResponseEntity(String message) {
		return new ResponseEntity<Object>(new Response(message, null), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleExcepiton(Exception ex, WebRequest request) {

		System.out.println("exception message=" + ex.getMessage());

		return getResponseEntity(ex.getMessage());
	}

}
