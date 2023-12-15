package com.mc.multicinema.screeningschedule.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.mc.multicinema.screeningschedule.dao.ScreeningScheduleDAO;

public class ScreeningScheduleDAOFactory {
    @Autowired
	private ScreeningScheduleDAO dao;

    public ScreeningScheduleDAO getInstance(){ return dao; }
}
