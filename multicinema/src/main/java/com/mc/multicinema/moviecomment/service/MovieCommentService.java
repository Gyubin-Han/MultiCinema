package com.mc.multicinema.moviecomment.service;

import java.util.HashMap;
import java.util.List;

import com.mc.multicinema.moviecomment.dto.MovieCommentDTO;

/**
 * 
 * @author JIN
 * search
 * 모든 list를 반환하거나 comment_num, user_key, movie_cd를 검색해 반환하는 list
 * 	ex) HashMap<String, Integer> map = new HashMap<String, Integer>();
 * 		map.put("search", comment_num | user_key | movie_cd);
 * 		map.put("keyword", search_value)
 * 		selectMovieComment(map);
 * insert
 * 한 영화에 한 한줄평만 남기게끔 하는 기능 구현 
 * HashMap<String, Integer> map = new HashMap<String, Integer>();
 * 		map.put("user_key", user_key);
 * 		map.put("movie_cd", movie_cd);
 * 		selectMovieCommentCheck(map);
 * 										! 시간 되면 paging 기능 구현 필요
 * 
 * 
 */
public interface MovieCommentService {
	
	List<MovieCommentDTO> selectMovieCommentAll();
	List<MovieCommentDTO> selectMovieComment(HashMap<String, Integer> map);
	boolean selectMovieCommentCheck(HashMap<String, Integer> map);
	String insertMovieComment(MovieCommentDTO dto);
	
	
}
