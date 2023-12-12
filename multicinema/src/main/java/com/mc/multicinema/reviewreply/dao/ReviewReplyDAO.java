package com.mc.multicinema.reviewreply.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mc.multicinema.reviewreply.dto.ReviewReplyDTO;
import com.mc.multicinema.reviewreply.dto.ReviewReplyWithLikeDTO;
import com.mc.multicinema.reviewreply.dto.WritenReplyDTO;

@Repository
public class ReviewReplyDAO {
	
	@Autowired
	SqlSession session;
	
	public ReviewReplyDTO selectReplyOne(int reply_num) {
		return session.selectOne("reply.selectReplyOne", reply_num);
	}
	
	public List<ReviewReplyDTO> selectReplyList(int board_num) {
		return session.selectList("reply.selectReplyList", board_num);
	}
	
	public List<ReviewReplyWithLikeDTO> selectReplyLikeList(HashMap<String, String> map) {
		return session.selectList("reply.selectReplyLikeList", map);
	}

	public void insertReply(WritenReplyDTO reply) {
		session.insert("insertReply", reply);
	}
	
//	public int insertReply(ReviewReplyDTO dto) {
//		return session.insert("ReviewReplyDTO", dto);
//	}
	
 
}
