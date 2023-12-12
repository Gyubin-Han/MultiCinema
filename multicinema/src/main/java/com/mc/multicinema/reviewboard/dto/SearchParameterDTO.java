package com.mc.multicinema.reviewboard.dto;

import org.springframework.stereotype.Component;

@Component
public class SearchParameterDTO {
	String search;
	String keyword;
	int startIndex;
	int limit;
	
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	@Override
	public String toString() {
		return "search=" + search + ", keyword=" + keyword + ", startIndex=" + startIndex
				+ ", limit=" + limit;
	}
	
}
