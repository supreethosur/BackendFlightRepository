package com.example.Advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception e){
		e.printStackTrace();
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.OK);
	}

}
