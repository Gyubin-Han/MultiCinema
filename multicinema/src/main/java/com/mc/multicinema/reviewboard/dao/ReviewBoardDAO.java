package com.mc.multicinema.reviewboard.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mc.multicinema.reviewboard.dto.ReviewBoardDTO;

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

}
