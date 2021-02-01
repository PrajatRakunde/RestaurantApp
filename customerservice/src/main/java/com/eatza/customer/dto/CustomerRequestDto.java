package com.eatza.customer.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class CustomerRequestDto {
	
	@NotBlank(message = "Name cannot be null or blank")
	@Pattern(regexp = "^[a-z A-Z ]*$", message = "Name cannot contain special characters")
	private String name;
	
	
	@NotBlank(message = "Mobile cannot be null or blank")
	@Pattern(regexp = "^[7-9]{1}[0-9]{9}$", message = "Invalid mobile number, mobile number must be 10 digits long")
	private String mobile;
	
	@NotBlank(message = "Adress cannot be null or blank")
	@Pattern(regexp = "[a-z A-Z //\n]*$", message = "Invalid address")
	private String address;

}
