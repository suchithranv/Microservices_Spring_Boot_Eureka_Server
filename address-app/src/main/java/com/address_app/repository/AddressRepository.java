package com.address_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.address_app.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>{

	Address findByEmployeeId(int employeeId);

	void deleteByEmployeeId(int employeeId);
    
}
