package com.customer_app.feignClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.employee_app.dto.EmployeeDTO;

@FeignClient(name = "employee-app", path="employee-app/api")
public interface EmployeeClient {
	
	@GetMapping("/employees")
	public ResponseEntity<List<EmployeeDTO>> getAllEmployees();
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable int id);

}
