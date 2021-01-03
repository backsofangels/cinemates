package com.salvatore.cinemates.model;

public class Person {
	private int personId;
	private String name;
	private int gender;
	private String alsoKnownAs;
	private String birthDay;
	private String deathDay;
	private String biography;
	private String placeOfBirth;
	private String profilePicturePath;
	private boolean adult;
	private String homepage;
	private double popularity;
	
	public int getPersonId() {
		return personId;
	}
	
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getGender() {
		return gender;
	}
	
	public void setGender(int gender) {
		this.gender = gender;
	}
	
	public String getAlsoKnownAs() {
		return alsoKnownAs;
	}
	
	public void setAlsoKnownAs(String alsoKnownAs) {
		this.alsoKnownAs = alsoKnownAs;
	}
	
	public String getBirthDay() {
		return birthDay;
	}
	
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
	
	public String getDeathDay() {
		return deathDay;
	}
	
	public void setDeathDay(String deathDay) {
		this.deathDay = deathDay;
	}
	
	public String getBiography() {
		return biography;
	}
	
	public void setBiography(String biography) {
		this.biography = biography;
	}
	
	public String getPlaceOfBirth() {
		return placeOfBirth;
	}
	
	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}
	
	public String getProfilePicturePath() {
		return profilePicturePath;
	}
	
	public void setProfilePicturePath(String profilePicturePath) {
		this.profilePicturePath = profilePicturePath;
	}
	
	public boolean isAdult() {
		return adult;
	}
	
	public void setAdult(boolean adult) {
		this.adult = adult;
	}
	
	public String getHomepage() {
		return homepage;
	}
	
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	
	public double getPopularity() {
		return popularity;
	}
	
	public void setPopularity(double popularity) {
		this.popularity = popularity;
	}
}
