package com.coolweather.app.model;

public class City {
	private int _id;
	private String cityName;
	private String cityCode;
	private int provinceId;
	
	public City(int _id, String cityName, String cityCode, int provinceId) {
		super();
		this._id = _id;
		this.cityName = cityName;
		this.cityCode = cityCode;
		this.provinceId = provinceId;
	}
	
	public City(){}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public int getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

	@Override
	public String toString() {
		return "City [_id=" + _id + ", cityName=" + cityName + ", cityCode=" + cityCode + ", provinceId=" + provinceId
				+ "]";
	}
	
}
