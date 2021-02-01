package com.eatza.ReviewManagementService.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eatza.ReviewManagementService.Exception.ReviewExcepton;
import com.eatza.ReviewManagementService.dto.ReviewDto;
import com.eatza.ReviewManagementService.model.Review;
import com.eatza.ReviewManagementService.repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService{
	
	@Autowired
	ReviewRepository repo;
	
	private static Logger logger = LoggerFactory.getLogger(ReviewServiceImpl.class);

	@Override
	public Review addReview(ReviewDto reviewDto) {
		
		logger.debug("entering method addReview, create review object to persist");
		
		
		Review review = new Review(reviewDto.getId(), 
				reviewDto.getRestaurantId(), 
				reviewDto.getCustomerId(), 
				reviewDto.getComments(), 
				reviewDto.getRating());
		logger.debug("calling repository save review");
		repo.save(review);
		logger.debug("returning review");
		return review;
		
	}

	@Override
	public Review editReview(ReviewDto reviewDto) throws ReviewExcepton {
		
		logger.debug("entering method edit review, update review object to persist");
		Optional<Review> prevReview = repo.findById(reviewDto.getId());
		
		if(!prevReview.isPresent()) {
			
			throw new ReviewExcepton("the review with the given id does not exist");
			
		}
		
		if(prevReview.get().getCustomerId() != reviewDto.getCustomerId() || 
				prevReview.get().getRestaurantId()!= reviewDto.getRestaurantId()) {
			throw new ReviewExcepton("the customer or restaurant cannot be changed");
		}
		
		Review updatedReview = new Review(reviewDto.getId(), 
				reviewDto.getRestaurantId(), 
				reviewDto.getCustomerId(), 
				reviewDto.getComments(), 
				reviewDto.getRating());
		
		updatedReview.setCreateDateTime(prevReview.get().getCreateDateTime());
		
		logger.debug("calling repository save review");
		repo.save(updatedReview);
		
		logger.debug("returning review");
		return updatedReview;
		
	}

	@Override
	public List<Review> getReviews() throws ReviewExcepton {
		
		List<Review> reviews = repo.findAll();
		
		if(reviews.size() < 1) {
			
			throw new ReviewExcepton("No reviews availble");
			
		}
		
		return reviews;
		
	}

	
	
}
