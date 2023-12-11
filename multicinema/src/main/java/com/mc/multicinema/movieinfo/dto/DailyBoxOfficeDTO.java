package com.mc.multicinema.movieinfo.dto;

import org.springframework.stereotype.Component;

@Component
public class DailyBoxOfficeDTO implements Cloneable{
	String movie_title;
	int movie_cd;
	int rank;
	String movie_limitAge;
	int audiAcc;
	String movie_img_src;
	double score;
	
	
	
	public double getScore() {
		return score;
	}


	public void setScore(double score) {
		this.score = score;
	}





	public String getMovie_img_src() {
		return movie_img_src;
	}





	public void setMovie_img_src(String movie_img_src) {
		this.movie_img_src = movie_img_src;
	}







	public String getMovie_title() {
		return movie_title;
	}





	public void setMovie_title(String movie_title) {
		this.movie_title = movie_title;
	}





	public int getMovie_cd() {
		return movie_cd;
	}





	public void setMovie_cd(int movie_cd) {
		this.movie_cd = movie_cd;
	}





	public int getRank() {
		return rank;
	}





	public void setRank(int rank) {
		this.rank = rank;
	}





	public String getMovie_limitAge() {
		return movie_limitAge;
	}





	public void setMovie_limitAge(String movie_limitAge) {
		this.movie_limitAge = movie_limitAge;
	}





	public int getAudiAcc() {
		return audiAcc;
	}





	public void setAudiAcc(int audiAcc) {
		this.audiAcc = audiAcc;
	}





	@Override
	public Object clone() throws CloneNotSupportedException {
		
		return super.clone();
	}
	
	
	
}
