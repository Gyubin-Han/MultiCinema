package com.mc.multicinema.seat.dao;

import com.mc.multicinema.seat.dto.SeatDTO;

public interface SeatDAO {
	// 해당 좌석의 예매 기록이 있는지 확인
	public boolean isEmptySeat(SeatDTO dto);
	
	// 해당 좌석을 예매
	public boolean insertSeat(SeatDTO dto);
}
