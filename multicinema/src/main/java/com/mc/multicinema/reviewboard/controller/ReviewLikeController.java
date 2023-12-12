package com.mc.multicinema.reviewboard.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mc.multicinema.likecount.service.LikeCountService;
import com.mc.multicinema.reviewboard.service.ReviewBoardService;

@Controller
public class ReviewLikeController {
	
	@Autowired
	LikeCountService likeService;
	@Autowired
	ReviewBoardService service;
	
	@RequestMapping("/likebtn")
	@ResponseBody
	public boolean likeBoardToggle(int board_num, int user_key) {
		HashMap<String, Integer> map = hashMapping(board_num, user_key);
		if(likeService.selectLikeBoard(map)) {
			likeService.deleteLikeBoard(map);
			service.minusLike(board_num);
			return false;
		} else {
			System.out.println("ch");
			likeService.insertLikeBoard(map);
			service.plusLike(board_num);
			return true;
		}
	}

	private HashMap<String, Integer> hashMapping(int board_num, int user_key) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("user_key", user_key);
		map.put("board_num", board_num);
		return map;
	}
	
}
