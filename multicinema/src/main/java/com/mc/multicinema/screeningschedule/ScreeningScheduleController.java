package com.mc.multicinema.screeningschedule;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import com.mc.multicinema.screeningschedule.dto.ScreeningScheduleQueryDateDTO;
import com.mc.multicinema.screeningschedule.dto.ScreeningScheduleQueryDateMovieResultBeforeDTO;
import com.mc.multicinema.screeningschedule.service.ScreeningScheduleDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.ResponseBody;

import com.mc.multicinema.screeningschedule.dto.ScreeningScheduleQueryDTO;
import com.mc.multicinema.screeningschedule.service.ScreeningScheduleService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/timetable")
public class ScreeningScheduleController {
	@Autowired
	@Qualifier("ssds")
//	ScreeningScheduleService service;
	ScreeningScheduleDateService service;
	
	// @RequestMapping(value="/query",produces= {"application/json;charset=utf-8"})
	// @ResponseBody
	// public String scheduleQuery(Model){
		
	// 	return "";
	// }
//	@GetMapping("/test")
//	public void test(){
//		System.out.println("컨트롤러 : test 받음.");
////		service.queryResult(new ScreeningScheduleQueryDTO(LocalDate.of(2023, 10, 10),null));
//		service.queryResult(new ScreeningScheduleQueryDTO(null,10001));
//	}

	// 상영시간표 조회 기준을 정하는 페이지
	@GetMapping("/")
	public String ScheduleSelect() {
		return "timetable_select";
	}
	@GetMapping("")
	public String ScheduleSelect2() {
		return "redirect: ./timetable/";
	}
	
	@GetMapping("/date")
	public String ScheduleTime(Model model) {
		return "timetable_date";
	}

	// 날짜 기준 영화 정보 조회 처리 메소드 / 사용자에게 AJAX로 요청받은 경우, 처리 후 결과를 JSON 형태로 반환
	@RequestMapping(value="/selectDate", produces={"application/json;charset=utf-8"})
	@ResponseBody
	public HashMap<String,Object> selectDate(@RequestParam(value="selectDate") String selectDate){
		HashMap<String,Object> result=new HashMap<String,Object>();
		System.out.println(selectDate);

		List<ScreeningScheduleQueryDateDTO> list=service.queryDateToMovies(selectDate);
		result.put("dataCnt",list.size());
		result.put("movieList",list);
		for(ScreeningScheduleQueryDateDTO d:list) {
			System.out.println(d.getMovieCd()+" "+d.getMovieTitle()+" "+d.getMovieLimitAge());
		}

		return result;
	}
	
	// 영화 선택 후, 상영관 정보 및 상영 시간표 정보 조회 처리 메소드 / 사용자에게 AJAX로 요청받은 경우, 처리 후 결과를 JSON 형태로 반환
	@RequestMapping(value="/selectDateMovie", produces={"application/json;charset=utf-8"})
	@ResponseBody
	public HashMap<String,Object> selectDateMovie(@RequestParam(value="selectDate") String selectDate, @RequestParam(value="selectMovie") int selectMovie){
		HashMap<String,Object> result=new HashMap<String,Object>();
		System.out.println("1. 날짜 : "+selectDate);
		System.out.println("2. 영화 : "+selectMovie);

		List<ScreeningScheduleQueryDateMovieResultBeforeDTO> list=service.queryDateMovieToTimes(selectDate,selectMovie);
		result.put("dataCnt",list.size());
		result.put("timeTable",list);
		for(ScreeningScheduleQueryDateMovieResultBeforeDTO d:list) {
			System.out.println(d.getSchId()+" "+d.getTheaterId()+" "+d.getTheaterName()+" "+d.getSchDate()+" "+d.getNowPeople()+"/"+d.getTheaterMaxPeople());
		}

		return result;
	}
}
