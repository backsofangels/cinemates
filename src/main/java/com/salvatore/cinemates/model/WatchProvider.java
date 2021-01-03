package com.salvatore.cinemates.model;

public class WatchProvider {
	private int displayPriority;
	private String logoPicturePath;
	private int providerid;
	private String providerName;
	private String locale;

	public int getDisplayPriority() {
		return displayPriority;
	}

	public void setDisplayPriority(int displayPriority) {
		this.displayPriority = displayPriority;
	}

	public String getLogoPicturePath() {
		return logoPicturePath;
	}

	public void setLogoPicturePath(String logoPicturePath) {
		this.logoPicturePath = logoPicturePath;
	}

	public int getProviderid() {
		return providerid;
	}

	public void setProviderid(int providerid) {
		this.providerid = providerid;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

}
