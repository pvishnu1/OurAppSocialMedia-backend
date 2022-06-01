package com.ourapp.socialmedia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ourapp.socialmedia.entity.Customer;
import com.ourapp.socialmedia.repository.CustomerRepository;

@RestController
public class TestCustomerController {

	@Autowired
	private CustomerRepository customerRepo;

	@PostMapping("/addCustomer")
    public String addCustomer(@RequestBody Customer customer) {
		System.out.println("customer"+customer.getEmail()+"name"+customer.getName());
		Customer customer1 = customerRepo.save(customer);
        return customer1.toString();
    }
	
	@GetMapping("/customers")
    public List<Customer> listAll(Model model) {
        List<Customer> listCustomers = customerRepo.findAll();
        return listCustomers;
    }


}
