package com.mc.multicinema.user.service;

import java.util.List;

import com.mc.multicinema.user.dto.UserDTO;

/**
 * 
 * @author JIN
 * 전체 유저 가져오는 리스트, 한 개의 유저 가져오는 셀렉트 작성
 *
 */
public interface UserService {
	
	List<UserDTO> selectUserAll();
	
	UserDTO selectUserOne(int user_key);
}
