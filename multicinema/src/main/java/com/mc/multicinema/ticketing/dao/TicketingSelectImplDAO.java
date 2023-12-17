package com.mc.multicinema.ticketing.dao;

import java.util.ArrayList;

import com.mc.multicinema.ticketing.dto.TicketingDTO;
import com.mc.multicinema.user.dto.UserDTO;

public class TicketingSelectImplDAO implements TicketingSelectDAO{

	@Override
	public ArrayList<TicketingDTO> selectAllMyTickets(UserDTO dto) {
		// ArrayList<TicketingDTO> list=new ArrayList<>();
		return null;
	}

	@Override
	public TicketingDTO selectTicket() {
		// TODO Auto-generated method stub
		// sql : SELECT user_info.user_id,user_info.user_name,movie_info.movie_title,screening_schedule.sch_time,seat.seat_name FROM ticketing LEFT JOIN seat ON ticketing.seat_id=seat.seat_id LEFT JOIN user_info ON ticketing.user_key=user_info.user_key LEFT JOIN screening_schedule ON seat.sch_id=screening_schedule.sch_id LEFT JOIN movie_info ON screening_schedule.movie_cd=movie_info.movie_cd;
		// ㄴ> 여러번 조인해서 필요한 모든 정보를 가져옴.
		
		
		return null;
	}
	
	// 테스트용
	public void test() {
		// ArrayList<String> list=
	}
	
}
