package com.eatza.ReviewManagementService.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.when;

import com.eatza.ReviewManagementService.dto.ReviewDto;
import com.eatza.ReviewManagementService.model.Review;
import com.eatza.ReviewManagementService.repository.ReviewRepository;

class ReviewServiceImplTest {

	@Mock
	ReviewRepository repo;
	
	@Mock
	ReviewService reviewService;
	
	@Test
	public void addReview() {
		
		ReviewDto reviewDto = new ReviewDto();
		reviewDto.setId(1);
		reviewDto.setCustomerId(1);
		reviewDto.setComments("test comments");
		reviewDto.setRating(4);
		
		
		Review review = new Review(reviewDto.getId(), reviewDto.getRestaurantId(), reviewDto.getCustomerId(), reviewDto.getComments(), reviewDto.getRating());
		
		
		when(repo.save(any(Review.class))).thenReturn(review);
		
		Review persistedReview = reviewService.addReview(reviewDto);
		
		assertNotNull(persistedReview);
		assertEquals(1,persistedReview.getId());
		assertEquals(1,persistedReview.getCustomerId());
		assertEquals("test comments",persistedReview.getComments());
		assertEquals(4,persistedReview.getRating());

		
	}
	

}
