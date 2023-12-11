package com.mc.multicinema.moviehistory;

public class MovieHistoryDTO {
	
	String movie_title;
	String movie_img_url;
	String ticketing_num;
	String sch_time;
	
	public MovieHistoryDTO() {
		super();
	}
	
	public MovieHistoryDTO(String movie_title, String movie_img_url, String ticketing_num, String sch_time) {
		super();
		this.movie_title = movie_title;
		this.movie_img_url = movie_img_url;
		this.ticketing_num = ticketing_num;
		this.sch_time = sch_time;
	}

	public String getMovie_title() {
		return movie_title;
	}

	public void setMovie_title(String movie_title) {
		this.movie_title = movie_title;
	}

	public String getMovie_img_url() {
		return movie_img_url;
	}

	public void setMovie_img_url(String movie_img_url) {
		this.movie_img_url = movie_img_url;
	}

	public String getTicketing_num() {
		return ticketing_num;
	}

	public void setTicketing_num(String ticketing_num) {
		this.ticketing_num = ticketing_num;
	}

	public String getSch_time() {
		return sch_time;
	}

	public void setSch_time(String sch_time) {
		this.sch_time = sch_time;
	}
	
	
	
}
