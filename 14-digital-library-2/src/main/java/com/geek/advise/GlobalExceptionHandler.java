package com.geek.advise;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.geek.dto.ApiError;
import com.geek.exception.ApplicationException;
@RestControllerAdvice

public class GlobalExceptionHandler {
@ExceptionHandler(ApplicationException.class)
	public ResponseEntity<ApiError> handleException(Exception e){
		ApiError err=new ApiError(e.getMessage(), "404");
		return new ResponseEntity<ApiError>(err,HttpStatus.NOT_FOUND);
		
	}
}
