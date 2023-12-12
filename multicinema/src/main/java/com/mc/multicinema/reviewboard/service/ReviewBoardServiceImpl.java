package com.mc.multicinema.reviewboard.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mc.multicinema.reviewboard.dao.ReviewBoardDAO;
import com.mc.multicinema.reviewboard.dto.ReviewBoardDTO;
import com.mc.multicinema.reviewboard.dto.SearchParameterDTO;
import com.mc.multicinema.reviewboard.dto.WritenReviewDTO;

@Service
public class ReviewBoardServiceImpl implements ReviewBoardService {

	@Autowired
	ReviewBoardDAO dao;
	
	@Override
	public List<ReviewBoardDTO> selectReviewBoard(ArrayList<Integer> paging) {
		System.out.println("ReviewBoardService : selectReviewBoard called");
		return dao.selectReviewBoard(paging);
	}

	@Override
	public ReviewBoardDTO selectReviewBoard(int board_num) {
		System.out.println("ReviewBoardService : selectReviewBoard called");
		dao.updateViewCount(board_num);
		return dao.selectReviewBoard(board_num);
	}
	
	@Override
	public int countAllReview() {
		System.out.println("ReviewBoardService : countAllReview called");
		return dao.countAllReview();
	}

	@Override
	public int countSerchReview(SearchParameterDTO dto) {
		System.out.println("ReviewBoardService : countSearchReview called");
		return dao.countSearchReview(dto);
	}

	@Override
	public List<ReviewBoardDTO> selectReviewBoardSearch(SearchParameterDTO dto) {
		System.out.println("ReviewBoardService : selectReviewBoardSearch called");
		return dao.selectSearchBoard(dto);
	}

	@Override
	public void insertReviewBoard(WritenReviewDTO form) {
		dao.insertReviewBoard(form);
	}

	@Override
	public void updateReviewBoard(WritenReviewDTO form) {
		dao.updateReviewBoard(form);
	}

	@Override
	public void updateDeleteBoard(int board_num) {
		dao.updateDeleteBoard(board_num);
	}

	@Override
	public void minusLike(int board_num) {
		dao.minusLike(board_num);
	}

	@Override
	public void plusLike(int board_num) {
		dao.plusLike(board_num);
	}


}
