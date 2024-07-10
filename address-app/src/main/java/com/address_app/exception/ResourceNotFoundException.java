package com.address_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception{
	public ResourceNotFoundException(String message) {
		super(message);
	}
}

/*
 @ResponseStatus(HttpStatus.NOT_FOUND) to ensure that when the exception is thrown, 
 a 404 status code is returned along with the exception message
*/

