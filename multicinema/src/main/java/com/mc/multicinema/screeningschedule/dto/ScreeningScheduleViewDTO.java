package com.mc.multicinema.screeningschedule.dto;

import java.util.ArrayList;
import java.time.LocalDateTime;

public class ScreeningScheduleViewDTO {
	ScreeningScheduleDTO original;
	String movieTitle;   // 영화명
	String movieShowTm;   // 영화 총 상영시간
	String cinemaName;   // 영화관 명(지점명)
	String theaterName;   // 상영관 명
	LocalDateTime sch_time;   // 예매한 영화 일시
	ArrayList<String> seat;   // 좌석(문자열 배열)
	
	public ScreeningScheduleDTO getOriginal() {
		return original;
	}
	public void setOriginal(ScreeningScheduleDTO original) {
		this.original = original;
	}
	public String getMovieTitle() {
		return movieTitle;
	}
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}
	public String getMovieShowTm() {
		return movieShowTm;
	}
	public void setMovieShowTm(String movieShowTm) {
		this.movieShowTm = movieShowTm;
	}
	public String getCinemaName() {
		return cinemaName;
	}
	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}
	public String getTheaterName() {
		return theaterName;
	}
	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}
	public LocalDateTime getSch_time() {
		return sch_time;
	}
	public void setSch_time(LocalDateTime sch_time) {
		this.sch_time = sch_time;
	}
	public ArrayList<String> getSeat() {
		return seat;
	}
	public void setSeat(ArrayList<String> seat) {
		this.seat = seat;
	}
}
