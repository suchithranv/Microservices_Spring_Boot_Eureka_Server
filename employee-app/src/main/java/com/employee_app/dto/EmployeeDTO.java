package com.employee_app.dto;

import com.address_app.dto.AddressDTO;

public class EmployeeDTO {
	 
	private int id;
	private String name;
	private String email;
	private String bloodGroup;
	private AddressDTO addressDTO;
	
	public AddressDTO getaddressDTO() {
		return addressDTO;
	}
	public void setaddressDTO(AddressDTO addressDTO) {
		this.addressDTO = addressDTO;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public AddressDTO getAddressDTO() {
		return addressDTO;
	}
	public void setAddressDTO(AddressDTO addressDTO) {
		this.addressDTO = addressDTO;
	}
	
	public EmployeeDTO(int id, String name, String email, String bloodGroup, AddressDTO addressDTO) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.bloodGroup = bloodGroup;
		this.addressDTO = addressDTO;
	}
	
	public EmployeeDTO() {
		super();
	}
	

}
