package com.example.app.mapper;

import java.util.List;

import com.example.app.domain.Item;

public interface TopMapper {
	
	List<Item> selectPart() throws Exception;

}
