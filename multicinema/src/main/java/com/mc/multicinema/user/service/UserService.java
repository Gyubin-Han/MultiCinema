package com.mc.multicinema.user.service;

import java.util.List;


import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.mc.multicinema.moviehistory.MovieHistoryDTO;
import com.mc.multicinema.movieinfo.dto.MovieDTO;
import com.mc.multicinema.reviewboard.dto.ReviewBoardDTO;
import com.mc.multicinema.reviewhistory.ReviewHistoryDTO;
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
	
	// 본인이 본 영화 목록 가져오기
	List<MovieHistoryDTO> movieList(int[] limit);
	
	// 전체 시청한 영화 개수 가져오기
	int getTotalMovieBoard();
	
	// 본인이 남긴 리뷰 목록 불러오기
	List<ReviewHistoryDTO> reviewBoardList(int[] limit);
	
	// 전체 남긴 리뷰 개수 가져오기
	int getTotalReviewBoard();

}


	

	
	
	
	
	
	
	
	
	
		
=======

>>>>>>> 7d77d6fa5ee4f8f208705b2d7d277e4eb8b9d420
}
