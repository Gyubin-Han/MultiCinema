package com.mc.multicinema.screeningschedule.dto;

import java.time.LocalDate;

// 조회 질의를 날리기 위한 DTO 객체
public class ScreeningScheduleQueryDTO {
	LocalDate date;   // 조회 대상 날짜
	int movie_cd;   // 조회 대상 영화 
	// int cinema_id;   // 조회 대상 영화관

	public ScreeningScheduleQueryDTO(LocalDate date, int movie_cd, int cinema_id) {
		this.date = date;
		this.movie_cd = movie_cd;
		// this.cinema_id = cinema_id;
	}

	public ScreeningScheduleQueryDTO(LocalDate of, Object object) {
    }

    public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public int getMovie_cd() {
		return movie_cd;
	}
	public void setMovie_cd(int movie_cd) {
		this.movie_cd = movie_cd;
	}
	// public int getCinema_id() {
	// 	return cinema_id;
	// }
	// public void setCinema_id(int cinema_id) {
	// 	this.cinema_id = cinema_id;
	// }
}
