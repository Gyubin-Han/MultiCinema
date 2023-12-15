package com.mc.multicinema.screeningschedule.dao;

import java.io.IOException;
// import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;

import com.mc.multicinema.screeningschedule.dto.ScreeningScheduleQueryDateDTO;
import com.mc.multicinema.screeningschedule.dto.ScreeningScheduleQueryDateMovieDTO;
import com.mc.multicinema.screeningschedule.dto.ScreeningScheduleQueryDateMovieResultBeforeDTO;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Repository;

import com.mc.multicinema.screeningschedule.dto.ScreeningScheduleQueryDTO;
import com.mc.multicinema.screeningschedule.dto.ScreeningScheduleQueryResultDTO;

// 상영 일정 DAO
@Repository("ssdao")
public class ScreeningScheduleDAO {
//	public boolean isEmptySeat(ScreeningScheduleDTO dto){
//
//	}

	public List<ScreeningScheduleQueryResultDTO> doMovieQuery(ScreeningScheduleQueryDTO query){
		// DB 접근 호출
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory=null;
		try {
			factory = builder.build(Resources.getResourceAsReader("/mybatis-config.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SqlSession session = factory.openSession();
		
		// 결과 값 받아서 반환
		List<ScreeningScheduleQueryResultDTO> result=session.selectList("queryResultMovie");

		// 테스트용
		System.out.println("영화 쿼리 : 응답 준비 완료"+result.toString());
		for(ScreeningScheduleQueryResultDTO d:result){
			System.out.println(d.toString());
		}
		
		session.close();
		
		return result;
	}

	public List<ScreeningScheduleQueryDateDTO> doDateQuery(String selectDate){
		// DB 접근 호출
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory=null;
		try {
			factory = builder.build(Resources.getResourceAsReader("/mybatis-config.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SqlSession session = factory.openSession();
		
		// 결과 값 받아서 반환
		// 선택한 화면에 따라 쿼리 다르게 적용
//		List<ScreeningScheduleQueryResultDTO> result=null;
//		if(query.getDate()==null){
//			result=session.selectList("queryResultMovie");
//		}else{
//			result=session.selectList("queryResultDate");
//		}

		List<ScreeningScheduleQueryDateDTO> result=session.selectList("scq.queryDateToMovies",selectDate);
		
		session.close();

		// 테스트용
//		System.out.println("응답 준비 완료");
//		for(ScreeningScheduleQueryResultDTO d:result){
//			System.out.println(d.toString());
//		}
		
		System.out.println(result.size());
		System.out.println(result);
		System.out.println("응답 완료");
		return result;
	}
	
	// 날짜,영화 -> 상영관,시간 조회
	public List<ScreeningScheduleQueryDateMovieResultBeforeDTO> doDateMovieQuery(ScreeningScheduleQueryDateMovieDTO query){
		// DB 접근 호출
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory=null;
		try {
			factory = builder.build(Resources.getResourceAsReader("/mybatis-config.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSession session = factory.openSession();

		List<ScreeningScheduleQueryDateMovieResultBeforeDTO> result=session.selectList("scq.queryDateMovieToTimes",query);
		
		session.close();

		System.out.println(result.size());
		System.out.println("객체 : "+result);
		System.out.println("응답 완료");
		return result;
	}
}
