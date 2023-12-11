package com.mc.multicinema.reviewhistory;

public class ReviewHistoryDTO {
	
	String movie_title;
	String movie_img_src;
	String board_title;
	String board_write_date;
	
	
	public ReviewHistoryDTO() {
		super();
	}

	public ReviewHistoryDTO(String movie_title, String movie_img_src, String board_title, String board_write_date) {
		super();
		this.movie_title = movie_title;
		this.movie_img_src = movie_img_src;
		this.board_title = board_title;
		this.board_write_date = board_write_date;
	}

	public String getMovie_title() {
		return movie_title;
	}

	public void setMovie_title(String movie_title) {
		this.movie_title = movie_title;
	}

	public String getMovie_img_src() {
		return movie_img_src;
	}

	public void setMovie_img_src(String movie_img_src) {
		this.movie_img_src = movie_img_src;
	}

	public String getBoard_title() {
		return board_title;
	}

	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}

	public String getBoard_write_date() {
		return board_write_date;
	}

	public void setBoard_write_date(String board_write_date) {
		this.board_write_date = board_write_date;
	}
	
	
}
