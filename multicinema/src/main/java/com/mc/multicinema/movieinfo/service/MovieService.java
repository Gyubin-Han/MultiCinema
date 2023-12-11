package com.mc.multicinema.movieinfo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mc.multicinema.moviecomment.dto.MovieCommentDTO;
import com.mc.multicinema.movieinfo.dto.DailyBoxOfficeDTO;
import com.mc.multicinema.movieinfo.dto.MovieDTO;



public interface MovieService {
	public ArrayList<DailyBoxOfficeDTO> dailyBoxOffice();
	public MovieDTO selectMovieDetailInfo(String movieCd);
	public HashMap<String,ArrayList<String>> stillCutActorsService(String movie_title, String director);
	void ApiToDB();
	HashMap<String, String> posterAndPlot(String movie_title, String director);
	public List<MovieDTO> selectAllmovie();
	
	
	
	
}
