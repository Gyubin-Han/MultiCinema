package com.mc.multicinema.reviewboard.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mc.multicinema.reviewboard.dto.ReviewBoardDTO;
import com.mc.multicinema.reviewboard.dto.SearchParameterDTO;
import com.mc.multicinema.reviewboard.dto.WritenReviewDTO;

@Repository
public class ReviewBoardDAO {

	@Autowired
	SqlSession session;
	
	public List<ReviewBoardDTO> selectReviewBoard(ArrayList<Integer> paging) {
		System.out.println("ReviewBoardDAO : selectReviewBoard called");
		return session.selectList("reviewboard.selectReviewBoard", paging);
	}

	public int countAllReview() {
		System.out.println("ReviewBoardDAO : countAllReview called");
		return session.selectOne("reviewboard.countAllReview");
	}

	public ReviewBoardDTO selectReviewBoard(int board_num) {
		System.out.println("ReviewBoardDAO : selectReviewBoard called");
		return session.selectOne("reviewboard.selectOneReviewBoard", board_num);
	}

	public void updateViewCount(int board_num) {
		System.out.println("ReviewBoardDAO : updateViewCount called");
		session.update("updateViewCount", board_num);
	}

	public int countSearchReview(SearchParameterDTO dto) {
		System.out.println("ReviewBoardDAO : countSearchReview called");
		System.out.println(dto);
		return session.selectOne("countSearchReview", dto);
	}

	public List<ReviewBoardDTO> selectSearchBoard(SearchParameterDTO dto) {
		System.out.println("ReviewBoardDAO : selectSearchBoard called");
		System.out.println(dto);
		return session.selectList("selectSearchBoard", dto);
	}

	public void insertReviewBoard(WritenReviewDTO form) {
		session.insert("insertReviewBoard", form);
		
	}

	public void updateReviewBoard(WritenReviewDTO form) {
		session.update("updateReviewBoard", form);
	}

	public void updateDeleteBoard(int board_num) {
		session.update("deleteBoard", board_num);
	}

	public void minusLike(int board_num) {
		session.update("minusLike", board_num);
	}

	public void plusLike(int board_num) {
		session.update("plusLike", board_num);
	}

}
