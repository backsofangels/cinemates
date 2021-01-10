package com.salvatore.cinemates.dto;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.salvatore.cinemates.model.Movie;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TmdbMovieDto {
	private int page;
	private ArrayList<Movie> results;
	private int total_pages;
	private int total_result;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public ArrayList<Movie> getResults() {
		return results;
	}

	public void setResults(ArrayList<Movie> results) {
		this.results = results;
	}

	public int getTotal_pages() {
		return total_pages;
	}

	public void setTotal_pages(int total_pages) {
		this.total_pages = total_pages;
	}

	public int getTotal_result() {
		return total_result;
	}

	public void setTotal_result(int total_result) {
		this.total_result = total_result;
	}

	@Override
	public String toString() {
		return "TmdbMovieDto [page=" + page + ", results=" + results + ", total_pages=" + total_pages
				+ ", total_result=" + total_result + "]";
	}
	
	
}
