package com.mc.multicinema.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mc.multicinema.user.dto.UserDTO;
import com.mc.multicinema.user.service.UserService;


/**
 * 
 * @author JIN
 * 회원 키 가지고 유저 닉네임 가져오는 sql 작성 
 *
 */
@Controller
public class UserController {
	
	@Autowired
	UserService service;
	
	List<UserDTO> selectUserAll() {
		return service.selectUserAll();
	}
	
	
}
