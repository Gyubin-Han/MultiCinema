package com.mc.multicinema.reviewboard.dto;

import org.springframework.stereotype.Component;

@Component
public class WritenReviewDTO {
	
	String board_title;
	String board_content;
	int movie_cd;
	int user_key;
	int board_num;
	
	public int getBoard_num() {
		return board_num;
	}
	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public int getMovie_cd() {
		return movie_cd;
	}
	public void setMovie_cd(int movie_cd) {
		this.movie_cd = movie_cd;
	}
	public int getUser_key() {
		return user_key;
	}
	public void setUser_key(int user_key) {
		this.user_key = user_key;
	}
	
	@Override
	public String toString() {
		return "WritenReviewDTO [board_title=" + board_title + ", board_content=" + board_content + ", movie_cd="
				+ movie_cd + ", user_key=" + user_key + "]";
	}
	
	
	
}
