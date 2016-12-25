package com.coolweather.app.model;

public class DailyForecast {
	/*
	 {
                    "astro":Object{...},
                    "cond":Object{...},
                    "date":"2016-12-23",
                    "hum":"41",
                    "pcpn":"0.0",
                    "pop":"0",
                    "pres":"1031",
                    "tmp":Object{...},
                    "uv":"2",
                    "vis":"10",
                    "wind":Object{...}
                },
	 * */
	/**
	 * 天文数值
	 */
	private Astro astro;
	/**
	 * 天气状况
	 */
	private Cond cond;
	/**
	 * 今日时间
	 */
	private String date;
	/**
	 * 相对湿度humidity
	 */
	private String hum;
	/**
	 * 降水量
	 */
	private String pcpn;
	/**
	 * probability降水概率
	 */
	private String pop;
	/**
	 * pressure气压
	 */
	private String pres;
	private Tempreture tmp;
	private String uv;
	/**
	 * 可见度visible
	 */
	private String vis;
	private Wind wind;
	public Astro getAstro() {
		return astro;
	}
	public void setAstro(Astro astro) {
		this.astro = astro;
	}
	public Cond getCond() {
		return cond;
	}
	public void setCond(Cond cond) {
		this.cond = cond;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getHum() {
		return hum;
	}
	public void setHum(String hum) {
		this.hum = hum;
	}
	public String getPcpn() {
		return pcpn;
	}
	public void setPcpn(String pcpn) {
		this.pcpn = pcpn;
	}
	public String getPop() {
		return pop;
	}
	public void setPop(String pop) {
		this.pop = pop;
	}
	public String getPres() {
		return pres;
	}
	public void setPres(String pres) {
		this.pres = pres;
	}
	public Tempreture getTmp() {
		return tmp;
	}
	public void setTmp(Tempreture tmp) {
		this.tmp = tmp;
	}
	public String getUv() {
		return uv;
	}
	public void setUv(String uv) {
		this.uv = uv;
	}
	public String getVis() {
		return vis;
	}
	public void setVis(String vis) {
		this.vis = vis;
	}
	public Wind getWind() {
		return wind;
	}
	public void setWind(Wind wind) {
		this.wind = wind;
	}
	
}
