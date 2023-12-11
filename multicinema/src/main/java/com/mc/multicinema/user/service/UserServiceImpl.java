package com.mc.multicinema.user.service;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mc.multicinema.moviehistory.MovieHistoryDTO;
import com.mc.multicinema.movieinfo.dto.MovieDTO;
import com.mc.multicinema.reviewboard.dto.ReviewBoardDTO;
import com.mc.multicinema.reviewhistory.ReviewHistoryDTO;

import java.util.HashMap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.mc.multicinema.user.dao.UserDAO;
import com.mc.multicinema.user.dto.UserDTO;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDAO userdao;
	
	@Override
	public UserDTO loginProcess(String user_id, String user_pw) {
		System.out.println("=====================ser"+user_id + user_pw+"=====================");
	
		return userdao.loginProcess(user_id, user_pw);
	}

	@Override
	public List<UserDTO> memberCheckProcess(String user_id, String user_email) {
		
		return userdao.memberCheckProcess(user_id, user_email);
	}

	@Override
	public int memberJoinProcess(UserDTO dto) {
		
		return userdao.memberJoinProcess(dto);
	}
	
	@Override
	public List<UserDTO> selectUserAll() {
		return userdao.selectList();
	}

	@Override
	// 로그인 된 사용자가 비밀번호를 한번 더 입력했을때 맞나 확인
	public boolean authentication(String user_pw, HttpSession session) {
		
		String loginUserKey =(String) session.getAttribute("login_user_key");
		UserDTO loginUser = userdao.getUserByUserKey(loginUserKey);
		String userPw = userdao.getPassword(loginUser);
		System.out.println(user_pw);
		System.out.println(userPw);
		if (user_pw.equals(userPw)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	// 이메일 변경
	@Override
	public void changeUserEmail(UserDTO dto, String user_email) {
		userdao.changeUserEmail(dto, user_email);
	}
	// 비밀번호 변경
	@Override
	public void changeUserPw(UserDTO dto, String user_pw) {
		userdao.changeUserPw(dto, user_pw);
	}
	// 유저 키로 유저 DTO 가져오기
	@Override
	public UserDTO getUserByUserKey(String login_user_key) {
		return userdao.getUserByUserKey(login_user_key);
	}
	
	// 시청한 movieHistory DTO 객체 가져오기
	@Override
	public List<MovieHistoryDTO> movieList(String login_user_key, int[] limit) {
		return userdao.getMovieList(login_user_key,limit);
		
	}
	// 시청한 전체 영화 수
	@Override
	public int getTotalMovieBoard() {
		
		return userdao.getTotalMovieBoard();
		
	}

	@Override
	public List<ReviewHistoryDTO> reviewBoardList(String login_user_key,int[] limit) {
		return userdao.getReviewBoardList(login_user_key,limit);
	}

	@Override
	public int getTotalReviewBoard() {
		return userdao.getTotalReviewBoard();
	}


	public UserDTO selectUserOne(int user_key) {
		return userdao.selectUserOne(user_key);
	}
	

}
