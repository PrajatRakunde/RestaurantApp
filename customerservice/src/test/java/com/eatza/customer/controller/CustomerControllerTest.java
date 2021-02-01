package com.eatza.customer.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.eatza.customer.dto.CustomerDto;
import com.eatza.customer.dto.CustomerRequestDto;
import com.eatza.customer.exception.CustomerException;
import com.eatza.customer.model.Customer;
import com.eatza.customer.service.customerservice.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RunWith(SpringRunner.class)
@WebMvcTest(value= CustomerController.class)
public class CustomerControllerTest {
	
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CustomerService customerService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	String jwt="";
	private static final long EXPIRATIONTIME = 900000;
	@Before
	public void setup() {
		jwt = "Bearer "+Jwts.builder().setSubject("user").claim("roles", "user").setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretkey").setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME)).compact();
	}
	
	
	@Test
	public void testAddCustomer() throws Exception {
		CustomerRequestDto requestDto = new CustomerRequestDto();
		requestDto.setName("Ketan Hulaji");
		requestDto.setAddress("Belgaum");
		requestDto.setMobile("7974792787");
		
		Customer customer = new Customer();
		customer.setAddress(requestDto.getAddress());
		customer.setName(requestDto.getName());
		customer.setMobile(requestDto.getMobile());
		customer.setId(98L);
		when(customerService.addCustomer(any(CustomerRequestDto.class))).thenReturn(customer);
		RequestBuilder request = MockMvcRequestBuilders.post(
				"/customer")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString((requestDto)))
				.header(HttpHeaders.AUTHORIZATION,
						jwt);
		 mockMvc.perform(request)
		.andExpect(status().is(200))
		.andExpect(jsonPath("id", is(98)))
		.andReturn();
	}
	
	@Test
	public void testAddCustomerValidationError() throws Exception {
		CustomerRequestDto requestDto = new CustomerRequestDto();
		requestDto.setName("Ketan Hulaji");
		requestDto.setAddress("Belgaum");
		requestDto.setMobile("79747927879");
		
		RequestBuilder request = MockMvcRequestBuilders.post(
				"/customer")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString((requestDto)))
				.header(HttpHeaders.AUTHORIZATION,
						jwt);
		 mockMvc.perform(request)
		.andExpect(status().is(400))
		.andExpect(content().string("Invalid mobile number, mobile number must be 10 digits long"))
		.andReturn();
	}
	
	@Test
	public void testGetCustomerById() throws Exception {
		
		Customer customer = new Customer();
		customer.setAddress("Belgaum");
		customer.setName("Ketan");
		customer.setMobile("8798797272");
		customer.setId(98L);
		when(customerService.getCustomerById(98L)).thenReturn(customer);
		RequestBuilder request = MockMvcRequestBuilders.get(
				"/customer/98")
				.header(HttpHeaders.AUTHORIZATION,
						jwt);
		 mockMvc.perform(request)
		.andExpect(status().is(200))
		.andExpect(jsonPath("id", is(98)))
		.andExpect(jsonPath("name", is("Ketan")))
		.andExpect(jsonPath("mobile", is("8798797272")))
		.andExpect(jsonPath("address", is("Belgaum")))
		.andReturn();
	}
	
	@Test
	public void testGetCustomerByIdException() throws Exception {
		
		Customer customer = new Customer();
		customer.setAddress("Belgaum");
		customer.setName("Ketan");
		customer.setMobile("8798797272");
		customer.setId(98L);
		when(customerService.getCustomerById(98L)).thenThrow(new CustomerException("Not Found"));
		RequestBuilder request = MockMvcRequestBuilders.get(
				"/customer/98")
				.header(HttpHeaders.AUTHORIZATION,
						jwt);
		 mockMvc.perform(request)
		.andExpect(status().is(500))
		.andExpect(content().string("Not Found"))
		.andReturn();
	}
	
	@Test
	public void testUpdateCustomer() throws Exception {
		CustomerDto requestDto = new CustomerDto();
		requestDto.setCustomerId(98L);
		requestDto.setName("Ketan Hulaji");
		requestDto.setAddress("Belgaum");
		requestDto.setMobile("7974792787");
		
		Customer customer = new Customer();
		customer.setAddress(requestDto.getAddress());
		customer.setName(requestDto.getName());
		customer.setMobile(requestDto.getMobile());
		customer.setId(98L);
		when(customerService.updateCustomer(any(CustomerDto.class))).thenReturn(customer);
		RequestBuilder request = MockMvcRequestBuilders.put(
				"/customer")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString((requestDto)))
				.header(HttpHeaders.AUTHORIZATION,
						jwt);
		 mockMvc.perform(request)
			.andExpect(status().is(200))
			.andExpect(jsonPath("id", is(98)))
			.andExpect(jsonPath("name", is("Ketan Hulaji")))
			.andExpect(jsonPath("mobile", is("7974792787")))
			.andExpect(jsonPath("address", is("Belgaum")))
			.andReturn();
	}
	
	@Test
	public void testDisableCustomer() throws Exception {
		Customer customer = new Customer();
		customer.setAddress("Belgaum");
		customer.setName("Ketan");
		customer.setMobile("8798797272");
		customer.setId(98L);
		when(customerService.disableCustomer(98L)).thenReturn("Customer disabled successfully");
		RequestBuilder request = MockMvcRequestBuilders.get(
				"/customer/disable/98")
				.header(HttpHeaders.AUTHORIZATION,
						jwt);
		 mockMvc.perform(request)
		.andExpect(status().is(200))
		.andExpect(content().string("Customer disabled successfully"))
		.andReturn();
	}

}
