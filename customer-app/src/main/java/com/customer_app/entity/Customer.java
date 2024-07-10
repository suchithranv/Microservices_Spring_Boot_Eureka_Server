package com.customer_app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "Name is mandatory")  // it only checks for null value
	@Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
	private String name;
	
	@NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
	private String email;
	
	@NotBlank(message = "Project is mandatory")  // it check both null and content. content should not be just whitespaces or empty
    @Size(min = 2, max = 100, message = "Project name must be between 2 and 100 characters")
	private String project;
	
	@NotNull(message = "Employee ID is mandatory")
	private int employeeId;
	
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
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	
	public Customer(int id, String name, String email, String project, int employeeId) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.project = project;
		this.employeeId = employeeId;
	}
	
	public Customer() {
		super();
	}
	
	public Customer(String name, String email, String project, int employeeId) {
		this.name = name;
		this.email = email;
		this.project = project;
		this.employeeId = employeeId;
	}
	
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", email=" + email + ", project=" + project + ", employeeId=" + employeeId + "]";
	}
	
}

