package com.mc.multicinema.seat.dto;

public class SeatDTO {
	
	String seat_id;   // 좌석 고유 id
	int sch_id;   // 상영 일정 id
	String seat_name;   // 좌석번호
	
	public String getSeat_id() {
		return seat_id;
	}
	public void setSeat_id(String seat_id) {
		this.seat_id = seat_id;
	}
	public int getSch_id() {
		return sch_id;
	}
	public void setSch_id(int sch_id) {
		this.sch_id = sch_id;
	}
	public String getSeat_name() {
		return seat_name;
	}
	public void setSeat_name(String seat_name) {
		this.seat_name = seat_name;
	}
	
	
}
