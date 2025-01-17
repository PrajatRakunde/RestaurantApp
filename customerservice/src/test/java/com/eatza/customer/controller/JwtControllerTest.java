package com.eatza.customer.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.eatza.customer.dto.UserDto;
import com.eatza.customer.service.authenticationservice.JwtAuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value= JwtAuthenticationController.class)
public class JwtControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	JwtAuthenticationService authenticationService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	public void enroll() throws Exception {
		ReflectionTestUtils.setField(authenticationService, "username","username");
		ReflectionTestUtils.setField(authenticationService, "password","password");
		UserDto dto = new UserDto();
		dto.setPassword("password");
		dto.setUsername("username");
		when(authenticationService.authenticateUser(any(UserDto.class))).thenCallRealMethod();
		RequestBuilder request = MockMvcRequestBuilders
				.post("/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString((dto)));

		// response
		mockMvc.perform(request)
		.andExpect(status().is(200))
		.andReturn();
		
	}
	
	@Test
	public void testAuthenticationException() throws Exception {
		ReflectionTestUtils.setField(authenticationService, "username","username");
		ReflectionTestUtils.setField(authenticationService, "password","passord");
		UserDto dto = new UserDto();
		dto.setPassword("password");
		dto.setUsername("username");
		when(authenticationService.authenticateUser(any(UserDto.class))).thenCallRealMethod();
		RequestBuilder request = MockMvcRequestBuilders
				.post("/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString((dto)));

		// response
		mockMvc.perform(request)
		.andExpect(status().is(401))
		.andReturn();
		
	}


}
