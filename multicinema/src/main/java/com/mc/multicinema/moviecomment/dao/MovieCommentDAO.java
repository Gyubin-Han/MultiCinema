package com.mc.multicinema.moviecomment.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mc.multicinema.moviecomment.dto.MovieCommentDTO;

@Repository
public class MovieCommentDAO {
	
	@Autowired
	SqlSession session;

	public List<MovieCommentDTO> selectMovieCommentAll() {
		return session.selectList("moviecomment.selectMovieCommentAll");
	}

	public List<MovieCommentDTO> selectMovieComment(HashMap<String, Integer> map) {
		return session.selectList("moviecomment.selectMovieCommentAllBySearching", map);
	}

	public int selectMovieCommentCheck(HashMap<String, Integer> map) {
		return session.selectOne("moviecomment.selectMovieCommentCheck", map);
	}

	public int insertMovieComment(MovieCommentDTO dto) {
		return session.insert("moviecomment.insertMovieComment", dto);
	}
	
	
	
}
