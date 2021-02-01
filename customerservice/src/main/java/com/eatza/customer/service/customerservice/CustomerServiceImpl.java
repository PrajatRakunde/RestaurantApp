package com.eatza.customer.service.customerservice;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eatza.customer.dto.CustomerDto;
import com.eatza.customer.dto.CustomerRequestDto;
import com.eatza.customer.exception.CustomerException;
import com.eatza.customer.model.Customer;
import com.eatza.customer.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer addCustomer(CustomerRequestDto customerRequestDto) throws CustomerException {
		logger.debug("entering method addCustomer, create Customer object to persist");
		Customer customer = new Customer(customerRequestDto.getName(), customerRequestDto.getMobile(), customerRequestDto.getAddress() , "CREATED");
		logger.debug("calling repository save customer");
		customer = customerRepository.save(customer);
		logger.debug("returning customer");
		return customer;
	}

	@Override
	public Customer getCustomerById(Long customerId) throws CustomerException {
		logger.debug("entering method getCustomerById, retrieving data from repository");
		Optional<Customer> customer = customerRepository.findById(customerId);
		if (!customer.isPresent()) {
			throw new CustomerException("Customer not found for provided id");
		}
		return customer.get();
	}

	@Override
	public Customer updateCustomer(CustomerDto customerUpdateDto) throws CustomerException {
		logger.debug("entering method updateCustomer, retrieving customer object from repo");
		Optional<Customer> customer = customerRepository.findById(customerUpdateDto.getCustomerId());
		if(!customer.isPresent()) {
			throw new CustomerException("Update failed, customer not found for provided id");
		}
		Customer oldCustomer = customer.get();
		Customer newCustomer = new Customer(customerUpdateDto.getName(), customerUpdateDto.getMobile(), customerUpdateDto.getAddress(), "UPDATED");
		newCustomer.setId(oldCustomer.getId());
		newCustomer.setCreateDateTime(oldCustomer.getCreateDateTime());
		newCustomer = customerRepository.save(newCustomer);
		return newCustomer;
	}

	@Override
	public String disableCustomer(Long customerId) throws CustomerException {
		logger.debug("entering method disableCustomer, retrieving customer object from repo");
		Optional<Customer> customer = customerRepository.findById(customerId);
		if(!customer.isPresent()) {
			throw new CustomerException("Disable failed, customer not found for provided id");
		}
		customer.get().setStatus("DISABLED");
		customerRepository.save(customer.get());
		return "Customer disabled successfully";
	}
	
	
}
