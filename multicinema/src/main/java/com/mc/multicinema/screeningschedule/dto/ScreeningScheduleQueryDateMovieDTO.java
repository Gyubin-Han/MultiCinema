package com.mc.multicinema.screeningschedule.dto;

import java.time.LocalDate;

// 시간,영화에 따른 결과를 나타내는 질의 DTO
public class ScreeningScheduleQueryDateMovieDTO {
	LocalDate selectDate;
	int selectMovie;
	
	public ScreeningScheduleQueryDateMovieDTO(LocalDate selectDate, int selectMovie) {
		super();
		this.selectDate = selectDate;
		this.selectMovie = selectMovie;
	}

	public LocalDate getSelectDate() {
		return selectDate;
	}

	public void setSelectDate(LocalDate selectDate) {
		this.selectDate = selectDate;
	}

	public int getSelectMovie() {
		return selectMovie;
	}

	public void setSelectMovie(int selectMovie) {
		this.selectMovie = selectMovie;
	}
}
