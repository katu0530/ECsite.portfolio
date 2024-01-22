package com.example.app.domain;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Member {
	
	private Integer id;
	
	private Integer login_id;
	
	private String login_pass;
	
	@NotBlank
	@Size(max=10)
	private String name;
	
	private String address_number;
	
	@Size(max=255)
	private String address;
	
	private String phone_number;
	
	@Range(min=0, max=120)
	private Integer age;

}
