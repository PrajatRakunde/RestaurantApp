//package com.eatza.restaurantsearch.config;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.messaging.handler.annotation.Payload;
//
//import com.eatza.restaurantsearch.dto.ReviewDto;
//
//@Configuration
//public class RestaurantKafkaListener {
//
//	@Autowired
//	private KafkaTemplate<String, String> kafkaTemplate;
//
//	private static Logger logger = LoggerFactory.getLogger(RestaurantKafkaListener.class);
//
//	private static final String TOPIC = "restaurant-notification";
//
//	
//	@KafkaListener(topics = "review", groupId = "reviews", containerFactory = "kafkaListenerContainerFactory")
//	public void consume(@Payload ReviewDto review) {
//		
//		kafkaTemplate.send(TOPIC, "A review from customerId: " + review.getCustomerId()+ " has been recieved with rating " + review.getRating());
//		
//	}
//	
//}
