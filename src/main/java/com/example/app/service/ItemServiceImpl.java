package com.example.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.domain.Admin;
import com.example.app.domain.Item;
import com.example.app.mapper.AdminMapper;
import com.example.app.mapper.ItemMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
	
	private final ItemMapper itemMapper;
	private final AdminMapper adminMapper;

	@Override
	public List<Item> getItemList() throws Exception {
		return itemMapper.selectAll();
	}

	@Override
	public Item getItemById(Integer id) throws Exception {
		return itemMapper.selectById(id);
	}

	@Override
	public void addItem(Item item) throws Exception {
		itemMapper.insert(item);		
	}

	@Override
	public void editItem(Item item) throws Exception {
		itemMapper.update(item);
	}

	@Override
	public void deleteItem(Integer id) throws Exception {
		itemMapper.delete(id);
	}

	@Override
	public List<Admin> getAdminList() throws Exception {
		return adminMapper.selectAll();
		
	}
	
	

}
