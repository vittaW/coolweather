package com.coolweather.app.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import com.coolweather.app.db.CoolWeatherDB;
import com.coolweather.app.model.City;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JSONUtil {
	
	/**解析和保存服务器返回的City数据
	 * @param json
	 */
	public static void parserJSONCity(CoolWeatherDB coolWeatherDB,InputStream is){
		
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
		LogUtil.i("CoolWeather", "保存成功!");
	}
}
