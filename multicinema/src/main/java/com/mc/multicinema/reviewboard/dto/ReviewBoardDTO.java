package com.mc.multicinema.reviewboard.dto;

import org.springframework.stereotype.Component;

/**
 * 
 * @author JIN
 * 23-12-08 15:36 board_view_count, is_deleted, deleted_date
 * 필드 게터 세터 추가 완료 
 *
 */
@Component
public class ReviewBoardDTO {
	
	int board_num;
	int user_key;
	int movie_cd;
	String board_write_date;
	String board_title;
	String board_content;
	String board_update_date;
	int board_like_count;
	int board_dislike_count;
	int board_comment_count;
	
	String movie_title;

	int board_view_count;
	boolean is_deleted;
	String deleted_date;
	
	public String getMovie_title() {
		return movie_title;
	}
	public void setMovie_title(String movie_title) {
		this.movie_title = movie_title;
	}
	public int getBoard_view_count() {
		return board_view_count;
	}
	public void setBoard_view_count(int board_view_count) {
		this.board_view_count = board_view_count;
	}
	public boolean isIs_deleted() {
		return is_deleted;
	}
	public void setIs_deleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
	}
	public String getDeleted_date() {
		return deleted_date;
	}
	public void setDeleted_date(String deleted_date) {
		this.deleted_date = deleted_date;
	}
	public int getBoard_num() {
		return board_num;
	}
	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}
	public int getUser_key() {
		return user_key;
	}
	public void setUser_key(int user_key) {
		this.user_key = user_key;
	}
	public int getMovie_cd() {
		return movie_cd;
	}
	public void setMovie_cd(int movie_cd) {
		this.movie_cd = movie_cd;
	}
	public String getBoard_write_date() {
		return board_write_date;
	}
	public void setBoard_write_date(String board_write_date) {
		this.board_write_date = board_write_date;
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
	public String getBoard_update_date() {
		return board_update_date;
	}
	public void setBoard_update_date(String board_update_date) {
		this.board_update_date = board_update_date;
	}
	public int getBoard_like_count() {
		return board_like_count;
	}
	public void setBoard_like_count(int board_like_count) {
		this.board_like_count = board_like_count;
	}
	public int getBoard_dislike_count() {
		return board_dislike_count;
	}
	public void setBoard_dislike_count(int board_dislike_count) {
		this.board_dislike_count = board_dislike_count;
	}
	public int getBoard_comment_count() {
		return board_comment_count;
	}
	public void setBoard_comment_count(int board_comment_count) {
		this.board_comment_count = board_comment_count;
	}
	
	@Override
	public String toString() {
		return "ReviewBoardDTO [board_num=" + board_num + ", user_key=" + user_key + ", movie_cd=" + movie_cd
				+ ", board_write_date=" + board_write_date + ", board_title=" + board_title + ", board_content="
				+ board_content + ", board_update_date=" + board_update_date + ", board_like_count=" + board_like_count
				+ ", board_dislike_count=" + board_dislike_count + ", board_comment_count=" + board_comment_count
				+ ", board_view_count=" + board_view_count + ", is_deleted=" + is_deleted + ", deleted_date="
				+ deleted_date + "]";
	}
	
}
