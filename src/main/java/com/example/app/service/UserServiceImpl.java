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
	
	private User user;

	@Override
	public boolean isCorrectUserIdAndPassword(String userLoginId, String userLoginPass) throws Exception {
		User user = userMapper.selectByLoginId(userLoginId);
		
		if(user == null) {
			return false;
		}
		
		if(!BCrypt.checkpw(userLoginPass, user.getLoginPass())) {
			return false;
		}
		
		// ログインIDとパスワードが正しい場合、ユーザー情報を保存
		this.user = user;
		
		return true;
	}

	@Override
	public User getUser() {
		User user = this.user;
		this.user = null;
		return user;
	}
	
	@Override
	public User getUserById(Integer id) throws Exception {
		return userMapper.selectByUserId(id);
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
