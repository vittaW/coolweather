package com.coolweather.app.model;

import android.os.Parcel;
import android.os.Parcelable;

public class City implements Parcelable {

    private String id;
    private String cityEn;
    private String cityZh;
    private String countryCode;
    private String countryEn;
    private String countryZh;
    private String provinceEn;
    private String provinceZh;
    private String leaderEn;
    private String leaderZh;
    private String lat;
    private String lon;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCityEn() {
		return cityEn;
	}
	public void setCityEn(String cityEn) {
		this.cityEn = cityEn;
	}
	public String getCityZh() {
		return cityZh;
	}
	public void setCityZh(String cityZh) {
		this.cityZh = cityZh;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getCountryEn() {
		return countryEn;
	}
	public void setCountryEn(String countryEn) {
		this.countryEn = countryEn;
	}
	public String getCountryZh() {
		return countryZh;
	}
	public void setCountryZh(String countryZh) {
		this.countryZh = countryZh;
	}
	public String getProvinceEn() {
		return provinceEn;
	}
	public void setProvinceEn(String provinceEn) {
		this.provinceEn = provinceEn;
	}
	public String getProvinceZh() {
		return provinceZh;
	}
	public void setProvinceZh(String provinceZh) {
		this.provinceZh = provinceZh;
	}
	public String getLeaderEn() {
		return leaderEn;
	}
	public void setLeaderEn(String leaderEn) {
		this.leaderEn = leaderEn;
	}
	public String getLeaderZh() {
		return leaderZh;
	}
	public void setLeaderZh(String leaderZh) {
		this.leaderZh = leaderZh;
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
	@Override
	public String toString() {
		return "City [id=" + id + ", cityEn=" + cityEn + ", cityZh=" + cityZh + ", countryCode=" + countryCode
				+ ", countryEn=" + countryEn + ", countryZh=" + countryZh + ", provinceEn=" + provinceEn
				+ ", provinceZh=" + provinceZh + ", leaderEn=" + leaderEn + ", leaderZh=" + leaderZh + ", lat=" + lat
				+ ", lon=" + lon + "]";
	}
	
	//City实现parcelable接口,可以使用intent传递对象
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// 写出
		dest.writeString(id);
		dest.writeString(cityEn);
		dest.writeString(cityZh);
		dest.writeString(countryCode);
		dest.writeString(countryEn);
		dest.writeString(countryZh);
		dest.writeString(provinceEn);
		dest.writeString(provinceZh);
		dest.writeString(leaderEn);
		dest.writeString(leaderZh);
		dest.writeString(lat);
		dest.writeString(lon);
	}
	
	public static final Parcelable.Creator<City> CREATOR = new Creator<City>() {
		
		@Override
		public City[] newArray(int size) {
			// TODO Auto-generated method stub
			return new City[size];
		}
		
		@Override
		public City createFromParcel(Parcel source) {
			City city = new City();
			//读取的顺序要跟写入的顺序相同
			city.setId(source.readString());
			city.setCityEn(source.readString());
			city.setCityZh(source.readString());
			city.setCountryCode(source.readString());
			city.setCountryEn(source.readString());
			city.setCountryZh(source.readString());
			city.setProvinceEn(source.readString());
			city.setProvinceZh(source.readString());
			city.setLeaderEn(source.readString());
			city.setLeaderZh(source.readString());
			city.setLat(source.readString());
			city.setLon(source.readString());
			return city;
		}
	};
	
}
