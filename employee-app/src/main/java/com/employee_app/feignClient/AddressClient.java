package com.employee_app.feignClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.address_app.dto.AddressDTO;


// it is a proxy class because implementation is provided at run time
// give service name and its context path
// this will ask load balancer to give the instance of address-service and concate the url
@FeignClient(name = "address-app", path="address-app/api")
public interface AddressClient {
	
	
	// when we make a call to this method, spring will internally make this get request and provide implementation for this method
	// just we need to know the end point and the return type
	@GetMapping("/addresses/{employeeId}")
	public ResponseEntity<AddressDTO> getAddressById(@PathVariable("employeeId") int employeeId);
	
	@GetMapping("/addresses")
	public ResponseEntity<List<AddressDTO>> getAllAddresses();
    
	@PostMapping("/addresses")
	public ResponseEntity<AddressDTO> createAddress(@RequestBody AddressDTO addressDTO);
    
	@PutMapping("/addresses/{employeeId}")
	public ResponseEntity<AddressDTO> updateAddress(@PathVariable("employeeId") int employeeId, @RequestBody AddressDTO addressDTO);

	@DeleteMapping("/addresses/{employeeId}")
	public ResponseEntity<Void> deleteAddress(@PathVariable("employeeId") int employeeId);
	

}
