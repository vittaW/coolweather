package com.coolweather.app.activity;

import java.io.InputStream;
import java.util.List;

import com.coolweather.app.R;
import com.coolweather.app.db.CoolWeatherDB;
import com.coolweather.app.model.Basic;
import com.coolweather.app.model.City;
import com.coolweather.app.model.DailyForecast;
import com.coolweather.app.model.HeWeather5;
import com.coolweather.app.model.JsonWeather;
import com.coolweather.app.model.Wind;
import com.coolweather.app.util.HttpBrCallbackListener;
import com.coolweather.app.util.HttpUtil;
import com.coolweather.app.util.JSONUtil;
import com.coolweather.app.util.LogUtil;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Window;
import android.widget.TextView;

/**显示天气信息的activity
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
public class WeatherActivity extends BaseActivity {
	
	private static final int TAG_JSONWEATHER_SUCCESS = 1;
	protected static final int TAG_JSONWEATHER_FAIL = 2;
	private static final String V5_WEATHER_INTERFACE = "https://free-api.heweather.com/v5/";
	private TextView cityInfo;
	private TextView weatherInfo;
	private JsonWeather jsonWeather;
	private Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			LogUtil.v("CoolWeather", "Handler-msg:"+msg);
			if(msg.what == TAG_JSONWEATHER_SUCCESS){
				showInfo(jsonWeather);
				LogUtil.v("CoolWeather", "WeatherActivity-jsonWeather解析成功!");
			}else if(msg.what == TAG_JSONWEATHER_FAIL){
				LogUtil.v("CoolWeather", "WeatherActivity-解析失败!");
			}
		};
	};
	private CoolWeatherDB coolWeatherDB;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_weather);
		initView();
		
		//拿到启动自己的intent,拿到传过来的city对象
		Intent intent = getIntent();
		City city = intent.getParcelableExtra("city");
		
		String address = appendCityAddress(city.getId());
		getWeatherInfo(address);//拿到天气信息类JsonWeather
	}

	/**显示天气信息(在textView里)
	 * @param jsonWeather
	 */
	private void showInfo(JsonWeather jsonWeather) {
		HeWeather5 heWeather5 = jsonWeather.getHeWeather5().get(0);
		Basic basic = heWeather5.getBasic();
		coolWeatherDB = CoolWeatherDB.getInstance(this);
		List<City> cityList = coolWeatherDB.listCity(basic.getId());
		City city = cityList.get(0);
		cityInfo.setText(city.getCountryZh()+"-"+city.getProvinceZh()+"-"+city.getLeaderZh()+"-"+city.getCityZh());
		List<DailyForecast> list_dailyForecast = heWeather5.getDaily_forecast();
		DailyForecast firstDailyForecast = list_dailyForecast.get(0);
		
		StringBuilder weatherStr = new StringBuilder();
		weatherStr.append("今日:"+firstDailyForecast.getDate()+"\n");
		weatherStr.append("温度:"+firstDailyForecast.getTmp().getMin()+"~"+firstDailyForecast.getTmp().getMax()+"\n");
		weatherStr.append("湿度:"+firstDailyForecast.getHum()+"\n");
		weatherStr.append("降水量:"+firstDailyForecast.getPcpn()+"\n");
		weatherStr.append("降水概率:"+firstDailyForecast.getPop()+"\n");
		weatherStr.append("可见度:"+firstDailyForecast.getVis()+"\n");
		weatherStr.append("白天天气描述:"+firstDailyForecast.getCond().getTxt_d()+"\n");
		weatherStr.append("夜间天气描述:"+firstDailyForecast.getCond().getTxt_n()+"\n");
		Wind wind = firstDailyForecast.getWind();
		weatherStr.append(wind.getDir()+wind.getSc()+"级,风速"+wind.getSpd()+"\n");
		String status = heWeather5.getStatus();
		weatherStr.append("状态"+status);
		weatherInfo.setText(weatherStr);
	}

	/**根据不同城市拼接访问地址
	 * @return
	 */
	private String appendCityAddress(String cityId) {
//		"https://free-api.heweather.com/v5/forecast?city=beijing&key=3ba2d26546c44c0ead1f450c6cbd73e9";
		return V5_WEATHER_INTERFACE+"forecast?city="+cityId+"&key=3ba2d26546c44c0ead1f450c6cbd73e9";
	}

	/**
	 * 网络请求天气数据,解析数据
	 */
	private void getWeatherInfo(String address) {
		HttpUtil.sendHttpRequest(address, new HttpBrCallbackListener() {
			
			@Override
			public void onFinish(InputStream is) {
				LogUtil.v("CoolWeather", "HttpBrCallbackListener-is:"+is);
				jsonWeather = JSONUtil.handleJSONWeather(is);
				LogUtil.v("CoolWeather", "HttpBrCallbackListener-jsonWeather:"+jsonWeather);
				
				Message msg = new Message();
				if(jsonWeather != null){
					//解析成功
					msg.what = TAG_JSONWEATHER_SUCCESS;
					LogUtil.v("CoolWeather", "HttpBrCallbackListener-msg:解析成功!");
				}else{
					//解析失败
					msg.what = TAG_JSONWEATHER_FAIL;
					LogUtil.v("CoolWeather", "HttpBrCallbackListener-msg:解析失败!");
				}
				boolean sendMessage = handler.sendMessage(msg);
				LogUtil.v("CoolWeather", "HttpBrCallbackListener-sendMessage:"+sendMessage);
			}
			
			@Override
			public void onError(Exception e) {
				// TODO Auto-generated method stub
				LogUtil.e("CoolWeather", "网络访问错误!"+e);
			}
		});
	}

	private void initView() {
		// TODO Auto-generated method stub
		cityInfo = (TextView) findViewById(R.id.cityInfo_tv);
		weatherInfo = (TextView) findViewById(R.id.weatherInfo_tv);
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		ActivityCollections.finishAll();
	}
}
