package com.salvatore.cinemates.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Review {
	@Id
	@GeneratedValue
	private long reviewId;
	private int rating;
	private String description;
	private int tmdbMovieId;
	private CinematesUser cinematesUser;

	public long getReviewId() {
		return reviewId;
	}

	public void setReviewId(long reviewId) {
		this.reviewId = reviewId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getTmdbMovieId() {
		return tmdbMovieId;
	}

	public void setTmdbMovieId(int tmdbMovieId) {
		this.tmdbMovieId = tmdbMovieId;
	}

	public CinematesUser getUser() {
		return cinematesUser;
	}

	public void setUser(CinematesUser cinematesUser) {
		this.cinematesUser = cinematesUser;
	}

}
