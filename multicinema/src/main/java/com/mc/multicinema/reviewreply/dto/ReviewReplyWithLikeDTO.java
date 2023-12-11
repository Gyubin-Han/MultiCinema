package com.mc.multicinema.reviewreply.dto;

import org.springframework.stereotype.Component;

@Component
public class ReviewReplyWithLikeDTO extends ReviewReplyDTO {
	
	boolean user_like;
	boolean user_dislike;
	
	public boolean isUser_like() {
		return user_like;
	}
	public void setUser_like(boolean user_like) {
		this.user_like = user_like;
	}
	public boolean isUser_dislike() {
		return user_dislike;
	}
	public void setUser_dislike(boolean user_dislike) {
		this.user_dislike = user_dislike;
	}
	
}
