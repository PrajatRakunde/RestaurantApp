package com.eatza.ReviewManagementService.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Review {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	
	private long restaurantId;
	
	private long customerId;
	
	private String comments;
	
	private int rating;
	
	@CreationTimestamp
    private LocalDateTime createDateTime;
	
    @UpdateTimestamp
    private LocalDateTime updateDateTime;
    
	public Review(long id, long restaurantId, long customerId, String comments, int rating) {
		super();
		this.id = id;
		this.restaurantId = restaurantId;
		this.customerId = customerId;
		this.comments = comments;
		this.rating = rating;
	}
    
    
	
	
	
}
