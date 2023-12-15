package com.mc.multicinema.screeningschedule.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mc.multicinema.screeningschedule.dao.ScreeningScheduleDAO;
import com.mc.multicinema.screeningschedule.dto.ScreeningScheduleQueryResultDTO;
// import com.mc.multicinema.screeningschedule.dto.ScreeningScheduleDTO;
import com.mc.multicinema.screeningschedule.dto.ScreeningScheduleQueryDTO;

@Service("ssms")
public class ScreeningScheduleMovieService implements ScreeningScheduleService {
	@Autowired
	private ScreeningScheduleDAO dao;

//	@Override
	public ArrayList<ScreeningScheduleQueryResultDTO> queryResult(ScreeningScheduleQueryDTO query) {
		ArrayList<ScreeningScheduleQueryResultDTO> result=(ArrayList<ScreeningScheduleQueryResultDTO>) dao.doMovieQuery(query);
		
		return result;
	}

}
