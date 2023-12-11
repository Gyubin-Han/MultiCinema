package com.mc.multicinema.likecount.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author JIN
 * 좋아요 여부를 확인하기 위한 dao이다. 5종류의 테이블에 접근한다.
 * 작동 방식은 테이블에 있는지 조회하고 있으면 false 리턴, 없으면 추가 후 true 리턴
 * 
 */
@Repository
public class LikeCountDAO {

	@Autowired
	SqlSession session;

	public boolean selectLikeBoard(HashMap<String, Integer> keys) {
		return session.selectOne("liketable.selectLikeBoard", keys) != null ? true : false;
	}

	public boolean selectLikeReply(HashMap<String, Integer> keys) {
		return session.selectOne("liketable.selectLikeReply", keys) != null ? true : false;
	}
	
	public boolean selectLikeComment(HashMap<String, Integer> keys) {
		return session.selectOne("liketable.selectLikeComment", keys) != null ? true : false;
	}
	
	public boolean selectDislikeBoard(HashMap<String, Integer> keys) {
		return session.selectOne("liketable.selectDislikeBoard", keys) != null ? true : false;
	}
	
	public boolean selectDislikeReply(HashMap<String, Integer> keys) {
		return session.selectOne("liketable.selectDislikeReply", keys) != null ? true : false;
	}

	public int insertLikeBoard(HashMap<String, Integer> keys) {
		return session.insert("liketable.insertLikeBoard", keys);
	}
	
	public int insertLikeReply(HashMap<String, Integer> keys) {
		return session.insert("liketable.insertLikeReply", keys);
	}
	
	public int insertLikeComment(HashMap<String, Integer> keys) {
		return session.insert("liketable.insertLikeComment", keys);
	}
	
	public int insertDislikeBoard(HashMap<String, Integer> keys) {
		return session.insert("liketable.insertDislikeBoard", keys);
	}
	
	public int insertDislikeReply(HashMap<String, Integer> keys) {
		return session.insert("liketable.insertDislikeReply", keys);
	}

	public int deleteLikeBoard(HashMap<String, Integer> keys) {
		return session.delete("liketable.deleteLikeBoard", keys);
	}
	
	public int deleteLikeReply(HashMap<String, Integer> keys) {
		return session.delete("liketable.deleteLikeReply", keys);
	}
	
	public int deleteLikeComment(HashMap<String, Integer> keys) {
		return session.delete("liketable.deleteLikeComment", keys);
	}
	
	public int deleteDislikeBoard(HashMap<String, Integer> keys) {
		return session.delete("liketable.deleteDislikeBoard", keys);
	}
	
	public int deleteDislikeReply(HashMap<String, Integer> keys) {
		return session.delete("liketable.deleteDislikeReply", keys);
	}
}
