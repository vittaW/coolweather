package com.coolweather.app.model;

public class Wind {
	/*
	 "wind":{
                        "deg":"133",
                        "dir":"�Ϸ�",
                        "sc":"΢��",
                        "spd":"8"
                    }
	 * */
	private String deg;//��Ƕ�
	private String dir;//����
	private String sc;//�����ȼ�
	private String spd;//����speed
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
