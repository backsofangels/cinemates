package com.salvatore.cinemates.model;

public class Review {
	private long reviewId;
	private int rating;
	private String description;
	private int tmdbMovieId;
	private User user;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
