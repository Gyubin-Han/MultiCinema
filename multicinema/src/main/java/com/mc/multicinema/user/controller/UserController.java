package com.mc.multicinema.user.controller;

import java.util.List;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
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

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mc.multicinema.user.dto.UserDTO;
import com.mc.multicinema.user.service.UserService;


/**
 * 
 * @author JIN
 * 회원 키 가지고 유저 닉네임 가져오는 sql 작성 
 *
 */
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
	List<UserDTO> selectUserAll() {
		return service.selectUserAll();
	}
	
	@RequestMapping("/logout")
	public String logoutProcess(HttpSession session) {
		if(session.getAttribute("login_user_id") != null) {
			session.removeAttribute("login_user_id");
			
			if(session.getAttribute("login_user_name") != null)
				session.removeAttribute("login_user_name");
			if(session.getAttribute("login_user_key") != null)
				session.removeAttribute("login_user_key");
		}
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String login() {
		//로그인 이미 했을때 처리
		return "login";
	}
	
	@PostMapping("/login")
	public String loginProcess(String user_id, String user_pw, Model m, HttpSession session) {
		
		//id, pw 정보 있는지 확인
		//있으면 return 로그인을 눌렀던 그 페이지로
		//정보 있음
		System.out.println("=====================con"+user_id + user_pw+"=====================");
		UserDTO dto = service.loginProcess(user_id, user_pw);
		
		if(dto != null && dto.getUser_key() != null && dto.getUser_name() != null && dto.getUser_id() != null) {
			session.setAttribute("login_user_id", dto.getUser_id());
			session.setAttribute("login_user_key", dto.getUser_key());
			session.setAttribute("login_user_name", dto.getUser_name());
			
			System.out.println("test" + session.getAttribute("login_user_id"));
			
			//세션에 추가할 것
			//user_key,
			//아이디, 이름,
			return "redirect:/";
		}else {
			m.addAttribute("result", "아이디와 비밀번호가 일치하지 않습니다");
			return "login";
		}
	}
	
	@GetMapping("/membercheck")
	public String memberCheck() {
		return "member_check";
	}
	
	@PostMapping("/membercheck")

	public String memberCheckProcess(String user_id, String user_email, Model m) {
		
		//서비스 dao 멤버 있는지 확인 후
		//있다 modelandview 생성 후 model에 아이디 이메일값 넣어주고 회원가입 페이지로
		//없다 modelandview 생성 후 model에 이미 있는 회원이다 하고 모델만 주기
		//jsp에선 ${모델이름명}으로 들고와서 스크립트에서 alert 띄워주기
		List<UserDTO> list = service.memberCheckProcess(user_id, user_email);
		
		if(list.size() == 0) {
			m.addAttribute("result", "가입가능");
			m.addAttribute("user_id", user_id);
			m.addAttribute("user_email", user_email);
			return "member_check";
		}else {
			m.addAttribute("result", "이미 존재하는 회원입니다");
			return "member_check";
		}

	}
	

	

	// 회원 정보 수정
	@RequestMapping("/mypage/authentication")
	ModelAndView authentication(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("authentication");
		return mv;
	}
	
	@PostMapping("/mypage/authenticationresult")
	ModelAndView authenticationResult(String user_pw, HttpSession session,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		if(service.authentication(user_pw, session) == true) {
			mv.addObject("authenticationStatus","1");
			mv.setViewName("changeuserinfo");
			
			return mv;
		}
		else	{
			mv.addObject("authenticationStatus","2");
			mv.setViewName("authentication");
			
			return mv;
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
	ModelAndView reserveMovielist(@RequestParam(value="pagenum", required=false , defaultValue="1") int pagenum,HttpSession session ) {
		int pagecount = 5;
		int [] limit = new int[2];
		limit[0] = (pagenum-1) * pagecount;
		limit[1] = pagecount;
		ModelAndView mv = new ModelAndView();
		String login_user_key = (String)session.getAttribute("login_user_key");
		List<MovieHistoryDTO> list = service.movieList(login_user_key,limit);
		mv.addObject("boardlist", list);
		int totalcount = service.getTotalMovieBoard();
		mv.addObject("totalcount", totalcount);
		
		mv.addObject("pagecount", pagecount);
		mv.setViewName("reservedetail");
		return mv;
	}
	
	// 작성 리뷰 게시글 
	@RequestMapping("/mypage/reviewboardhistory")
	ModelAndView reviewBoardHistory(@RequestParam(value="pagenum", required=false , defaultValue="1") int pagenum,HttpSession session) {
		int pagecount = 5;
		int [] limit = new int[2];
		limit[0] = (pagenum-1) * pagecount;
		limit[1] = pagecount;
		ModelAndView mv = new ModelAndView();
		String login_user_key = (String)session.getAttribute("login_user_key");
		List<ReviewHistoryDTO> list = service.reviewBoardList(login_user_key,limit);
		mv.addObject("boardlist", list);
		int totalcount = service.getTotalReviewBoard();
		mv.addObject("totalcount", totalcount);
		
		mv.addObject("pagecount", pagecount);
		mv.setViewName("reviewboardhistory");
		return mv;
	}
	
	@PostMapping("/memberjoin")
	public ModelAndView memberjoin(String user_id, String user_email) {
		
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("user_id", user_id);
		mv.addObject("user_email", user_email);
		mv.setViewName("member_join");

		
		return mv;
	}
	

	@PostMapping("/welcome")
	public String welcome(UserDTO dto, Model m) {
		int res = service.memberJoinProcess(dto);
		if(res == -1) {
			System.out.println("db 오류");
			return "redirect:/memberjoin";
		}else {
			m.addAttribute("user_name", dto.getUser_name());
			return "welcome";
		}
		
		
	}

	
}
