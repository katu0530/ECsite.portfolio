package com.example.app.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Item {
	
	private Integer id;
	
	@NotBlank
	@Size(max=10)
	private String item_name;
	
	@NotBlank
	private Integer price;
	
	private String item_introduction;
	

}
