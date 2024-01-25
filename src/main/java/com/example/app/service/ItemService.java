package com.example.app.service;

import java.util.List;

import com.example.app.domain.Item;

public interface ItemService {
	
	List<Item> getItemList() throws Exception;
	List<Item> getItemListPart() throws Exception;
	Item getItemById(Integer id) throws Exception;
	void addItem(Item item) throws Exception;
	void editItem(Item item) throws Exception;
	void deleteItem(Integer id) throws Exception;

}
