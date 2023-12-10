package com.mc.multicinema.reviewboard.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mc.multicinema.reviewboard.dto.ReviewBoardDTO;
import com.mc.multicinema.reviewboard.service.ReviewBoardService;

/**
 * 2023-12-07 ~ ing JIN616
 * 영화리뷰게시글 테이블을 처리하기 위한 클래스
 */
@Controller
public class ReviewBoardController {

	@Autowired
	ReviewBoardService service;

	@RequestMapping("/board")
	public String reviewBoardHome() {
		return "reviewboard/reviewboard";
	}
	
	@RequestMapping("/countallreview")
	@ResponseBody
	public int countAllReview() {
		return service.countAllReview();
	}
	
	@RequestMapping("/reviewboard")
	@ResponseBody
	public List<ReviewBoardDTO> selectReviewBoard(
			@RequestParam(value="page", required=false, defaultValue="1") int page,
			@RequestParam(value="limit", required=false, defaultValue="10") int limit
			) {
		System.out.printf("Controller : selectReviewBoard called page %d, limit %d\n", page, limit);
		
		ArrayList<Integer> paging = pagingAll(page, limit);
		
		return service.selectReviewBoard(paging);
	}

	// 페이징 관련 입력값 받아와서 보정 
	// paging[0] = 인덱스 시작, paging[1] = 페이지당 게시글 개수(10, 20, limit 50)
	private ArrayList<Integer> pagingAll(int page, int limit) {
		
		if(limit < 20) limit = 10;
		else if(limit < 30) limit = 20;
		else if(limit < 50) limit = 30;
		else limit = 50;
		
		int count = service.countAllReview();
		page--;
		/**example
		 * case 1 : 
		 * page 1, count 10, limit 10 > page index is 0
		 * 
		 * case 2 : 
		 * page 4, count 11, limit 10 > page index is 1
		 */
		if(page < 0) page = 0;
		else if(page > (count - 1) / limit) page = (count - 1) / limit;
		
		ArrayList<Integer> paging = new ArrayList<Integer>();
		paging.add(page*limit);
		paging.add(limit);
		return paging;
	}

	
	
	
}