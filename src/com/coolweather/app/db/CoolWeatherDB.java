package com.coolweather.app.db;

import java.util.ArrayList;
import java.util.List;

import com.coolweather.app.model.City;
import com.coolweather.app.model.Country;
import com.coolweather.app.model.Province;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class CoolWeatherDB {
	
	private static CoolWeatherDB coolWeatherDB;
	private String DBName = "cool_weather.db";
	private int version = 1;
	private SQLiteDatabase db;
	
	/**
	 * 构造方法私有化
	 */
	private CoolWeatherDB(Context context){
		CoolWeatherOpenHelper oh = new CoolWeatherOpenHelper(context, DBName, null, version);
		db = oh.getWritableDatabase();
	}
	
	/**
	 * 获取单例CoolWeatherDB的实例
	 */
	public synchronized static CoolWeatherDB getInstance(Context context){
		if(coolWeatherDB == null){
			coolWeatherDB = new CoolWeatherDB(context);
		}
		return coolWeatherDB;
	}
	
	/**
	 * 将Province实例保存到数据库
	 */
	public void saveProvince(Province province){
		if(province != null){
			ContentValues values = new ContentValues();
			values.put("province_name", province.getProvinceName());
			values.put("province_code", province.getProvinceCode());
			db.insert("Province", null, values);
		}
	}
	
	/**
	 * 查询数据库中所有的Province实例
	 */
	public List<Province> listProvince(){
		List<Province> list = new ArrayList<Province>();
		Cursor cursor = db.query("Province", null, null, null, null, null, null);
		Province province = null;
		while(cursor.moveToNext()){
			province = new Province();
			province.setId(cursor.getInt(0));
			province.setProvinceName(cursor.getString(1));
			province.setProvinceCode(cursor.getString(2));
			list.add(province);
		}
		return list ;
	}
	
	/**
	 * 将City实例保存到数据库
	 */
	public void saveCity(City city){
		if(city != null){
			ContentValues values = new ContentValues();
			values.put("city_name",city.getCityName());
			values.put("city_code",city.getCityCode());
			values.put("province_id", city.getProvinceId());
			long insert = db.insert("City", null, values);
			Log.d("CoolWeather", insert+"");
		}
	}
	
	/**
	 * 查询数据库中所有provinceId对应的City实例
	 */
	public List<City> listCity(int provinceId){
		List<City> list = new ArrayList<City>();
		Cursor cursor = db.query("City", null, 
				"province_id = ?", new String[]{String.valueOf(provinceId)}, null, null, null);
		City city = null;
		while(cursor.moveToNext()){
			city = new City();
			city.set_id(cursor.getInt(cursor.getInt(cursor.getColumnIndex("_id"))));
			city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
			city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
			city.setProvinceId(provinceId);
			list.add(city);
		}
		return list ;
	}
	
	/**
	 * 将Country实例保存到数据库
	 */
	public void saveCountry(Country country){
		if(country != null){
			ContentValues values = new ContentValues();
			values.put("country_code", country.getCountryCode());
			values.put("country_name", country.getCountryName());
			values.put("city_id", country.getCityId());
			db.insert("Country", null, values);
		}
	}
	
	/**
	 * 查询数据库中所有cityId对应的Country
	 */
	public List<Country> listCountry(int cityId){
		
		List<Country> list = new ArrayList<Country>();
		Cursor cursor = db.query("Country", null, 
				"city_id =?", new String[]{String.valueOf(cityId)}, null, null, null);
		Country country = null;
		while(cursor.moveToNext()){
			country = new Country();
			country.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
			country.setCountryCode(cursor.getString(cursor.getColumnIndex("country_code")));
			country.setCountryName(cursor.getString(cursor.getColumnIndex("country_name")));
			country.setCityId(cityId);
			list.add(country);
		}
		return list;
	}
	
}
