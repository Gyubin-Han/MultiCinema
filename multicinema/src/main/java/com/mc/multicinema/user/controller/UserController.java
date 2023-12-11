package com.mc.multicinema.user.controller;

import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mc.multicinema.moviehistory.MovieHistoryDTO;
import com.mc.multicinema.movieinfo.dto.MovieDTO;
import com.mc.multicinema.reviewhistory.ReviewHistoryDTO;
import com.mc.multicinema.user.dto.UserDTO;
import com.mc.multicinema.user.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService service;
	
	@RequestMapping("/mypage")
	ModelAndView mypage(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("mypage");
		return mv;
	}
	

	
	// 회원 정보 수정
	@RequestMapping("/mypage/authentication")
	ModelAndView authentication(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("authentication");
		return mv;
	}
	
	@PostMapping("/mypage/authenticationresult")
	String authenticationResult(String user_pw, HttpSession session) {
		
		if(service.authentication(user_pw, session) == true) {
			return "changeuserinfo";
		}
		else	{
			return "authentication";
		}
	}
	
	

	
	@RequestMapping("/mypage/changeuserinfo")
	ModelAndView changeUserInfo() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("changeuserinfo");
		return mv;
	}
	
	@RequestMapping("/mypage/changeemail")
	ModelAndView changeemail() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("changeemail");
		return mv;
	}
	
	@PostMapping("/mypage/changeemailresult")
	String changeEmailResult(String user_email, String user_email_again, HttpSession session) {
		
		String email_pattern = "^[A-Za-z0-9]{4,12}@[A-Za-z0-9]{1,}.(co.kr|com|net)$";
		// 이메일 형식이 안맞는 경우
		if(!Pattern.matches(email_pattern,user_email)) {
			return "changeemail";
		}
		// 두 입력값이 다른 경우
		else if(!user_email.equals(user_email_again)) {
			return "changeemail";
		}
		// 정상적인 경우
		else {
			String login_user_key = (String)session.getAttribute("login_user_key");
			UserDTO login_user = service.getUserByUserKey(login_user_key);
			service.changeUserEmail(login_user, user_email);
			return "infochangefinish";
		}
	}
	
	@RequestMapping("/mypage/changepw")
	ModelAndView changepw(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("changepw");
		return mv;
	}
	
	@PostMapping("/mypage/changepwresult")
	String changePwResult(String user_pw, String user_pw_again, HttpSession session) {
		
		// 두 입력값이 다른 경우
		if(!user_pw.equals(user_pw_again) || user_pw.equals(null) ) {
			return "changepw";
		}
		// 정상적인 경우
		else {
			String login_user_key = (String)session.getAttribute("login_user_key");
			UserDTO login_user = service.getUserByUserKey(login_user_key);
			service.changeUserPw(login_user, user_pw);
			return "infochangefinish";
		}
	}
	// 예매 내역 
	@RequestMapping("/mypage/reservedetail")
	ModelAndView reserveMovielist(@RequestParam(value="pagenum", required=false , defaultValue="1") int pagenum) {
		int pagecount = 5;
		int [] limit = new int[2];
		limit[0] = (pagenum-1) * pagecount;
		limit[1] = pagecount;
		ModelAndView mv = new ModelAndView();
		
		List<MovieHistoryDTO> list = service.movieList(limit);
		mv.addObject("boardlist", list);
		int totalcount = service.getTotalMovieBoard();
		mv.addObject("totalcount", totalcount);
		
		mv.addObject("pagecount", pagecount);
		mv.setViewName("reservedetail");
		return mv;
	}
	
	// 작성 리뷰 게시글 
	@RequestMapping("/mypage/reviewboardhistory")
	ModelAndView reviewBoardHistory(@RequestParam(value="pagenum", required=false , defaultValue="1") int pagenum) {
		int pagecount = 5;
		int [] limit = new int[2];
		limit[0] = (pagenum-1) * pagecount;
		limit[1] = pagecount;
		ModelAndView mv = new ModelAndView();
		
		List<ReviewHistoryDTO> list = service.reviewBoardList(limit);
		mv.addObject("boardlist", list);
		int totalcount = service.getTotalReviewBoard();
		mv.addObject("totalcount", totalcount);
		
		mv.addObject("pagecount", pagecount);
		mv.setViewName("reviewboardhistory");
		
		return mv;
	}
	
	
	
}
