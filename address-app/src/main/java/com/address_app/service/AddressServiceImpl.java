package com.address_app.service;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.address_app.dto.AddressDTO;
import com.address_app.entity.Address;
import com.address_app.exception.ResourceNotFoundException;
import com.address_app.repository.AddressRepository;

@Service
public class AddressServiceImpl implements IAddressService{

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<AddressDTO> getAllAddresses() {
		List<Address> addressList = addressRepository.findAll();
		List<AddressDTO> addressListDTO = Arrays.asList(modelMapper.map(addressList, AddressDTO.class));
		return addressListDTO;
	}

	@Override
	public AddressDTO getAddressByEmployeeId(int employeeId) throws ResourceNotFoundException {
		Address address = addressRepository.findByEmployeeId(employeeId);
		if(address == null) {
			throw new ResourceNotFoundException("Address of employee id : " +employeeId+ " not found");
		}
		AddressDTO addressDTO = modelMapper.map(address, AddressDTO.class);
		return addressDTO;
	}

	@Override
	public AddressDTO createAddress(AddressDTO addressDTO) {
		Address address = new Address(addressDTO.getStreet(), addressDTO.getCity(), addressDTO.getState(), addressDTO.getZipCode(), addressDTO.getEmployeeId());
		Address createdAddress = addressRepository.save(address);
		
		addressDTO.setId(createdAddress.getId());
		return addressDTO;
	}

	@Override
	public AddressDTO updateAddress(AddressDTO addressDTO) throws ResourceNotFoundException {
		Address existingAddress = addressRepository.findById(addressDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("Address of employee id : " + addressDTO.getEmployeeId() + " not found"));
		existingAddress = new Address(addressDTO.getStreet(), addressDTO.getCity(), addressDTO.getState(), addressDTO.getZipCode(), addressDTO.getEmployeeId());
		Address updatedAddress = addressRepository.save(existingAddress);
		
		addressDTO.setId(updatedAddress.getId());
		return addressDTO;
	}

	@Override
	public void deleteAddress(int employeeId) throws ResourceNotFoundException {
	   Address existingAddress = addressRepository.findByEmployeeId(employeeId);
	   if(existingAddress == null) {
		   throw new ResourceNotFoundException("Address of employee id : " + employeeId + " not found");
	   }
	   addressRepository.deleteByEmployeeId(employeeId);
		
	}	

}
