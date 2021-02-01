package com.eatza.customer.service.customerservice;

import com.eatza.customer.dto.CustomerDto;
import com.eatza.customer.dto.CustomerRequestDto;
import com.eatza.customer.exception.CustomerException;
import com.eatza.customer.model.Customer;

public interface CustomerService {

	Customer addCustomer(CustomerRequestDto customerRequestDto) throws CustomerException;

	Customer getCustomerById(Long customerId) throws CustomerException;

	Customer updateCustomer(CustomerDto customerUpdateDto) throws CustomerException;

	String disableCustomer(Long customerId) throws CustomerException;

}
