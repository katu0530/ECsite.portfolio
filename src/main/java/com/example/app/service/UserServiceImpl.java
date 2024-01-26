package com.example.app.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.domain.User;
import com.example.app.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserMapper userMapper;

	@Override
	public boolean isCorrectUserIdAndPassword(String userLoginId, String userLoginPass) throws Exception {
		User user = userMapper.selectByLoginId(userLoginId);
		
		if(user == null) {
			return false;
		}
		
		if(!BCrypt.checkpw(userLoginPass, user.getUserLoginPass())) {
			return false;
		}
		
		return true;
	}

	@Override
	public User getUserById(Integer userId) throws Exception {
		return userMapper.selectByUserId(userId);
	}

	@Override
	public void addUser(User user) throws Exception {
		userMapper.insert(user);
	}

	@Override
	public void editUser(User user) throws Exception {
		userMapper.update(user);
	}


}
