package com.mc.multicinema.reviewboard.service;

import java.util.ArrayList;
import java.util.List;

import com.mc.multicinema.reviewboard.dto.ReviewBoardDTO;
import com.mc.multicinema.reviewboard.dto.SearchParameterDTO;
import com.mc.multicinema.reviewboard.dto.WritenReviewDTO;

public interface ReviewBoardService {

	List<ReviewBoardDTO> selectReviewBoard(ArrayList<Integer> paging);
	ReviewBoardDTO selectReviewBoard(int board_num);

	int countAllReview();
	int countSerchReview(SearchParameterDTO dto);
	List<ReviewBoardDTO> selectReviewBoardSearch(SearchParameterDTO dto);
	void insertReviewBoard(WritenReviewDTO form);
	void updateReviewBoard(WritenReviewDTO form);
	void updateDeleteBoard(int board_num);
	
	void minusLike(int board_num);
	void plusLike(int board_num);

	
}
