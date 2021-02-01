package com.eatza.customer.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.eatza.customer.dto.CustomerDto;
import com.eatza.customer.dto.CustomerRequestDto;
import com.eatza.customer.exception.CustomerException;
import com.eatza.customer.model.Customer;
import com.eatza.customer.service.customerservice.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	CustomerService customerService;

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@PostMapping("/customer")
	public ResponseEntity<Customer> addCustomer(@RequestHeader String authorization,
			@RequestBody @Valid CustomerRequestDto customerRequestDto) throws CustomerException {
		logger.debug("Add customer method, calling the service");
		Customer customer = customerService.addCustomer(customerRequestDto);
		logger.debug("Customer added Successfully");
		return ResponseEntity.status(HttpStatus.OK).body(customer);
	}
	
	
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<Customer> getCustomerById(@RequestHeader String authorization, @PathVariable Long customerId)
			throws CustomerException {
		logger.debug("In get customer by id method, calling service to get Customer by ID");
		Customer customer = customerService.getCustomerById(customerId);
		logger.debug("Got Customer from service");
		return ResponseEntity.status(HttpStatus.OK).body(customer);
	}
	
	@PutMapping("/customer")
	public ResponseEntity<Customer> updateCustomer(@RequestHeader String authorization, @RequestBody @Valid  CustomerDto customerUpdateDto) throws CustomerException{
		logger.debug(" In updateCustomer method, calling service");
		Customer updatedResponse = customerService.updateCustomer(customerUpdateDto);
		logger.debug("Returning back the object");
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(updatedResponse);
	}
	
	@GetMapping("/customer/disable/{customerId}")
	public ResponseEntity<String> disableCustomer(@RequestHeader String authorization, @PathVariable Long customerId)
			throws CustomerException {
		logger.debug("In disable customer method, calling service to get Customer by disable customer");
		String result = customerService.disableCustomer(customerId);
		logger.debug("Received status from service");
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

}
