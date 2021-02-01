package com.eatza.ReviewManagementService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eatza.ReviewManagementService.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{

}
