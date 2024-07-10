package com.employee_app.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.address_app.dto.AddressDTO;
import com.employee_app.dto.EmployeeDTO;
import com.employee_app.entity.Employee;
import com.employee_app.exception.ResourceNotFoundException;
import com.employee_app.feignClient.AddressClient;
import com.employee_app.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private EmployeeRepository employeeRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AddressClient addressClient;

	@Override
	public List<EmployeeDTO> getAllEmployees() {
		List<Employee> employeeList = employeeRepo.findAll();
		List<EmployeeDTO> employeeDTOList = Arrays.asList(modelMapper.map(employeeList, EmployeeDTO.class));
		List<AddressDTO> addressDTOList =   addressClient.getAllAddresses().getBody();
		employeeDTOList.forEach(emp -> {
			for (AddressDTO addressDTO : addressDTOList) {
				emp.setaddressDTO(addressDTO);
			}
		});
		return employeeDTOList;
	}

	@Override
	public EmployeeDTO getEmployeeById(int id) throws ResourceNotFoundException {
		Employee employee = employeeRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found for ID: " + id));
		EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);

		// using openFeign client to make external rest calls
		AddressDTO addressDTO = addressClient.getAddressById(id).getBody();
		employeeDTO.setaddressDTO(addressDTO);
		return employeeDTO;
	}

	@Override
	public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
		// Save address details first
		AddressDTO addressDTO = employeeDTO.getaddressDTO();
		AddressDTO createdAddress = addressClient.createAddress(addressDTO).getBody();

		// Save employee details with address ID
		Employee employee = new Employee(employeeDTO.getName(), employeeDTO.getEmail(), employeeDTO.getBloodGroup(),
				createdAddress.getId());
		Employee savedEmployee = employeeRepo.save(employee);

		// Set the generated ID and address back in the DTO
		employeeDTO.setId(savedEmployee.getId());
		employeeDTO.setaddressDTO(createdAddress);
		return employeeDTO;
	}

	@Override
	public EmployeeDTO updateEmployee(int id, EmployeeDTO employeeDTO) throws ResourceNotFoundException {
		// Find the existing employee
		Employee existingEmployee = employeeRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found for ID: " + id));

		// Update address details
		AddressDTO addressDTO = employeeDTO.getAddressDTO();
		addressDTO.setId(existingEmployee.getAddressId());
		AddressDTO updatedAddress = addressClient.updateAddress(addressDTO.getId(), addressDTO).getBody();

		// Update employee details
		existingEmployee.setName(employeeDTO.getName());
		existingEmployee.setEmail(employeeDTO.getEmail());
		existingEmployee.setBloodGroup(employeeDTO.getBloodGroup());
		Employee updatedEmployee = employeeRepo.save(existingEmployee);

		// Set updated details back in the DTO
		employeeDTO.setId(updatedEmployee.getId());
		employeeDTO.setAddressDTO(updatedAddress);
		return employeeDTO;
	}

	@Override
	public void deleteEmployee(int id) throws ResourceNotFoundException {
		// Find the existing employee
		Employee existingEmployee = employeeRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found for ID: " + id));

		// Delete the address first
		addressClient.deleteAddress(existingEmployee.getAddressId());

		// Delete the employee
		employeeRepo.deleteById(id);

	}

}
