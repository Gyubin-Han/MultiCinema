package com.mc.multicinema.screeningschedule.dto;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ScreeningScheduleQueryDateMovieResultBeforeDTO {
	int theaterId;   // 상영관 코드
    String theaterName;   // 상영관 이름
    int theaterMaxPeople;   // 상영관 최대 좌석 수
    int schId;   // 상영 일정 코드
//    LocalDateTime schDateTime;   // 상영 일정 날짜 및 시간
    String schDate;
    String schTime;
    int nowPeople;   // 현재 인원 수 (조회 시, COUNT 함수 사용)

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

    public String getSchDate() {
        return schDate;
    }

    public void setSchDate(LocalDateTime schDateTime) {
        this.schDate = schDateTime.toString().split("T")[0];
    }
    
    public String getSchTime() {
        return schTime;
    }

    public void setSchTime(LocalDateTime schDateTime) {
        this.schTime = schDateTime.toString().split("T")[1];
    }

	public int getTheaterId() {
		return theaterId;
	}

	public void setTheaterId(int theaterId) {
		this.theaterId = theaterId;
	}

	public int getNowPeople() {
		return nowPeople;
	}

	public void setNowPeople(int nowPeople) {
//		System.out.println("예매 가능 전 : "+nowPeople);
//		this.nowPeople = (this.theaterMaxPeople-nowPeople);
		this.nowPeople=nowPeople;
	}
}
