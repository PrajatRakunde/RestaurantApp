package com.eatza.restaurantsearch.dto;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter @Setter @NoArgsConstructor @ToString
public class ReviewDto {

	private long id;
	
	private long restaurantId;
	
	private long customerId;
	
	private String comments;
	
	private int rating;
	
	@CreationTimestamp
    private LocalDateTime createDateTime;
	
    @UpdateTimestamp
    private LocalDateTime updateDateTime;
    
	public ReviewDto(long id, long restaurantId, long customerId, String comments, int rating) {
		super();
		this.id = id;
		this.restaurantId = restaurantId;
		this.customerId = customerId;
		this.comments = comments;
		this.rating = rating;
	}
    
    
	
	
	
}
