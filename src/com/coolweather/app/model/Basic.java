package com.coolweather.app.model;

public class Basic {
	/*
	 "basic":{
                "city":"北京",
                "cnty":"中国",
                "id":"CN101010100",
                "lat":"39.904000",
                "lon":"116.391000",
                "update":Object{...}
	 */
	private String city;
	private String cnty;
	private String id;
	private String lat;
	private String lon;
	private Update update;
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCnty() {
		return cnty;
	}
	public void setCnty(String cnty) {
		this.cnty = cnty;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public Update getUpdate() {
		return update;
	}
	public void setUpdate(Update update) {
		this.update = update;
	}
	
}
