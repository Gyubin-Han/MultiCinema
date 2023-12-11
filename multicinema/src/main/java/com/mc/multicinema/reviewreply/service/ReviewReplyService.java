package com.mc.multicinema.reviewreply.service;

import java.util.HashMap;
import java.util.List;

import com.mc.multicinema.reviewreply.dto.ReviewReplyDTO;
import com.mc.multicinema.reviewreply.dto.ReviewReplyWithLikeDTO;

public interface ReviewReplyService {
	
	ReviewReplyDTO selectReplyOne(int reply_num);
	List<ReviewReplyDTO> selectReplyList(HashMap<String, String> map);
	List<ReviewReplyWithLikeDTO> selectReplyLikeList(HashMap<String, String> map);
	
	
}
