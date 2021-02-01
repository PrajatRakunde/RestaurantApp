package com.eatza.ReviewManagementService.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.eatza.ReviewManagementService.Exception.ReviewExcepton;
import com.eatza.ReviewManagementService.dto.ReviewDto;
import com.eatza.ReviewManagementService.model.Review;
import com.eatza.ReviewManagementService.repository.ReviewRepository;
import com.eatza.ReviewManagementService.service.ReviewService;

@RestController
public class ReviewController {

	@Autowired
	ReviewService reviewService;

	@Autowired
	private KafkaTemplate<String, Review> kafkaTemplate;

	private static Logger logger = LoggerFactory.getLogger(ReviewController.class);

	private static final String TOPIC = "review";

	@PostMapping("/review")
	public ResponseEntity<Review> addReview(@RequestHeader String authentication, @RequestBody ReviewDto reviewDto)
			throws ReviewExcepton {

		logger.debug("Add reveiw method, calling the service");
		Review review = reviewService.addReview(reviewDto);
		logger.debug("Customer added Successfully");

		kafkaTemplate.send(TOPIC, review);

		return ResponseEntity.status(HttpStatus.OK).body(review);

	}

	@PutMapping("/review")
	public ResponseEntity<Review> editReview(@RequestHeader String authentication, @RequestBody ReviewDto reviewDto)
			throws ReviewExcepton {

		logger.debug("Edit reveiw method, calling the service");
		Review review = reviewService.editReview(reviewDto);
		logger.debug("Customer updated Successfully");
		kafkaTemplate.send(TOPIC, review);
		return ResponseEntity.status(HttpStatus.OK).body(review);

	}

	@GetMapping("/reviews")
	public List<Review> getReviews() throws ReviewExcepton {

		List<Review> reviews = reviewService.getReviews();
		
		return reviewService.getReviews();

	}

}
