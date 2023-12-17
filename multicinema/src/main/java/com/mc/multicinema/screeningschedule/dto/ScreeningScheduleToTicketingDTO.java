package com.mc.multicinema.screeningschedule.dto;

import java.time.LocalDateTime;

public class ScreeningScheduleToTicketingDTO {
	int schId;
	String movieTitle;
	LocalDateTime schDateTime;
	String cinemaName;
	String theaterName;
	
	public int getSchId() {
		return schId;
	}
	public void setSchId(int schId) {
		this.schId = schId;
	}
	public String getMovieTitle() {
		return movieTitle;
	}
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
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
	public LocalDateTime getSchDateTime() {
		return schDateTime;
	}
	public void setSchDateTime(LocalDateTime schDateTime) {
		this.schDateTime = schDateTime;
	}

}
