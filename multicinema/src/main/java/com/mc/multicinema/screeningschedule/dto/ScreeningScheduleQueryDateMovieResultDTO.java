package com.mc.multicinema.screeningschedule.dto;

import java.time.LocalDateTime;

public class ScreeningScheduleQueryDateMovieResultDTO {
	int theaterId;   // 상영관 코드
    String theaterName;   // 상영관 이름
    int theaterMaxPeople;   // 상영관 최대 좌석 수
    int schId;   // 상영 일정 코드
    LocalDateTime schDateTime;   // 상영 일정 날짜 및 시간
    int availSeatCnt;   // 예매 가능 좌석
    
	public int getTheaterId() {
		return theaterId;
	}
	public void setTheaterId(int theaterId) {
		this.theaterId = theaterId;
	}
	public String getTheaterName() {
		return theaterName;
	}
	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}
	public int getTheaterMaxPeople() {
		return theaterMaxPeople;
	}
	public void setTheaterMaxPeople(int theaterMaxPeople) {
		this.theaterMaxPeople = theaterMaxPeople;
	}
	public int getSchId() {
		return schId;
	}
	public void setSchId(int schId) {
		this.schId = schId;
	}
	public LocalDateTime getSchDateTime() {
		return schDateTime;
	}
	public void setSchDateTime(LocalDateTime schDateTime) {
		this.schDateTime = schDateTime;
	}
	public int getAvailSeatCnt() {
		return availSeatCnt;
	}
	public void setAvailSeatCnt(int availSeatCnt) {
		this.availSeatCnt = availSeatCnt;
	}
}
