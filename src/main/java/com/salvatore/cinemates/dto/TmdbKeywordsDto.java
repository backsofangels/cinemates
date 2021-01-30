package com.salvatore.cinemates.dto;

import java.util.List;

import com.salvatore.cinemates.model.Keyword;

public class TmdbKeywordsDto {
	private int id;
	private List<Keyword> keywords;

	public int getId() {
		return id;
	}

	public void setId(int id) { 
		this.id = id;
	}

	public List<Keyword> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<Keyword> keywords) {
		this.keywords = keywords;
	}

}
