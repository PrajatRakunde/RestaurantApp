package com.eatza.api.gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class FallbackController {
	
	@RequestMapping("/orderFallback")
	public Mono<String> orderServiceFallback(){
		return Mono.just("Order service is taking too long to respond or is down. Please try again later");
	}
	
	
	@RequestMapping("/restaurantFallback")
	public Mono<String> restaurantServiceFallback(){
		return Mono.just("Restaurant service is taking too long to respond or is down. Please try again later");
	}

}
