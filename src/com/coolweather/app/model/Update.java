package com.coolweather.app.model;

public class Update {
	/*"update":{
                    "loc":"2016-12-23 18:50",
                    "utc":"2016-12-23 10:50"
                }*/
	private String loc;
	private String utc;
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public String getUtc() {
		return utc;
	}
	public void setUtc(String utc) {
		this.utc = utc;
	}
}
