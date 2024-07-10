package com.employee_app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee_app.dto.EmployeeDTO;
import com.employee_app.exception.ResourceNotFoundException;
import com.employee_app.service.IEmployeeService;

import jakarta.validation.Valid;

@RestController
public class EmployeeController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private IEmployeeService employeeService;

	@GetMapping("/employees")
	public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
		logger.info("Fetching all employees");
		List<EmployeeDTO> employeeList = employeeService.getAllEmployees();
		return ResponseEntity.status(HttpStatus.OK).body(employeeList);

	}

	@GetMapping("/employees/{id}")
	public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable int id) throws ResourceNotFoundException {
		logger.info("Fetching employee with ID: {}", id);
		EmployeeDTO employeeDTO = employeeService.getEmployeeById(id);
		return ResponseEntity.status(HttpStatus.OK).body(employeeDTO);
	}

	@PostMapping("/employees")
	public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
		logger.info("Creating new employee");
		EmployeeDTO createdEmployee = employeeService.createEmployee(employeeDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
	}

	@PutMapping("/employees/{id}")
	public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable int id, @Valid @RequestBody EmployeeDTO employeeDTO) throws ResourceNotFoundException {
		logger.info("Updating employee with ID: {}", id);
		EmployeeDTO updatedEmployee = employeeService.updateEmployee(id, employeeDTO);
		return ResponseEntity.status(HttpStatus.OK).body(updatedEmployee);
	}

	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable int id) throws ResourceNotFoundException {
		logger.info("Deleting employee with ID: {}", id);
		employeeService.deleteEmployee(id);
		return ResponseEntity.ok().build();
	}

}

// instead of returning an entity(Employee), we return response model object. its not safe to expose entity to outer world
// use ResponseEntity to return response body with status code
//RestController is the combination of controller and responseBody annotation. it return data in json format
