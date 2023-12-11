package com.mc.multicinema.reviewboard.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mc.multicinema.reviewboard.dao.ReviewBoardDAO;
import com.mc.multicinema.reviewboard.dto.ReviewBoardDTO;

@Service
public class ReviewBoardServiceImpl implements ReviewBoardService {

	@Autowired
	ReviewBoardDAO dao;
	
	@Override
	public List<ReviewBoardDTO> selectReviewBoard(ArrayList<Integer> paging) {
		System.out.println("Service : selectReviewBoard called");
		return dao.selectReviewBoard(paging);
	}

	@Override
	public ReviewBoardDTO selectReviewBoard(int board_num) {
		System.out.println("Service : selectReviewBoard called");
		return dao.selectReviewBoard(board_num);
	}
	
	@Override
	public int countAllReview() {
		System.out.println("Service : countAllReview called");
		return dao.countAllReview();
	}


}
