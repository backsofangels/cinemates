package com.salvatore.cinemates.model;

import java.util.Date;
import java.util.List;

public class Movie {
	private int tmbdId;
	private String title;
	private String originalLanguage;
	private String originalTitle;
	private String posterImagePath;
	private Date releaseDate;
	private String status;
	private List<String> keywords;
	private Person director;
	private List<Person> actors;

	public int getTmbdId() {
		return tmbdId;
	}

	public void setTmbdId(int tmbdId) {
		this.tmbdId = tmbdId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOriginalLanguage() {
		return originalLanguage;
	}

	public void setOriginalLanguage(String originalLanguage) {
		this.originalLanguage = originalLanguage;
	}

	public String getOriginalTitle() {
		return originalTitle;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}

	public String getPosterImagePath() {
		return posterImagePath;
	}

	public void setPosterImagePath(String posterImagePath) {
		this.posterImagePath = posterImagePath;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

	public Person getDirector() {
		return director;
	}

	public void setDirector(Person director) {
		this.director = director;
	}

	public List<Person> getActors() {
		return actors;
	}

	public void setActors(List<Person> actors) {
		this.actors = actors;
	}
	
	public void addKeyword(String keyword) {
		this.keywords.add(keyword);
	}
	
	public void addActor(Person actor) {
		this.actors.add(actor);
	}

}
