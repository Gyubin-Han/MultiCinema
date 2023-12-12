package com.mc.multicinema.reviewreply.dto;

import org.springframework.stereotype.Component;

@Component
public class WritenReplyDTO {
	
	int board_num; // nead
	int user_key; // nead
	String reply_content; // nead
	
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
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	
}
