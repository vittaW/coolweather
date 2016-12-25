package com.coolweather.app.db;

import java.util.ArrayList;
import java.util.List;
import com.coolweather.app.model.City;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
	 * 将City实例保存到数据库
	 */
	public void saveCity(City city){
		if(city != null){
			ContentValues values = new ContentValues();
			values.put("id", city.getId());
			values.put("city_en", city.getCityEn());
			values.put("city_zh", city.getCityZh());
			values.put("country_code", city.getCountryCode());
			values.put("country_en", city.getCountryEn());
			values.put("country_zh", city.getCountryZh());
			values.put("province_en", city.getProvinceEn());
			values.put("province_zh", city.getProvinceZh());
			values.put("leader_en", city.getLeaderEn());
			values.put("leader_zh", city.getLeaderZh());
			values.put("lat", city.getLat());
			values.put("lon", city.getLon());
			db.insert("City", null, values); 
		}
	}
	
	/**查询数据库中所有cityId对应的City实例
	 * @param cityId 或null返回所有的数据
	 * @return 
	 */
	public List<City> listCity(String cityId){
		List<City> list = new ArrayList<City>();
		Cursor cursor = null;
		if(cityId != null){
			cursor = db.query("City", null, 
					"id = ?", new String[]{cityId}, null, null, null);
		}else{
			cursor = db.query("City", null, null, null, null, null, null);
		}
		City city = null;
		while(cursor.moveToNext()){
			city = new City();
			city.setId(cursor.getString(cursor.getColumnIndex("id")));
			city.setCityEn(cursor.getString(cursor.getColumnIndex("city_en")));
			city.setCityZh(cursor.getString(cursor.getColumnIndex("city_zh")));
			city.setCountryCode(cursor.getString(cursor.getColumnIndex("country_code")));
			city.setCountryEn(cursor.getString(cursor.getColumnIndex("country_en")));
			city.setCountryZh(cursor.getString(cursor.getColumnIndex("country_zh")));
			city.setProvinceEn(cursor.getString(cursor.getColumnIndex("province_en")));
			city.setProvinceZh(cursor.getString(cursor.getColumnIndex("province_zh")));
			city.setLeaderEn(cursor.getString(cursor.getColumnIndex("leader_en")));
			city.setLeaderZh(cursor.getString(cursor.getColumnIndex("leader_zh")));
			city.setLat(cursor.getString(cursor.getColumnIndex("lat")));
			city.setLon(cursor.getString(cursor.getColumnIndex("lon")));
			list.add(city);
		}
		return list ;
	}
}
