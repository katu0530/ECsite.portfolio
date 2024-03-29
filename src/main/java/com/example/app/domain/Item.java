package com.example.app.domain;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Item {
	
	private Integer id;
	
	@NotBlank
	@Size(max=10)
	private String itemName;
	
	@NotNull
	private Integer price;
	
	private String itemIntroduction;
	
	private MultipartFile upfile;
	
	private String photo;
	

}
