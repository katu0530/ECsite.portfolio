package com.example.app.service;

import com.example.app.domain.User;

public interface UserService {
	
	boolean isCorrectUserIdAndPassword(String userLoginId, String userLoginPass) throws Exception;
	User getUser();
	User getUserById(Integer userId) throws Exception;
	void addUser(User user) throws Exception;
	void editUser(User user) throws Exception;
	
}
