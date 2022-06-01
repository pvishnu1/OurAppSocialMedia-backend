package com.ourapp.socialmedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ourapp.socialmedia.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	
}
