package com.eatza.customer.service;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.eatza.customer.dto.CustomerDto;
import com.eatza.customer.dto.CustomerRequestDto;
import com.eatza.customer.exception.CustomerException;
import com.eatza.customer.model.Customer;
import com.eatza.customer.repository.CustomerRepository;
import com.eatza.customer.service.customerservice.CustomerServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {
	
	@Mock
	CustomerRepository customerRepository;
	
	@InjectMocks
	CustomerServiceImpl customerService;
	
	@Test
	public void testAddCustomer() throws CustomerException {
		CustomerRequestDto customerRequestDto = new CustomerRequestDto();
		customerRequestDto.setAddress("Belgaum");
		customerRequestDto.setName("Ketan");
		customerRequestDto.setMobile("9878787878");
		Customer customer = new Customer(customerRequestDto.getName(), customerRequestDto.getMobile(), customerRequestDto.getAddress(), "CREATED");
		customer.setId(98L);
		when(customerRepository.save(any(Customer.class))).thenReturn(customer);
		Customer newCustomer = customerService.addCustomer(customerRequestDto);
		assertEquals(customer, newCustomer);
	}
	
	@Test
	public void testGetCustomerById() throws CustomerException {
		Customer customer = new Customer("Ketan", "9878787878","Belgaum" ,"CREATED");
		customer.setId(98L);
		when(customerRepository.findById(98L)).thenReturn(Optional.of(customer));
		Customer newCustomer = customerService.getCustomerById(98L);
		assertEquals(customer, newCustomer);
	}
	
	@Test
	public void testGetCustomerByIdCustomerNotFound(){
		when(customerRepository.findById(98L)).thenReturn(Optional.empty());
		try {
			customerService.getCustomerById(98L);
		} catch (CustomerException exp) {
			assertEquals("Customer not found for provided id", exp.getMessage());
		}
	}
	
	@Test
	public void testUpdateCustomer() throws CustomerException {
		CustomerDto customerRequestDto = new CustomerDto();
		customerRequestDto.setAddress("Belgaum");
		customerRequestDto.setName("Ketan");
		customerRequestDto.setMobile("9878787878");
		customerRequestDto.setCustomerId(98L);
		Customer customer = new Customer(customerRequestDto.getName(), customerRequestDto.getMobile(), customerRequestDto.getAddress(), "CREATED");
		customer.setId(98L);
		when(customerRepository.findById(98L)).thenReturn(Optional.of(customer));
		when(customerRepository.save(any(Customer.class))).thenReturn(customer);
		Customer newCustomer = customerService.updateCustomer(customerRequestDto);
		assertEquals(customer, newCustomer);
	
	}
	
	@Test
	public void testUpdateCustomerCustomerNotFound() {
		CustomerDto customerRequestDto = new CustomerDto();
		customerRequestDto.setAddress("Belgaum");
		customerRequestDto.setName("Ketan");
		customerRequestDto.setMobile("9878787878");
		customerRequestDto.setCustomerId(98L);
		when(customerRepository.findById(98L)).thenReturn(Optional.empty());
		try {
			customerService.updateCustomer(customerRequestDto);
		} catch (CustomerException exp) {
			assertEquals("Update failed, customer not found for provided id", exp.getMessage());
		}
	}
	
	@Test
	public void testDisableCustomer() throws CustomerException {
		Customer customer = new Customer("Ketan", "9878787878","Belgaum" ,"CREATED");
		customer.setId(98L);
		when(customerRepository.findById(98L)).thenReturn(Optional.of(customer));
		when(customerRepository.save(customer)).thenReturn(customer);
		String result = customerService.disableCustomer(98L);
		assertEquals("Customer disabled successfully", result);
	}
	
	@Test
	public void testDisableCustomerCustomerNotFound(){
		when(customerRepository.findById(98L)).thenReturn(Optional.empty());
		try {
			customerService.disableCustomer(98L);
		} catch (CustomerException exp) {
			assertEquals("Disable failed, customer not found for provided id", exp.getMessage());
		}
	}

}
