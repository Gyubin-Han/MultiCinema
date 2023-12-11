package com.mc.multicinema.moviecomment.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mc.multicinema.moviecomment.dto.MovieCommentDTO;
import com.mc.multicinema.moviecomment.dto.MovieCommentSortDTO;
import com.mc.multicinema.moviecomment.service.MovieCommentService;
import com.mc.multicinema.movieinfo.service.MovieService;

@Controller
public class MovieCommentController {
	
	@Autowired
	MovieCommentService moviecommentservice;
	@Autowired
	MovieService movieservice;
	
	@PostMapping("/moviedetail")
	public String insertMovieComment(MovieCommentDTO dto, HttpSession session) {
		// 한줄평 받아야할 정보
		// 유저 키, 컨텐츠, 점수, 영화코드
		System.out.println(dto.getComment_score());
		
		int result = moviecommentservice.insertMovieComment(dto);

		String message = "";
		if (result == -2) {
			message = "이미 한줄평을 등록하셨습니다";
		} else if (result == -1) {
			message = "db 등록 오류";
		} else {
			message = "등록되었습니다";
		}

		session.setAttribute("message", message);

		return "redirect:/moviedetail?movie_cd=" + dto.getMovie_cd();
	}
	
	@RequestMapping(value="/moviecommentlikeadd", produces = {"application/json;charset=utf-8"})
	public @ResponseBody String movieCommentLikeAdd(String comment_num, String user_key) {
		Integer comment_num_parsed = Integer.parseInt(comment_num);
		Integer user_key_parsed = Integer.parseInt(user_key);
		
		System.out.println("=================Con comnum:"+comment_num_parsed + "/" + "uskey" + user_key_parsed +"================");
		
		String result = moviecommentservice.likeAdd(comment_num_parsed, user_key_parsed);
		
		return "{\"result\": \"" + result + "\"}";
		
	}
	
	@PostMapping("/deletemoviecomment")
	public String deleteMovieComment(String comment_num, String movie_cd, HttpSession session) {
		System.out.println(comment_num);
		int comment_num_parsed = Integer.parseInt(comment_num);
		
		int res = moviecommentservice.deleteComment(comment_num_parsed);
		System.out.println("===============con: " + res + "=========================");
		if(res == -1) {
			session.setAttribute("message", "좋아요 삭제 오류");
		}else if(res == -2) {
			session.setAttribute("message", "코멘트 삭제 오류");
		}else {
			session.setAttribute("message", "성공적으로 삭제되었습니다");
			System.out.println("===============con: 성공적 삭제" + "=========================");
		}
		
		return "redirect:/moviedetail?movie_cd=" + movie_cd;
	}
	
	@PostMapping("/updatemoviecomment")
	public String updateMovieComment(MovieCommentDTO dto, HttpSession session) {
		
		int res = moviecommentservice.updateMovieComment(dto);
		if(res == -1) {
			session.setAttribute("message", "한줄평 수정 오류");
		}else {
			session.setAttribute("message", "성공적으로 수정되었습니다");
		}
		
		return "redirect:/moviedetail?movie_cd=" + dto.getMovie_cd();
		
	}
	
	@RequestMapping(value="/morecomment", produces = {"application/json;charset=utf-8"})
	public @ResponseBody List<MovieCommentDTO> moreComment(MovieCommentSortDTO dto) {
		
		List<MovieCommentDTO> list =moviecommentservice.moreComment(dto);
		return list;
		
	}
	
	@RequestMapping(value="/sortcomment", produces = {"application/json;charset=utf-8"})
	public @ResponseBody List<MovieCommentDTO> sortComment(MovieCommentSortDTO dto) {
		
		List<MovieCommentDTO> list =moviecommentservice.sortComment(dto);
		
		return list;
		
	}
	
	@RequestMapping("/moviedb")
	public String moviedb() {
		movieservice.ApiToDB();
		
		return "/";
	}
}
