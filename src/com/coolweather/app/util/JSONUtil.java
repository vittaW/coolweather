package com.coolweather.app.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.List;
import com.coolweather.app.db.CoolWeatherDB;
import com.coolweather.app.model.City;
import com.coolweather.app.model.JsonWeather;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JSONUtil {
	
	/**�����ͱ�����������ص�City����
	 * @param json
	 */
	public static void handleJSONCity(CoolWeatherDB coolWeatherDB,InputStream is){
		
		Gson gson = new Gson();
		Type typeOfT = new TypeToken<List<City>>(){}.getType();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(is,"utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<City> list = gson.fromJson(br, typeOfT );
		if(list != null){
			LogUtil.i("CoolWeather", "list"+list.size());
		}else{
			LogUtil.i("CoolWeather", "list == null?"+(list==null));
		}
		for(City city : list){
			coolWeatherDB.saveCity(city);
		}
		LogUtil.i("CoolWeather", "����ɹ�!");
	}
	
	public static JsonWeather handleJSONWeather(InputStream is){
		Gson gson = new Gson();
		BufferedReader json = new BufferedReader(new InputStreamReader(is));
		JsonWeather fromJson = gson.fromJson(json, JsonWeather.class);
		if(fromJson != null){
			LogUtil.v("CoolWeather", "JsonUtil-������Ϣ�����ɹ�!fromJson != null");
		}else{
			LogUtil.v("CoolWeather", "JsonUtil-fromJson="+fromJson);
		}
		return fromJson;
	}
	
}
