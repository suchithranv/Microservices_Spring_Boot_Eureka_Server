package com.employee_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee_app.entity.Employee;

// this is interface because implementation will be provided at runtime
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
               
}
 