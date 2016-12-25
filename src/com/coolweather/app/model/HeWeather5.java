package com.coolweather.app.model;

import java.util.List;

public class HeWeather5 {
	/*
	 {
            "basic":Object{...},
            "daily_forecast":[
                Object{...},
                Object{...},
                Object{...}
            ],
            "status":"ok"
        }
	 * */
	private Basic basic;
	private List<DailyForecast> daily_forecast;
	private String status;
	public Basic getBasic() {
		return basic;
	}
	public void setBasic(Basic basic) {
		this.basic = basic;
	}
	public List<DailyForecast> getDaily_forecast() {
		return daily_forecast;
	}
	public void setDaily_forecast(List<DailyForecast> daily_forecast) {
		this.daily_forecast = daily_forecast;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
