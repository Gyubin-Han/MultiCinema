package com.mc.multicinema.screeningschedule.dto;

import java.time.LocalDateTime;

// 특정 조건(날짜, 영화)에 맞는 쿼리 결과에 대한 DTO
public class ScreeningScheduleQueryResultDTO {
	int movieCd;   // 영화 코드
	String movieTitle;   // 영화 제목
	String movieShowTm;   // 상영 시간
	int cinemaId;   // 영화관 코드
	String cinemaName;   // 영화관 이름
	String theaterId;   // 상영관 코드
	String theaterName;   // 상영관 이름
	String theaterSeatCount;   // 상영관 전체 좌석 수
	int schId;   // 상영 일정 코드
	LocalDateTime schTime;   // 상영 일시
	
	public int getMovieCd() {
		return movieCd;
	}
	public void setMovieCd(int movieCd) {
		this.movieCd = movieCd;
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
	public int getCinemaId() {
		return cinemaId;
	}
	public void setCinemaId(int cinemaId) {
		this.cinemaId = cinemaId;
	}
	public String getCinemaName() {
		return cinemaName;
	}
	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}
	public String getTheaterId() {
		return theaterId;
	}
	public void setTheaterId(String theaterId) {
		this.theaterId = theaterId;
	}
	public String getTheaterName() {
		return theaterName;
	}
	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}
	public String getTheaterSeatCount() {
		return theaterSeatCount;
	}
	public void setTheaterSeatCount(String theaterSeatCount) {
		this.theaterSeatCount = theaterSeatCount;
	}
	public int getSchId() {
		return schId;
	}
	public void setSchId(int schId) {
		this.schId = schId;
	}
	public LocalDateTime getSchTime() {
		return schTime;
	}
	public void setSchTime(LocalDateTime schTime) {
		this.schTime = schTime;
	}
}
