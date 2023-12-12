package com.mc.multicinema.movieinfo.service;

import java.util.List;

import com.mc.multicinema.movieinfo.dto.MovieDTO;

public interface MovieInfoService {

	List<MovieDTO> selectMovieTitleAll();
}
