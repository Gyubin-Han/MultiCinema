package com.mc.multicinema.seat.dao;

import com.mc.multicinema.seat.dto.SeatDTO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository("seatdao")
public class SeatImplDAO implements SeatDAO{

	@Override
	public boolean isEmptySeat(SeatDTO dto) {
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory=null;
		try {
			factory = builder.build(Resources.getResourceAsReader("seat/mapper/seat-config.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSession session = factory.openSession();

		List<Integer> result=session.selectList("isEmptySeat");
		session.close();

		if(result.get(0)==0){ return true; }
		else{ return false; }
	}

	public List<String> getSeatAll(int schId) {
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory=null;
		try {
			factory = builder.build(Resources.getResourceAsReader("/mybatis-config.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSession session = factory.openSession();

		List<String> result=session.selectList("seat.getSeatAll",schId);
		session.close();

		return result;
	}

	@Override
	public boolean insertSeat(SeatDTO dto) {
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory=null;
		try {
			factory = builder.build(Resources.getResourceAsReader("screeningschedule/mapper/sc-config.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SqlSession session = factory.openSession();

		return false;
	}

	public int countPeople(int sch_id){
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory=null;
		try {
			factory = builder.build(Resources.getResourceAsReader("seat/mapper/seat-config.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SqlSession session=factory.openSession();

		List<Integer> result=session.selectList("countPeople");
		session.close();

		return result.get(0);
	}

}
