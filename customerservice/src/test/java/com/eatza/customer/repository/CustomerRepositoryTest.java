package com.eatza.customer.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.eatza.customer.model.Customer;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Test
	public void testfindById() {
		Optional<Customer> customer = customerRepository.findById(1L);
		assertEquals("Khagesh", customer.get().getName());
	}

}
