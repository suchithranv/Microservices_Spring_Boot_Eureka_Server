package com.customer_app.dto;

import com.employee_app.dto.EmployeeDTO;

public class CustomerDTO {
	
	private int id;
	private String name;
	private String email;
	private String project;
	private int employeeId;
	private EmployeeDTO employeeDTO;
	
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
	public EmployeeDTO getEmployeeDTO() {
		return employeeDTO;
	}
	public void setEmployeeDTO(EmployeeDTO employeeDTO) {
		this.employeeDTO = employeeDTO;
	}
	
	public CustomerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CustomerDTO(int id, String name, String email, String project, int employeeId, EmployeeDTO employeeDTO) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.project = project;
		this.employeeId = employeeId;
		this.employeeDTO = employeeDTO;
	}
	
	@Override
	public String toString() {
		return "CustomerDTO [id=" + id + ", name=" + name + ", email=" + email + ", project=" + project
				+ ", employeeId=" + employeeId + ", employeeDTO=" + employeeDTO + "]";
	}
		
}
