package com.salvatore.cinemates.model;

import java.util.Comparator;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Person {
	@JsonAlias("id")
	private int personId;
	private String name;
	private int gender;
	@JsonAlias("birthday")
	private String birthDay;
	@JsonAlias("deathday")
	private String deathDay;
	private String biography;
	@JsonAlias("place_of_birth")
	private String placeOfBirth;
	@JsonAlias("profile_path")
	private String profilePicturePath;
	private boolean adult;
	private String homepage;
	private double popularity;
	@JsonAlias("known_for_department")
	private String knownForDepartment;
	
	private String job;
	
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

	public String getKnownForDepartment() {
		return knownForDepartment;
	}

	public void setKnownForDepartment(String knownForDepartment) {
		this.knownForDepartment = knownForDepartment;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	@Override
	public String toString() {
		return "Person [personId=" + personId + ", name=" + name + ", gender=" + gender + ", birthDay=" + birthDay
				+ ", deathDay=" + deathDay + ", biography=" + biography + ", placeOfBirth=" + placeOfBirth
				+ ", profilePicturePath=" + profilePicturePath + ", adult=" + adult + ", homepage=" + homepage
				+ ", popularity=" + popularity + ", knownForDepartment=" + knownForDepartment + ", job=" + job + "]";
	}
	
	public static class PopularityComparator implements Comparator<Person> {
		@Override
		public int compare (Person firstPerson, Person secondPerson) {
			return Double.compare(firstPerson.getPopularity(), secondPerson.getPopularity());
		}
	}
}
