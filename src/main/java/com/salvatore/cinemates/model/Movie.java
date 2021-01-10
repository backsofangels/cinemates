package com.salvatore.cinemates.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class Movie {
	@JsonProperty("id")
	private int tmbdId;
	@JsonProperty("title")
	private String title;
	@JsonProperty("original_language")
	private String originalLanguage;
	@JsonProperty("original_title")
	private String originalTitle;
	@JsonProperty("poster_path")
	private String posterImagePath;
	@JsonProperty("release_date")
	private Date releaseDate;
	@JsonProperty("status")
	private String status;
	@JsonProperty("keywords")
	private List<String> keywords;
	@JsonProperty("director")
	private Person director;
	@JsonProperty("actors")
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

	@Override
	public String toString() {
		return "Movie [tmbdId=" + tmbdId + ", title=" + title + ", originalLanguage=" + originalLanguage
				+ ", originalTitle=" + originalTitle + ", posterImagePath=" + posterImagePath + ", releaseDate="
				+ releaseDate + ", status=" + status + ", keywords=" + keywords + ", director=" + director + ", actors="
				+ actors + "]";
	}

	
}
