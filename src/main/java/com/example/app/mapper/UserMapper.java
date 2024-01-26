package com.example.app.mapper;

import com.example.app.domain.User;

public interface UserMapper {
	
	User selectByLoginId(String userLoginId) throws Exception;
	User selectByUserId(Integer userId) throws Exception;
	void insert(User user) throws Exception;
	void update(User user) throws Exception;


}
