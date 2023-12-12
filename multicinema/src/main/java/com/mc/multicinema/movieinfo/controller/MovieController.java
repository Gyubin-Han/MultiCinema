package com.mc.multicinema.movieinfo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mc.multicinema.moviecomment.dto.MovieCommentDTO;
import com.mc.multicinema.moviecomment.service.MovieCommentService;
import com.mc.multicinema.movieinfo.dto.DailyBoxOfficeDTO;
import com.mc.multicinema.movieinfo.dto.MovieDTO;
import com.mc.multicinema.movieinfo.service.MovieService;

@Controller
public class MovieController {
	@Autowired
	MovieService movieservice;
	@Autowired
	MovieCommentService moviecommentservice;
	
	@RequestMapping("/")
	public ModelAndView home() {
		
		ArrayList<DailyBoxOfficeDTO> list = movieservice.dailyBoxOffice();
		System.out.println("==============================controller=======================================");
		ModelAndView mv = new ModelAndView();
		mv.addObject("movielist", list);
		mv.setViewName("mainpage");
		
		return mv;
	}
	
	@GetMapping("/moviedetail")
	public ModelAndView movieDetail(String movie_cd, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		if(movie_cd == null) {
			List<MovieDTO> list = movieservice.selectAllmovie();
			mv.addObject("list", list);
			mv.setViewName("movie_all");
			return mv;
		}else {
			MovieDTO dto = movieservice.selectMovieDetailInfo(movie_cd);
			//movie_cd를 영화제목으로 변환한 후 stillCutService에 영화 제목 전달
			//db에 검색 후 전달 감독명도
			String movie_title = dto.getMovie_title();
			String director = dto.getMovie_director();
			HashMap<String,ArrayList<String>> map = movieservice.stillCutActorsService(movie_title, director);
			List<MovieCommentDTO> commentlist = moviecommentservice.movieCommentsInit(movie_cd);
			//null일 경우 처리;
			
			mv.addObject("moviedto", dto);
			mv.addObject("movie_cd", movie_cd);
			mv.addObject("commentlist", commentlist);
			mv.addObject("map", map);
			mv.setViewName("movie_detail");
			return mv;	
		}
	}
}