package com.mc.multicinema.moviecomment.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mc.multicinema.moviecomment.dao.MovieCommentDAO;
import com.mc.multicinema.moviecomment.dto.MovieCommentDTO;

/**
 * 
 * @author JIN
 * search
 * 모든 list를 반환하거나 comment_num, user_key, movie_cd를 검색해 반환하는 list
 * 	ex) HashMap<String, Integer> map = new HashMap<String, Integer>();
 * 		map.put("search", comment_num | user_key | movie_cd);
 * 		map.put("key", search_value)
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
@Service
public class MovieCommentServiceImpl implements MovieCommentService {

	@Autowired
	MovieCommentDAO dao;
	
	@Override
	public List<MovieCommentDTO> selectMovieCommentAll() {
		return dao.selectMovieCommentAll();
	}

	@Override
	public List<MovieCommentDTO> selectMovieComment(HashMap<String, Integer> map) {
		return dao.selectMovieComment(map);
	}

	@Override
	public boolean selectMovieCommentCheck(HashMap<String, Integer> map) {
		return dao.selectMovieCommentCheck(map) != 0 ? true : false;
	}

	@Override
	public String insertMovieComment(MovieCommentDTO dto) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("user_key", dto.getUser_key());
		map.put("movie_cd", dto.getMovie_cd());
		if(selectMovieCommentCheck(map))
			return "한 영화당 한 한줄평만 남길 수 있습니다.";
		
		if(dao.insertMovieComment(dto) == 1)
			return "한줄평이 정상적으로 등록되었습니다.";
		else 
			return "알 수 없는 오류로 인해 등록에 실패하였습니다.\n관리자에게 문의하세요.";
	}

	
}
