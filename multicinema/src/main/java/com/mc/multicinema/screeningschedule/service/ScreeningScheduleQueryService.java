package com.mc.multicinema.screeningschedule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mc.multicinema.screeningschedule.dao.ScreeningScheduleDAO;
import com.mc.multicinema.screeningschedule.dto.ScreeningScheduleToTicketingDTO;

@Service("ssqs")
public class ScreeningScheduleQueryService implements ScreeningScheduleService{
	@Autowired
	ScreeningScheduleDAO dao;
	
	public ScreeningScheduleToTicketingDTO getTicketScheduleInfo(int schId) {
		return dao.getScreeningSceduleInfo(schId);
	}
}
