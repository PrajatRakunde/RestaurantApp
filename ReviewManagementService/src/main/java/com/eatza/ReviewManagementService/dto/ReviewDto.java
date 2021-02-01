package com.eatza.ReviewManagementService.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


import org.hibernate.validator.constraints.Range;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ReviewDto {

	@Range(min=0, max=Long.MAX_VALUE , message = "Invalid review id")
	private long id;
	
	@Range(min=0, max=Long.MAX_VALUE , message = "Invalid restaurant id")
	private long restaurantId;
	
	@Range(min=0, max=Long.MAX_VALUE , message = "Invalid customer id")
	private long customerId;
	
	@NotBlank(message = "Comments cannot be null or blank")
	@Pattern(regexp = "^[a-z A-Z ]*$", message = "Name cannot contain special characters")
	private String comments;
	
	@Pattern(regexp = "[0-5]", message = "rating is a required field and should not be null")
	private int rating;
	
}
