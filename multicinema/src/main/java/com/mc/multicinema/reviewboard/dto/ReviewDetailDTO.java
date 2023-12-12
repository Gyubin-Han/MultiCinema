package com.mc.multicinema.reviewboard.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mc.multicinema.reviewreply.dto.ReviewReplyDTO;
import com.mc.multicinema.reviewreply.dto.ReviewReplyWithLikeDTO;

@Component
public class ReviewDetailDTO extends ReviewBoardDTO {
	
	
	String[] users_like_board;
	List<ReviewReplyDTO> replys;
	
	public String[] getUsers_like_board() {
		return users_like_board;
	}
	public void setUsers_like_board(String[] users_like_board) {
		this.users_like_board = users_like_board;
	}
	public List<ReviewReplyDTO> getReply() {
		return replys;
	}
	public void setReply(List<ReviewReplyDTO> list) {
		this.replys = list;
	}
	
	
	
}
