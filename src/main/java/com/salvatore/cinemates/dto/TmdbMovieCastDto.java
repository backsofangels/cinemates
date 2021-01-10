package com.salvatore.cinemates.dto;

import java.util.List;

import com.salvatore.cinemates.model.Person;

public class TmdbMovieCastDto {
	private int id;
	private List<Person> cast;
	private List<Person> crew;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Person> getCast() {
		return cast;
	}

	public void setCast(List<Person> cast) {
		this.cast = cast;
	}

	public List<Person> getCrew() {
		return crew;
	}

	public void setCrew(List<Person> crew) {
		this.crew = crew;
	}
}
