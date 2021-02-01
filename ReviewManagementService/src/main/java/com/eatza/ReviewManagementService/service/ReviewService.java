package com.eatza.ReviewManagementService.service;

import java.util.List;

import com.eatza.ReviewManagementService.Exception.ReviewExcepton;
import com.eatza.ReviewManagementService.dto.ReviewDto;
import com.eatza.ReviewManagementService.model.Review;

public interface ReviewService {

	Review addReview(ReviewDto reviewDto);

	Review editReview(ReviewDto reviewDto) throws ReviewExcepton;

	List<Review> getReviews() throws ReviewExcepton;

}
