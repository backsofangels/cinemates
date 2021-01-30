package com.salvatore.cinemates.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Movie {
	@JsonAlias("id")
	private int tmbdId;
	private String title;
	@JsonAlias("original_language")
	private String originalLanguage;
	@JsonAlias("original_title")
	private String originalTitle;
	@JsonAlias("poster_path")
	private String posterImagePath;
	@JsonAlias("release_date")
	private Date releaseDate;
	private String status;
	private List<Keyword> keywords = new ArrayList<>();
	private List<Person> directors = new ArrayList<>();
	private List<Person> actors = new ArrayList<>();
	private List<Person> producers = new ArrayList<>();
	
	//TODO: releaseDate viene restituito in epoch time, convertirlo in stringa leggibile oppure farlo fare al f-e

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

	public List<Keyword> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<Keyword> keywords) {
		this.keywords = keywords;
	}
	
	public void addKeyword(Keyword keyword) {
		this.keywords.add(keyword);
	}

	public List<Person> getActors() {
		return actors;
	}

	public void setActors(List<Person> actors) {
		this.actors = actors;
	}
	
	public void addActor(Person actor) {
		this.actors.add(actor);
	}

	public List<Person> getDirectors() {
		return directors;
	}

	public void setDirectors(List<Person> directors) {
		this.directors = directors;
	}
	
	public void addDirector(Person director) {
		this.directors.add(director);
	}

	public List<Person> getProducers() {
		return producers;
	}

	public void setProducers(List<Person> producers) {
		this.producers = producers;
	}
	
	public void addProducer(Person p) {
		this.producers.add(p);
	}

	@Override
	public String toString() {
		return "Movie [tmbdId=" + tmbdId + ", title=" + title + ", originalLanguage=" + originalLanguage
				+ ", originalTitle=" + originalTitle + ", posterImagePath=" + posterImagePath + ", releaseDate="
				+ releaseDate + ", status=" + status + ", keywords=" + keywords + ", directors=" + directors
				+ ", actors=" + actors + ", producers=" + producers + "]";
	}
	
}
