package com.mc.multicinema.seat.service;

import com.mc.multicinema.seat.dao.SeatImplDAO;
import com.mc.multicinema.seat.dto.SeatDTO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("seat")
public class SeatService {
    @Autowired
    SeatImplDAO dao;

    public boolean isEmpty(SeatDTO seatdto){
        return dao.isEmptySeat(seatdto);
    }

    public int countPeople(int sch_id){
        return dao.countPeople(sch_id);
    }
    
    public List<String> getSeatAll(int schId){
    	return dao.getSeatAll(schId);
    }
}
