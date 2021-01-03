package com.salvatore.cinemates.model;

public class ProductionCompany {
	private long tmdbId;
	private String name;
	private String logoImagePath;
	private String countryOrigin;

	public long getTmdbId() {
		return tmdbId;
	}

	public void setTmdbId(long tmdbId) {
		this.tmdbId = tmdbId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogoImagePath() {
		return logoImagePath;
	}

	public void setLogoImagePath(String logoImagePath) {
		this.logoImagePath = logoImagePath;
	}

	public String getCountryOrigin() {
		return countryOrigin;
	}

	public void setCountryOrigin(String countryOrigin) {
		this.countryOrigin = countryOrigin;
	}

}
