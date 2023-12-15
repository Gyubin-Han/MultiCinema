package com.mc.multicinema.screeningschedule.dto;

import org.springframework.stereotype.Component;

@Component
public class ScreeningScheduleQueryDateDTO {
    int movieCd;
    String movieTitle;
    int movieLimitAge;
    // 지금 상영중인 일정인지 구분할 필요도 있어 보임. 상영하고 있지 않는(기록만 남은) 경우에는 목록에 뜨면 안되기 때문.

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

    public int getMovieLimitAge() {
        return movieLimitAge;
    }

    public void setMovieLimitAge(int movieLimitAge) {
        this.movieLimitAge = movieLimitAge;
    }
}
