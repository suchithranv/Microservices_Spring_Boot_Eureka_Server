package com.address_app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;
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

import com.address_app.dto.AddressDTO;
import com.address_app.exception.ResourceNotFoundException;
import com.address_app.service.IAddressService;

import jakarta.validation.Valid;

@RestController
public class AddressController {
	
	private Logger logger = LoggerFactory.getLogger(AddressController.class);
	
	@Autowired
	private IAddressService addressServiceImpl;
	
	@GetMapping("/addresses")
	public ResponseEntity<List<AddressDTO>> getAllAddresses(){
		logger.info("fetch all employee addresses");
		List<AddressDTO> addressList = addressServiceImpl.getAllAddresses();
		return ResponseEntity.status(HttpStatus.OK).body(addressList);
	}
		
	@GetMapping("/addresses/{employeeId}")
	public ResponseEntity<AddressDTO> getAddressByEmployeeId(@PathVariable("employeeId") int employeeId) throws ResourceNotFoundException{
		logger.info("fetching employee address of id : " +employeeId);
		AddressDTO addressDTO = addressServiceImpl.getAddressByEmployeeId(employeeId);
		return ResponseEntity.status(HttpStatus.OK).body(addressDTO);
	}

	@PostMapping("/addresses")
	public ResponseEntity<AddressDTO> createAddress(@Valid @RequestBody AddressDTO addressDTO){
		logger.info("creating employee address");
		AddressDTO createdAddress = addressServiceImpl.createAddress(addressDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdAddress);
		
	}
    
	@PutMapping("/addresses/{employeeId}")
	public ResponseEntity<AddressDTO> updateAddress(@PathVariable("employeeId") int employeeId, @Valid @RequestBody AddressDTO addressDTO) throws ResourceNotFoundException{
		logger.info("updating employee address");
		AddressDTO updatedAddress = addressServiceImpl.updateAddress(addressDTO);
		return ResponseEntity.status(HttpStatus.OK).body(updatedAddress);
		
	}

	@DeleteMapping("/addresses/{employeeId}")
	public ResponseEntity<Void> deleteAddress(@PathVariable("employeeId") int employeeId) throws ResourceNotFoundException{
		logger.info("delete employee address");
		addressServiceImpl.deleteAddress(employeeId);
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

/*
2xx Successful Responses
--------------------------------
HttpStatus.OK (200) - OK
HttpStatus.CREATED (201) - Created
HttpStatus.NO_CONTENT (204) - No Content

3xx Redirection Messages
----------------------------------
HttpStatus.MOVED_PERMANENTLY (301) - Moved Permanently
HttpStatus.FOUND (302) - Found
HttpStatus.SEE_OTHER (303) - See Other
HttpStatus.NOT_MODIFIED (304) - Not Modified
HttpStatus.TEMPORARY_REDIRECT (307) - Temporary Redirect
HttpStatus.PERMANENT_REDIRECT (308) - Permanent Redirect

4xx Client Error Responses
------------------------------------
HttpStatus.BAD_REQUEST (400) - Bad Request
HttpStatus.UNAUTHORIZED (401) - Unauthorized
HttpStatus.FORBIDDEN (403) - Forbidden
HttpStatus.NOT_FOUND (404) - Not Found
HttpStatus.METHOD_NOT_ALLOWED (405) - Method Not Allowed
HttpStatus.NOT_ACCEPTABLE (406) - Not Acceptable
HttpStatus.CONFLICT (409) - Conflict
HttpStatus.GONE (410) - Gone
HttpStatus.UNSUPPORTED_MEDIA_TYPE (415) - Unsupported Media Type

5xx Server Error Responses
------------------------------------------
HttpStatus.INTERNAL_SERVER_ERROR (500) - Internal Server Error
HttpStatus.NOT_IMPLEMENTED (501) - Not Implemented
HttpStatus.BAD_GATEWAY (502) - Bad Gateway
HttpStatus.SERVICE_UNAVAILABLE (503) - Service Unavailable
HttpStatus.GATEWAY_TIMEOUT (504) - Gateway Timeout
*/