package com.eatza.customer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import com.eatza.customer.config.JwtFilter;

@SpringBootApplication
@EnableEurekaClient
public class CustomerserviceApplication {

	
	@Value("${spring.cloud.config.uri}")
	String configserverUrl;
	
	public static void main(String[] args) {
		SpringApplication.run(CustomerserviceApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean jwtFilterBean() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/customer/*");

		return registrationBean;
	}


}
