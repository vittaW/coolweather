package com.coolweather.app.model;

public class Country {
	private int _id;
	private String countryName;
	private String countryCode;
	private int cityId;
	
	public Country(int _id, String countryName, String countryCode, int cityId) {
		super();
		this._id = _id;
		this.countryName = countryName;
		this.countryCode = countryCode;
		this.cityId = cityId;
	}
	
	public Country(){}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	@Override
	public String toString() {
		return "Country [_id=" + _id + ", countryName=" + countryName + ", countryCode=" + countryCode + ", cityId="
				+ cityId + "]";
	}
	
	
}
