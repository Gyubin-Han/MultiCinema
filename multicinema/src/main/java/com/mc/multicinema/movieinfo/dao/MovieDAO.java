package com.mc.multicinema.movieinfo.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mc.multicinema.movieinfo.dto.MovieDTO;

@Repository
public class MovieDAO {
	@Autowired
	SqlSession session;

	public int insertOneMovie(MovieDTO moviedto) {
		
		return session.insert("insertOneMovie", moviedto);
	}

	public String selectMovieImgSrc(int movie_cd) {
		
		return session.selectOne("selectMovieImgSrc", movie_cd);
	}

	public MovieDTO selectOneMovie(String movie_cd) {
		int movie_cd_parse = Integer.parseInt(movie_cd);
		return session.selectOne("selectOneMovie", movie_cd_parse);
	}

	public List<MovieDTO> selectAllmovie() {
		
		return session.selectList("selectAllmovie");
	}

	
	
	
	
}
