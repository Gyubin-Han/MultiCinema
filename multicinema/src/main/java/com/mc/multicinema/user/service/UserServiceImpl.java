package com.mc.multicinema.user.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mc.multicinema.movieinfo.dto.MovieDTO;
import com.mc.multicinema.reviewboard.dto.ReviewBoardDTO;
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
	// 로그인 된 사용자가 비밀번호를 한번 더 입력했을때 맞나 확인
	public boolean authentication(String pw, HttpSession session) {
		
		UserDTO loginUser = (UserDTO) session.getAttribute("login_user_key");
		String userPw = userdao.getPassword(loginUser);
		
		if (pw == userPw) {
			return true;
		}
		else {
			return false;
		}
	}


	@Override
	public List<ReviewBoardDTO> loadReviewBoardHistory(UserDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changeUserEmail(UserDTO dto, String user_email) {
		userdao.changeUserEmail(dto, user_email);
	}

	@Override
	public void changeUserPw(UserDTO dto, String user_pw) {
		userdao.changeUserPw(dto, user_pw);
	}

	@Override
	public UserDTO getUserByUserKey(String login_user_key) {
		return userdao.getUserByUserKey(login_user_key);
	}

	@Override
	public List<MovieDTO> movieList(int[] limit) {
		return userdao.getMovieList(limit);
		
	}

	@Override
	public int getTotalMovieBoard() {
		// TODO Auto-generated method stub
		return 0;
	}

}
