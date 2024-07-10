package com.employee_app.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}


/*
@ResponseStatus(HttpStatus.NOT_FOUND): This annotation indicates that when this exception is thrown, 
the response should have an HTTP status code of 404 (Not Found).
Constructor: This constructor allows you to set a custom error message when the exception is thrown.
*/