package com.employee_app.service;

import java.util.List;

import com.employee_app.dto.EmployeeDTO;
import com.employee_app.exception.ResourceNotFoundException;

public interface IEmployeeService {
	
	EmployeeDTO getEmployeeById(int id) throws ResourceNotFoundException;

	List<EmployeeDTO> getAllEmployees();

	EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

	EmployeeDTO updateEmployee(int id, EmployeeDTO employeeDTO) throws ResourceNotFoundException;

	void deleteEmployee(int id) throws ResourceNotFoundException;

}
