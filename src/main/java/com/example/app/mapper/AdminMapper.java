package com.example.app.mapper;

import java.util.List;

import com.example.app.domain.Admin;

public interface AdminMapper {
	
	List<Admin> selectAll() throws Exception;

}
