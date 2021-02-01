package com.eatza.customer.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name= "customers")
@Getter @Setter @NoArgsConstructor
public class Customer {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String mobile;
	private String address;
	private String status;
	
	
	
	@CreationTimestamp
    private LocalDateTime createDateTime;
    @UpdateTimestamp
    private LocalDateTime updateDateTime;
    
    public Customer(String name , String mobile , String address, String status){
    	this.name = name;
    	this.mobile = mobile;
    	this.address = address;
    	this.status = status;
    }

}
