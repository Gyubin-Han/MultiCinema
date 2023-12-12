package com.mc.multicinema.movieinfo.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mc.multicinema.movieinfo.dto.MovieDTO;

@Repository
public class MovieInfoDAO {
	
	@Autowired
	SqlSession session;

	public List<MovieDTO> selectMovieTitleAll() {
		return session.selectList("movieinfo.selectMovieTitleAll");
	}

	public String selectMovieTitle(int movie_cd) {
		return session.selectOne("movieinfo.selectMovieTitle", movie_cd);
	}
	
	
}
