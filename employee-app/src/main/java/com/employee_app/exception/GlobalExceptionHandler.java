package com.employee_app.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", ex.getMessage());
        return new ResponseEntity<>(errorMap, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}

/*
 This class is an exception handler that handles exceptions globally across the application. 
 It uses @RestControllerAdvice to intercept exceptions thrown by any controller
 


handleValidationExceptions(MethodArgumentNotValidException ex):
------------------------------------------------------------------
This method handles MethodArgumentNotValidException, which is thrown when validation on an argument annotated with @Valid fails.
It constructs a response with validation errors and a 400 status code.
Map<String, String> errors: A map containing field names and corresponding error messages.
ex.getBindingResult().getAllErrors(): Retrieves all validation errors.
((FieldError) error).getField(): Gets the name of the field that failed validation.
error.getDefaultMessage(): Gets the default error message for the field.
ResponseEntity: A wrapper that contains the response body (errors map) and the HTTP status code.


How It Works :


When a Resource is Not Found:
----------------------------------------
If a resource, like an employee, is not found in the database, the ResourceNotFoundException is thrown.
The GlobalExceptionHandler catches this exception.
The handleResourceNotFoundException method is invoked, constructing a response with a 404 status code and the error message.

When Validation Fails:
-------------------------------------------
If validation fails for an input (e.g., invalid email format), MethodArgumentNotValidException is thrown.
The GlobalExceptionHandler catches this exception.
The handleValidationExceptions method is invoked, constructing a response with a 400 status code and detailed validation error messages.
*/