package com.customer_app.service;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer_app.dto.CustomerDTO;
import com.customer_app.entity.Customer;
import com.customer_app.exception.ResourceNotFoundException;
import com.customer_app.feignClient.EmployeeClient;
import com.customer_app.repository.CustomerRepository;
import com.employee_app.dto.EmployeeDTO;

@Service
public class CustomerServiceImpl implements ICustomerService{
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private EmployeeClient employeeClient;

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<CustomerDTO> getAllCustomers() {
		List<Customer> customerList = customerRepo.findAll();
		List<CustomerDTO> customerDTOList = Arrays.asList(modelMapper.map(customerList, CustomerDTO.class));
		List<EmployeeDTO> employeeDTOList = employeeClient.getAllEmployees().getBody();
		
		customerDTOList.forEach( customer -> {
			for(EmployeeDTO employee : employeeDTOList) {
				if(customer.getEmployeeId() == employee.getId()) {
				       customer.setEmployeeDTO(employee);
				}
			}			
		});
		
		return customerDTOList;
	}

	@Override
	public CustomerDTO getCustomerById(int id) throws ResourceNotFoundException {
		Customer customer = customerRepo.findById(id).orElseThrow( () -> new ResourceNotFoundException("Customer with id : " + id + "not found"));
		CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
		EmployeeDTO emloyeeDTO = employeeClient.getEmployeeById(customer.getEmployeeId()).getBody();
		customerDTO.setEmployeeDTO(emloyeeDTO);
		return customerDTO;
	}

	@Override
	public CustomerDTO createCustomer(CustomerDTO customerDTO) {
		Customer customer = new Customer(customerDTO.getName(), customerDTO.getEmail(), customerDTO.getProject(), customerDTO.getEmployeeId());
		Customer createdCustomer = customerRepo.save(customer);		
		customerDTO.setId(createdCustomer.getId());
		return customerDTO;
	}

	@Override
	public CustomerDTO updateCustomer(int id, CustomerDTO customerDTO) throws ResourceNotFoundException {
		Customer existingCustomer = customerRepo.findById(id).orElseThrow( () -> new ResourceNotFoundException("Customer with id : " + id + "not found"));
		existingCustomer.setName(customerDTO.getName());
		existingCustomer.setEmail(customerDTO.getEmail());
		existingCustomer.setProject(customerDTO.getProject());
		existingCustomer.setEmployeeId(customerDTO.getEmployeeId());
		Customer updatedCustomer = customerRepo.save(existingCustomer);	
		
		customerDTO.setId(updatedCustomer.getId());
		return customerDTO;
	}

	@Override
	public void deleteCustomer(int id) throws ResourceNotFoundException {
		Customer existingCustomer = customerRepo.findById(id).orElseThrow( () -> new ResourceNotFoundException("Customer with id : " + id + "not found"));
		customerRepo.deleteById(existingCustomer.getId());	
	}

}
