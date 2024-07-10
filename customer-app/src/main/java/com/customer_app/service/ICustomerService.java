package com.customer_app.service;

import java.util.List;

import com.customer_app.dto.CustomerDTO;
import com.customer_app.exception.ResourceNotFoundException;


public interface ICustomerService {

	List<CustomerDTO> getAllCustomers();

	CustomerDTO getCustomerById(int id) throws ResourceNotFoundException;

	CustomerDTO createCustomer(CustomerDTO customerDTO);

	CustomerDTO updateCustomer(int id, CustomerDTO customerDTO) throws ResourceNotFoundException;

	void deleteCustomer(int id) throws ResourceNotFoundException;

}
