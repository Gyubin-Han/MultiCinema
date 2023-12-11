package com.mc.multicinema.user.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.mc.multicinema.movieinfo.dto.MovieDTO;
import com.mc.multicinema.reviewboard.dto.ReviewBoardDTO;
import com.mc.multicinema.user.dto.UserDTO;


public interface UserService {
	
	UserDTO loginProcess(String user_id, String user_pw);

	List<UserDTO> memberCheckProcess(String user_id, String user_email);

	int memberJoinProcess(UserDTO dto);
	
	//로그인한 본인인지 자기확인
	boolean authentication(String pw, HttpSession session);
	
	//회원 이메일 수정하기
	void changeUserEmail(UserDTO dto, String user_email);
	
	//회원 비밀번호 수정하기
	void changeUserPw(UserDTO dto, String user_pw);
	
	// 유저 키로 유저 객체 가져오기
	UserDTO getUserByUserKey(String login_user_key);
	
	//본인의 영화시청 목록 불러오기
	List<MovieDTO> movieList(int[] limit);
	
	//본인의 게시글 목록 불러오기
	List<ReviewBoardDTO> loadReviewBoardHistory(UserDTO dto);

	int getTotalMovieBoard();



	

	
	
	
	
	
	
	
	
	
		
}
