package com.coolweather.app.model;

public class Province {
	private int _id;
	private String provinceName;
	private String provinceCode;
	
	public Province(int id, String provinceName, String provinceCode) {
		this._id = id;
		this.provinceName = provinceName;
		this.provinceCode = provinceCode;
	}
	
	public Province(){}

	public int getId() {
		return _id;
	}

	public void setId(int id) {
		this._id = id;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	@Override
	public String toString() {
		return "Province [_id=" + _id + ", provinceName=" + provinceName + ", provinceCode=" + provinceCode + "]";
	}

	
}
