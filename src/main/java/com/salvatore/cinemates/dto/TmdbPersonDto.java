package com.salvatore.cinemates.dto;

import java.util.List;

import com.salvatore.cinemates.model.Person;

public class TmdbPersonDto {
	private int page;
	private List<Person> results;
	private int total_pages;
	private int total_results;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public List<Person> getResults() {
		return results;
	}

	public void setResults(List<Person> results) {
		this.results = results;
	}

	public int getTotal_pages() {
		return total_pages;
	}

	public void setTotal_pages(int total_pages) {
		this.total_pages = total_pages;
	}

	public int getTotal_results() {
		return total_results;
	}

	public void setTotal_results(int total_results) {
		this.total_results = total_results;
	}

}
