package com.example.app.domain;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class User {
	
	private Integer id;
	
	@NotBlank
	private String loginId;
	
	@NotBlank
	private String loginPass;
	
	private String email;
	
	@Size(max=10)
	private String userName;
	
	private String addressNumber;
	
	@Size(max=255)
	private String address;
	
	private String phoneNumber;
	
	@Range(min=0, max=120)
	private Integer age;

}
