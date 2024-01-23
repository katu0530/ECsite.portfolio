package com.example.app.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Admin {
	
	private Integer id;
	
	@NotBlank
	private Integer loginId;
	
	private String loginPass;
	
}
