package com.mc.multicinema.ticketing;

import com.mc.multicinema.seat.dao.SeatImplDAO;
import com.mc.multicinema.seat.dto.SeatDTO;
import com.mc.multicinema.seat.service.SeatService;
import com.mc.multicinema.ticketing.dto.TicketingPayRequestDTO;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mc.multicinema.screeningschedule.dto.ScreeningScheduleDTO;
import com.mc.multicinema.screeningschedule.dto.ScreeningScheduleToTicketingDTO;
import com.mc.multicinema.screeningschedule.service.ScreeningScheduleDateService;
import com.mc.multicinema.screeningschedule.service.ScreeningScheduleQueryService;
import com.mc.multicinema.screeningschedule.service.ScreeningScheduleService;
import com.mc.multicinema.ticketing.dto.TicketingPayDTO;
import com.mc.multicinema.ticketing.dto.TicketingPayResultDTO;
import com.mc.multicinema.ticketing.service.TicketingService;
//import com.mc.multicinema.ticketing.service;

@Controller
@RequestMapping("/ticketing")
public class TicketingController {
	@Autowired
	TicketingService service;
	@Autowired
	SeatService seatService;
	@Autowired
//	@Qualifier("ssds")
	ScreeningScheduleDateService schService;
	@Autowired
	ScreeningScheduleQueryService schQService;

	// 좌석을 선택하는 화면을 보여주는 메소드
	@PostMapping("/seatSelect")
	public ModelAndView seatSelect(@RequestParam(value="sch_id") String schId){
		// 먼저, 필요한 데이터를 서비스에서 받아옴.
		ScreeningScheduleToTicketingDTO result=schQService.getTicketScheduleInfo(Integer.parseInt(schId));
		
		// 다음으로, 뷰로 넘김.
		ModelAndView mv=new ModelAndView();
		mv.addObject("schedule",result);
		mv.setViewName("reserve_select");

		return mv;
	}

	// 현재 상영 일정에 예매한 좌석을 불러오는 메소드
	// 클라이언트가 AJAX로 요청 (비동기 처리)
	// 응답을 예매한 좌석을 배열로 보냄.
	@GetMapping("/seatAll")
	@ResponseBody
	public HashMap<String,Object> seatAllCheck(@RequestParam(value="sch_id") String schId){
		List<String> seatList=seatService.getSeatAll(Integer.parseInt(schId));
		
		HashMap<String,Object> result=new HashMap<String,Object>();
		result.put("seatList", seatList);
		
		return result;
	}
	
	// 현재 선택한 좌석 또는 확인중인 목록의 좌석이 이미 예매 완료된 좌석인지 확인하는 메소드
	// 클라이언트는 AJAX로 요청 및 응답 (비동기 처리)
	// 여러 좌석에 대해서는 클라이언트가 반복문으로 좌석 공석 확인 요청을 보내야 함.
	@RequestMapping(value="/seatSelectCheck", produces={"application/json;charset=utf-8"})
	@ResponseBody
	public String seatSelectCheck(SeatDTO seat){
		if(seatService.isEmpty(seat)){
			return "{\"emptyCheckResult\":\"FREE\"}";
		}else{
			return "{\"emptyCheckResult\":\"AVAILABLE\"}";
		}
	}

	@PostMapping("/payInfo")
	public ModelAndView payInfo(TicketingPayRequestDTO req) {
		ScreeningScheduleToTicketingDTO schInfo=schQService.getTicketScheduleInfo(req.getSchId());
		
		ModelAndView mv=new ModelAndView();
		mv.addObject("ticketingInfo",req);
		System.out.println("티켓 정보 : "+req.getSchId()+" "+req.getSeat().toString()+" "+req.getPrice());
		mv.addObject("schInfo",schInfo);
		mv.setViewName("reserve_payment");
		
		return mv;
	}
	
	@PostMapping("/dopay")
	public String paying(@RequestParam TicketingPayDTO paydto,RedirectAttributes reAttribute) {
		TicketingPayResultDTO result=service.doPay(paydto);
		
		reAttribute.addAttribute("result",result);
		
		return "redirect: /ticketing/result";
		
//		ModelAndView mv=new ModelAndView();
//		mv.addObject("payResult",result);
//		mv.setViewName
//		
//		return mv;
	}
	
	@GetMapping("/result")
	public ModelAndView result() {
		ModelAndView mv=new ModelAndView();
		mv.addObject("result","1");
		mv.addObject("errMsg","통장 잔액이 부족합니다.");
		mv.setViewName("reserve_result");
		
		return mv;
	}
}
