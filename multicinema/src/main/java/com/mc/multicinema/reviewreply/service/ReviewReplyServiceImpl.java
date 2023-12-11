package com.mc.multicinema.reviewreply.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mc.multicinema.reviewreply.dao.ReviewReplyDAO;
import com.mc.multicinema.reviewreply.dto.ReviewReplyDTO;
import com.mc.multicinema.reviewreply.dto.ReviewReplyWithLikeDTO;

@Service
public class ReviewReplyServiceImpl implements ReviewReplyService {

	@Autowired
	ReviewReplyDAO dao;
	
	@Override
	public ReviewReplyDTO selectReplyOne(int reply_num) {
		return dao.selectReplyOne(reply_num);
	}

	@Override
	public List<ReviewReplyDTO> selectReplyList(HashMap<String, String> map) {
		return dao.selectReplyList(map);
	}

	public List<ReviewReplyDTO> selectReplyList(String search, String keyword) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("search", search);
		map.put("keyword", keyword);
		return selectReplyList(map);
	}
	
	@Override
	public List<ReviewReplyWithLikeDTO> selectReplyLikeList(HashMap<String, String> map) {
		return dao.selectReplyLikeList(map);
	}

	public List<ReviewReplyWithLikeDTO> selectReplyLikeList(String user_key, String reply_num) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("user_key", user_key);
		map.put("reply_num", reply_num);
		return selectReplyLikeList(map);
	}
}
