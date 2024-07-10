package com.employee_app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name= "employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
	private String name;
	
	@NotBlank(message = "Email is mandatory")
	@Email(message = "Email should be valid")
	private String email;
	
	@NotBlank(message = "Blood group is mandatory")
	@Column(name="bloodgroup")
	private String bloodGroup;
	
	@NotBlank(message = "Address is mandatory")
	private int addressId;
	
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
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
    
	public Employee(int id, String name, String email, String bloodGroup, int addressId) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.bloodGroup = bloodGroup;
		this.addressId = addressId;
	}
	
	public Employee() {
		super();
	}
	
	public Employee(String name, String email, String bloodGroup, int addressId) {
		super();
		this.name = name;
		this.email = email;
		this.bloodGroup = bloodGroup;
		this.addressId = addressId;
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", email=" + email + ", bloodGroup=" + bloodGroup
				+ ", addressId=" + addressId + "]";
	}
		
}
