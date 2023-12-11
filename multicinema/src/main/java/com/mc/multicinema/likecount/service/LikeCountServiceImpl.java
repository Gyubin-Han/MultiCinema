package com.mc.multicinema.likecount.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mc.multicinema.likecount.dao.LikeCountDAO;

/**
 * 
 * @author JIN
 * 좋아요 테이블들을 컨트롤하는 서비스
 * 조회(select), 추가(insert), 취소(delete) 기능을 지원한다.
 * keys[0] = user_key, keys[1] = xxx_key
 * 
 */
@Service
public class LikeCountServiceImpl implements LikeCountService {

	@Autowired
	LikeCountDAO dao;

	@Override
	public boolean selectLikeBoard(HashMap<String, Integer> keys) {
		return dao.selectLikeBoard(keys);
	}

	@Override
	public boolean selectLikeReply(HashMap<String, Integer> keys) {
		return dao.selectLikeReply(keys);
	}

	@Override
	public boolean selectLikeComment(HashMap<String, Integer> keys) {
		return dao.selectLikeComment(keys);
	}

	@Override
	public boolean selectDislikeBoard(HashMap<String, Integer> keys) {
		return dao.selectDislikeBoard(keys);
	}

	@Override
	public boolean selectDislikeReply(HashMap<String, Integer> keys) {
		return dao.selectDislikeReply(keys);
	}

	@Override
	public String insertLikeBoard(HashMap<String, Integer> keys) {
		if(dao.selectLikeBoard(keys))
			return "이미 좋아요를 누르셨습니다.";
		else if(dao.selectDislikeBoard(keys))
			return "이미 싫어요를 누르셨습니다.";
		
		dao.insertLikeBoard(keys);
		return "좋아요를 누르셨습니다.";
	}

	@Override
	public String insertLikeReply(HashMap<String, Integer> keys) {
		if(dao.selectLikeReply(keys))
			return "이미 좋아요를 누르셨습니다.";
		else if(dao.selectDislikeReply(keys))
			return "이미 싫어요를 누르셨습니다.";
		
		dao.insertLikeReply(keys);
		return "좋아요를 누르셨습니다.";
	}

	@Override
	public String insertLikeComment(HashMap<String, Integer> keys) {
		if(dao.selectLikeComment(keys))
			return "이미 좋아요를 누르셨습니다.";
		
		dao.insertLikeComment(keys);
		return "좋아요를 누르셨습니다.";
	}

	@Override
	public String insertDislikeBoard(HashMap<String, Integer> keys) {
		if(dao.selectLikeBoard(keys))
			return "이미 좋아요를 누르셨습니다.";
		else if(dao.selectDislikeBoard(keys))
			return "이미 싫어요를 누르셨습니다.";
		
		dao.insertLikeBoard(keys);
		return "싫어요를 누르셨습니다.";
	}

	@Override
	public String insertDislikeReply(HashMap<String, Integer> keys) {
		if(dao.selectLikeReply(keys))
			return "이미 좋아요를 누르셨습니다.";
		else if(dao.selectDislikeReply(keys))
			return "이미 싫어요를 누르셨습니다.";
		
		dao.insertLikeReply(keys);
		return "싫어요를 누르셨습니다.";
	}

	@Override
	public String deleteLikeBoard(HashMap<String, Integer> keys) {
		dao.deleteLikeBoard(keys);
		return "좋아요를 취소하셨습니다.";
	
	}

	@Override
	public String deleteLikeReply(HashMap<String, Integer> keys) {
		dao.deleteLikeReply(keys);
		return "좋아요를 취소하셨습니다.";
	}

	@Override
	public String deleteLikeComment(HashMap<String, Integer> keys) {
		dao.deleteLikeComment(keys);
		return "좋아요를 취소하셨습니다.";
	}

	@Override
	public String deleteDislikeBoard(HashMap<String, Integer> keys) {
		dao.deleteDislikeBoard(keys);
		return "싫어요를 취소하셨습니다.";
	}

	@Override
	public String deleteDislikeReply(HashMap<String, Integer> keys) {
		dao.deleteDislikeReply(keys);
		return "싫어요를 취소하셨습니다.";
	}
	
	
	
}
