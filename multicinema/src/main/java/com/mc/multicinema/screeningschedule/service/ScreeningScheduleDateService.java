package com.mc.multicinema.screeningschedule.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mc.multicinema.screeningschedule.dto.ScreeningScheduleQueryDateDTO;
import com.mc.multicinema.screeningschedule.dto.ScreeningScheduleQueryDateMovieDTO;
import com.mc.multicinema.screeningschedule.dto.ScreeningScheduleQueryDateMovieResultBeforeDTO;
import com.mc.multicinema.screeningschedule.dto.ScreeningScheduleQueryDateMovieResultDTO;
import com.mc.multicinema.screeningschedule.dto.ScreeningScheduleQueryInDateDTO;
import com.mc.multicinema.seat.dao.SeatImplDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mc.multicinema.screeningschedule.dao.ScreeningScheduleDAO;
import com.mc.multicinema.screeningschedule.dto.ScreeningScheduleQueryDTO;
import com.mc.multicinema.screeningschedule.dto.ScreeningScheduleQueryResultDTO;

@Service("ssds")
public class ScreeningScheduleDateService implements ScreeningScheduleService {
	@Autowired
	private ScreeningScheduleDAO dao;
	@Autowired
	private SeatImplDAO seatdao;

//	@Override
	public List<ScreeningScheduleQueryDateDTO> queryResult(LocalDate date) {
		List<ScreeningScheduleQueryDateDTO> result=dao.doDateQuery(date.toString());
		
		return result;
	}

	// 날짜 선택 시 영화 반환하는 서비스 메소드
	public List<ScreeningScheduleQueryDateDTO> queryDateToMovies(String selectDate){
//		HashMap<String,Object> result=new HashMap<String,Object>();
//		List<ScreeningScheduleQueryDateDTO> queryResult;
		
		
		
//		System.out.println(date);
//		System.out.println(Integer.parseInt(date.substring(0,4))+" / "+Integer.parseInt(date.substring(4,6))+" / "+Integer.parseInt(date.substring(6,8)));
		LocalDate ldt= LocalDate.of(Integer.parseInt(selectDate.substring(0,4)),Integer.parseInt(selectDate.substring(4,6)),Integer.parseInt(selectDate.substring(6,8)));
		System.out.println(ldt);
		
		return dao.doDateQuery(ldt.toString());
	}

	// 날짜, 영화 선택 시 반환하는 서비스 메소드
	public List<ScreeningScheduleQueryDateMovieResultBeforeDTO> queryDateMovieToTimes(String selectDate,int selectMovie){
		HashMap<String,Object> result=new HashMap<String,Object>();
		ScreeningScheduleQueryDateMovieDTO query;
//		List<ScreeningScheduleQueryDateMovieResultBeforeDTO> queryResult;
		
//		System.out.println(date);
//		System.out.println(Integer.parseInt(date.substring(0,4))+" / "+Integer.parseInt(date.substring(4,6))+" / "+Integer.parseInt(date.substring(6,8)));
		LocalDate ldt= LocalDate.of(Integer.parseInt(selectDate.substring(0,4)),Integer.parseInt(selectDate.substring(4,6)),Integer.parseInt(selectDate.substring(6,8)));
		System.out.println(ldt);
		
		query=new ScreeningScheduleQueryDateMovieDTO(ldt,selectMovie);
//		queryResult=dao.doDateMovieQuery(query);
		return dao.doDateMovieQuery(query);
	}
}
