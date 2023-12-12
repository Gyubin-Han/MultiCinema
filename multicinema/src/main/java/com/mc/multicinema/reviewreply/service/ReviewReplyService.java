package com.mc.multicinema.reviewreply.service;

import java.util.HashMap;
import java.util.List;

import com.mc.multicinema.reviewreply.dto.ReviewReplyDTO;
import com.mc.multicinema.reviewreply.dto.ReviewReplyWithLikeDTO;
import com.mc.multicinema.reviewreply.dto.WritenReplyDTO;

public interface ReviewReplyService {
	
	ReviewReplyDTO selectReplyOne(int reply_num);
	List<ReviewReplyDTO> selectReplyList(int board_num);
	List<ReviewReplyWithLikeDTO> selectReplyLikeList(HashMap<String, String> map);
	void insertReply(WritenReplyDTO reply);
	
	
}
