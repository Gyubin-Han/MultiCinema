package com.mc.multicinema.screeningschedule.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ScreeningScheduleQueryInDateDTO {
	@JsonProperty("selectDate")
	private String date;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}

