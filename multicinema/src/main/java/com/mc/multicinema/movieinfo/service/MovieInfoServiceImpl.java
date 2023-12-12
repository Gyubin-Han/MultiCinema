package com.mc.multicinema.movieinfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mc.multicinema.movieinfo.dao.MovieInfoDAO;
import com.mc.multicinema.movieinfo.dto.MovieDTO;

@Service
public class MovieInfoServiceImpl implements MovieInfoService {

	@Autowired
	MovieInfoDAO dao;
	
	@Override
	public List<MovieDTO> selectMovieTitleAll() {
		return dao.selectMovieTitleAll();
	}

	@Override
	public String selectMovieTitle(int movie_cd) {
		return dao.selectMovieTitle(movie_cd);
	}

}
