package com.salvatore.cinemates.dto;

public class ReviewDto {
    private long id;
    private int rating;
    private String description;
    private int movieId;
    private String cinematesUserUsername;

    public ReviewDto() {}

    public ReviewDto(int rating, String description, int movieId, String cinematesUserUsername) {
        this.rating = rating;
        this.description = description;
        this.movieId = movieId;
        this.cinematesUserUsername = cinematesUserUsername;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getCinematesUserUsername() {
        return cinematesUserUsername;
    }

    public void setCinematesUserUsername(String cinematesUserUsername) {
        this.cinematesUserUsername = cinematesUserUsername;
    }
}
