package com.mc.multicinema.user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mc.multicinema.user.dto.UserDTO;

@Repository
public class UserDao {

	@Autowired
	SqlSession session;

	public List<UserDTO> selectList() {
		return session.selectList("user.selectUserAll");
	}

	public UserDTO selectUserOne(int user_key) {
		return session.selectOne("user.selectUserOne", user_key);
	}
	
	
}
