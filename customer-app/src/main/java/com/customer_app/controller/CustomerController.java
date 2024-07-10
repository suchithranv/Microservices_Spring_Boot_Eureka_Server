package com.customer_app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.customer_app.dto.CustomerDTO;
import com.customer_app.exception.ResourceNotFoundException;
import com.customer_app.service.ICustomerService;

import jakarta.validation.Valid;

@RestController
public class CustomerController {
	
	private Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	private ICustomerService customerServiceImpl;
	
	@GetMapping("/customers")
	public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
		List<CustomerDTO> customerList = customerServiceImpl.getAllCustomers();
		return ResponseEntity.status(HttpStatus.OK).body(customerList);
	}
	
	@GetMapping("/customers/{id}")
	public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable("id") int id) throws ResourceNotFoundException {
		CustomerDTO customerDTO = customerServiceImpl.getCustomerById(id);
		return ResponseEntity.status(HttpStatus.OK).body(customerDTO);
	}
    
	@PostMapping("/customers")
	public ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody CustomerDTO customerDTO){
		CustomerDTO createdCustomer = customerServiceImpl.createCustomer(customerDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
	}
	
	@PutMapping("/customers/{id}")
	public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable("id") int id, @Valid @RequestBody CustomerDTO customerDTO) throws ResourceNotFoundException{
		CustomerDTO updatedCustomer = customerServiceImpl.updateCustomer(id, customerDTO);
		return ResponseEntity.status(HttpStatus.OK).body(updatedCustomer);
	}
	
	@DeleteMapping("/customers/{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable("id") int id) throws ResourceNotFoundException{
		customerServiceImpl.deleteCustomer(id);
		return ResponseEntity.ok().build();
	}
	
	
	// Exception handler for MethodArgumentNotValidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
    	 Map<String, String> errorMap = new HashMap<>();
    	 ex.getBindingResult().getAllErrors().forEach(error -> {
             String fieldName = ((FieldError) error).getField();
             String errorMessage = error.getDefaultMessage();
             errorMap.put(fieldName, errorMessage);
         });
         return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }
    
	// Exception handler for ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleResourceNotFoundExceptions(ResourceNotFoundException ex) {
    	   Map<String, String> errorMap = new HashMap<>();
           errorMap.put("error", ex.getMessage());
           return new ResponseEntity<>(errorMap, HttpStatus.NOT_FOUND);
    }
}
