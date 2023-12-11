package com.mc.multicinema.likecount.service;

import java.util.HashMap;

public interface LikeCountService {
	
	boolean selectLikeBoard(HashMap<String, Integer> keys);
	boolean selectLikeReply(HashMap<String, Integer> keys);
	boolean selectLikeComment(HashMap<String, Integer> keys);
	boolean selectDislikeBoard(HashMap<String, Integer> keys);
	boolean selectDislikeReply(HashMap<String, Integer> keys);
	
	String insertLikeBoard(HashMap<String, Integer> keys);
	String insertLikeReply(HashMap<String, Integer> keys);
	String insertLikeComment(HashMap<String, Integer> keys);
	String insertDislikeBoard(HashMap<String, Integer> keys);
	String insertDislikeReply(HashMap<String, Integer> keys);
	
	String deleteLikeBoard(HashMap<String, Integer> keys);
	String deleteLikeReply(HashMap<String, Integer> keys);
	String deleteLikeComment(HashMap<String, Integer> keys);
	String deleteDislikeBoard(HashMap<String, Integer> keys);
	String deleteDislikeReply(HashMap<String, Integer> keys);
	
}
