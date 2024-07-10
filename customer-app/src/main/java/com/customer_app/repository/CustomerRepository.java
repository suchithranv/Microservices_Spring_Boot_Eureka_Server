package com.customer_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.customer_app.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
