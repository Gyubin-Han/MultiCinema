package com.mc.multicinema.reviewboard.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mc.multicinema.reviewreply.dto.ReviewReplyWithLikeDTO;

@Component
public class ReviewDetailDTO {
	
	ReviewBoardDTO board;
	String movie_title;
	String user_name;
	String[] users_like_board;
	List<ReviewReplyWithLikeDTO> reply;
	
	public ReviewBoardDTO getBoard() {
		return board;
	}
	public void setBoard(ReviewBoardDTO board) {
		this.board = board;
	}
	public String getMovie_title() {
		return movie_title;
	}
	public void setMovie_title(String movie_title) {
		this.movie_title = movie_title;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String[] getUsers_like_board() {
		return users_like_board;
	}
	public void setUsers_like_board(String[] users_like_board) {
		this.users_like_board = users_like_board;
	}
	public List<ReviewReplyWithLikeDTO> getReply() {
		return reply;
	}
	public void setReply(List<ReviewReplyWithLikeDTO> reply) {
		this.reply = reply;
	}
	
	
	
}
