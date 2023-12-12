package com.mc.multicinema.reviewboard.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mc.multicinema.movieinfo.dto.MovieDTO;
import com.mc.multicinema.movieinfo.service.MovieInfoService;
import com.mc.multicinema.reviewboard.dto.ReviewBoardDTO;
import com.mc.multicinema.reviewboard.dto.ReviewDetailDTO;
import com.mc.multicinema.reviewboard.dto.SearchParameterDTO;
import com.mc.multicinema.reviewboard.dto.WritenReviewDTO;
import com.mc.multicinema.reviewboard.service.ReviewBoardService;
import com.mc.multicinema.reviewreply.dto.ReviewReplyWithLikeDTO;
import com.mc.multicinema.reviewreply.dto.WritenReplyDTO;
import com.mc.multicinema.reviewreply.service.ReviewReplyService;
import com.mc.multicinema.user.service.UserService;

/**
 * 2023-12-07 ~ ing JIN616
 * 영화리뷰게시글 테이블을 처리하기 위한 클래스
 */
@Controller
public class ReviewBoardController {

	@Autowired
	ReviewBoardService service;
	@Autowired
	UserService userService;
	@Autowired
	MovieInfoService movieService;
	@Autowired
	ReviewReplyService replyService;
	
	@PostMapping("/insertReply")
	public String insertReply(WritenReplyDTO reply) {
		replyService.insertReply(reply);

		return "redirect:reviewdetail?board_num="+reply.getBoard_num();
	}
	
	@RequestMapping("/board")
	public String reviewBoardHome() {
		return "reviewboard/reviewboard";
	}
	
	@GetMapping("/deletereview")
	public String deleteBoard(int board_num, int user_key, HttpSession session) {
		if(user_key != Integer.parseInt((String)session.getAttribute("login_user_key")))
			return "redirect:board";
		
		service.updateDeleteBoard(board_num);
		return "redirect:board";
	}
	
	@PostMapping("/updatereview")
	public String updateReview(WritenReviewDTO form) {
		service.updateReviewBoard(form);
		return "redirect:reviewdetail?board_num="+form.getBoard_num();
	}
	
	@GetMapping("/updatereview")
	@ResponseBody
	public ModelAndView updateReview(int board_num, int user_key, HttpSession session, ModelAndView mv) {		
		int login_user_key = Integer.parseInt((String)session.getAttribute("login_user_key"));
		if(user_key != login_user_key)
			return null;
		mv.addObject("review", service.selectReviewBoard(board_num));
		mv.addObject("movies", movieService.selectMovieTitleAll());
		mv.setViewName("reviewboard/updatereview");
		return mv;
	}
	
	@PostMapping("/writenreview")
	public String writenReview(WritenReviewDTO form) {
		service.insertReviewBoard(form);
		return "redirect:/board";
	}
	
	@RequestMapping("/writereview")
	@ResponseBody
	public ModelAndView writeBoard(int login_user_key) {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("user", userService.selectUserOne(login_user_key));
		List<MovieDTO> list = movieService.selectMovieTitleAll();
		System.out.println(list.size());
		mv.addObject("movies", movieService.selectMovieTitleAll());
		mv.setViewName("reviewboard/writereview");
		return mv;
	}
	
	@RequestMapping("/reviewdetail")
	@ResponseBody
	public ModelAndView selectReviewDetail(
			@RequestParam(value="board_num", defaultValue="-1") int board_num) {
		ModelAndView mv = new ModelAndView();
		
		if(board_num == -1) {
			System.out.println("check");
			mv.setViewName("redirect:board");
		}
		
		ReviewBoardDTO dto = service.selectReviewBoard(board_num);
		
		if(dto == null) {
			mv.addObject("errormsg", "올바른 게시물 번호를 입력하세요.");
			mv.setViewName("redirect:board");
		} else if(dto.isIs_deleted()) {
			mv.addObject("errormsg", "삭제된 게시물입니다.");
			mv.setViewName("redirect:board");
		} else {
			mv.addObject("movie_title",movieService.selectMovieTitle(dto.getMovie_cd()));
			mv.addObject("replys",replyService.selectReplyList(board_num));
			mv.addObject("review", dto);
			mv.addObject("user", userService.selectUserOne(dto.getUser_key()));
			mv.setViewName("reviewboard/reviewdetail");
		}
		return mv;
	}
	
	@RequestMapping("/countsearchreview")
	@ResponseBody
	public int countSearchReview(String search, String keyword) {
		SearchParameterDTO dto = new SearchParameterDTO();
		dto.setKeyword(keyword);
		dto.setSearch(search);
		return service.countSerchReview(dto);
	}
	
	@RequestMapping("/searchboard")
	@ResponseBody
	public List<ReviewBoardDTO> selectReviewBoardSearch(
			@RequestParam(value="page", required=false, defaultValue="1") int page,
			@RequestParam(value="limit", required=false, defaultValue="10") int limit,
			String search, String keyword, HttpSession session
			) {
		System.out.printf("Controller : selectReviewBoardSearch called\n"
				+ "page %d, limit %d, search %s, keyword %s\n", page, limit, search, keyword);
		
		SearchParameterDTO dto = new SearchParameterDTO();
		dto.setKeyword(keyword == null ? (String)session.getAttribute("keyword") : keyword);
		dto.setSearch(search == null ? (String)session.getAttribute("search") : search);
		pagingSearch(dto, page, limit);
		session.setAttribute("search", search);
		session.setAttribute("keyword", keyword);
		
		return service.selectReviewBoardSearch(dto);
	}
	
	private void pagingSearch(SearchParameterDTO dto, int page, int limit) {
		if(limit < 20) limit = 10;
		else if(limit < 30) limit = 20;
		else if(limit < 50) limit = 30;
		else limit = 50;
		
		int count = service.countSerchReview(dto);
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
		
		dto.setStartIndex(page*limit);
		dto.setLimit(limit);
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