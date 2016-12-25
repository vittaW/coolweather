package com.coolweather.app.model;

public class Wind {
	/*
	 "wind":{
                        "deg":"133",
                        "dir":"南风",
                        "sc":"微风",
                        "spd":"8"
                    }
	 * */
	private String deg;//风角度
	private String dir;//风向
	private String sc;//风力等级
	private String spd;//风速speed
	public String getDeg() {
		return deg;
	}
	public void setDeg(String deg) {
		this.deg = deg;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	public String getSc() {
		return sc;
	}
	public void setSc(String sc) {
		this.sc = sc;
	}
	public String getSpd() {
		return spd;
	}
	public void setSpd(String spd) {
		this.spd = spd;
	}
	
}
