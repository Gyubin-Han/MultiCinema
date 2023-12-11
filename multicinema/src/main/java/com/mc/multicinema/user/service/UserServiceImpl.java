package com.mc.multicinema.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mc.multicinema.user.dao.UserDao_back;
import com.mc.multicinema.user.dto.UserDTO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao_back dao;
	
	@Override
	public List<UserDTO> selectUserAll() {
		return dao.selectList();
	}

	@Override
	public UserDTO selectUserOne(int user_key) {
		return dao.selectUserOne(user_key);
	}


}
