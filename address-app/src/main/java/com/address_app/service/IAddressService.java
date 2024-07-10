package com.address_app.service;

import java.util.List;
import com.address_app.dto.AddressDTO;
import com.address_app.exception.ResourceNotFoundException;

public interface IAddressService {

	List<AddressDTO> getAllAddresses();

	AddressDTO getAddressByEmployeeId(int id) throws ResourceNotFoundException;

	AddressDTO createAddress(AddressDTO addressDTO);

	AddressDTO updateAddress(AddressDTO addressDTO) throws ResourceNotFoundException;

	void deleteAddress(int employeeId) throws ResourceNotFoundException;

}
