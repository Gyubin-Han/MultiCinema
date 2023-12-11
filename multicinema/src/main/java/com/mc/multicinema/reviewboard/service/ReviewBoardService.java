package com.mc.multicinema.reviewboard.service;

import java.util.ArrayList;
import java.util.List;

import com.mc.multicinema.reviewboard.dto.ReviewBoardDTO;

public interface ReviewBoardService {

	List<ReviewBoardDTO> selectReviewBoard(ArrayList<Integer> paging);
	ReviewBoardDTO selectReviewBoard(int board_num);

	int countAllReview();

	
}
