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

/**��ʾ������Ϣ��activity
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
				LogUtil.v("CoolWeather", "WeatherActivity-jsonWeather�����ɹ�!");
			}else if(msg.what == TAG_JSONWEATHER_FAIL){
				LogUtil.v("CoolWeather", "WeatherActivity-����ʧ��!");
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
		
		//�õ������Լ���intent,�õ���������city����
		Intent intent = getIntent();
		City city = intent.getParcelableExtra("city");
		
		String address = appendCityAddress(city.getId());
		getWeatherInfo(address);//�õ�������Ϣ��JsonWeather
	}

	/**��ʾ������Ϣ(��textView��)
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
		weatherStr.append("����:"+firstDailyForecast.getDate()+"\n");
		weatherStr.append("�¶�:"+firstDailyForecast.getTmp().getMin()+"~"+firstDailyForecast.getTmp().getMax()+"\n");
		weatherStr.append("ʪ��:"+firstDailyForecast.getHum()+"\n");
		weatherStr.append("��ˮ��:"+firstDailyForecast.getPcpn()+"\n");
		weatherStr.append("��ˮ����:"+firstDailyForecast.getPop()+"\n");
		weatherStr.append("�ɼ���:"+firstDailyForecast.getVis()+"\n");
		weatherStr.append("������������:"+firstDailyForecast.getCond().getTxt_d()+"\n");
		weatherStr.append("ҹ����������:"+firstDailyForecast.getCond().getTxt_n()+"\n");
		Wind wind = firstDailyForecast.getWind();
		weatherStr.append(wind.getDir()+wind.getSc()+"��,����"+wind.getSpd()+"\n");
		String status = heWeather5.getStatus();
		weatherStr.append("״̬"+status);
		weatherInfo.setText(weatherStr);
	}

	/**���ݲ�ͬ����ƴ�ӷ��ʵ�ַ
	 * @return
	 */
	private String appendCityAddress(String cityId) {
//		"https://free-api.heweather.com/v5/forecast?city=beijing&key=3ba2d26546c44c0ead1f450c6cbd73e9";
		return V5_WEATHER_INTERFACE+"forecast?city="+cityId+"&key=3ba2d26546c44c0ead1f450c6cbd73e9";
	}

	/**
	 * ����������������,��������
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
					//�����ɹ�
					msg.what = TAG_JSONWEATHER_SUCCESS;
					LogUtil.v("CoolWeather", "HttpBrCallbackListener-msg:�����ɹ�!");
				}else{
					//����ʧ��
					msg.what = TAG_JSONWEATHER_FAIL;
					LogUtil.v("CoolWeather", "HttpBrCallbackListener-msg:����ʧ��!");
				}
				boolean sendMessage = handler.sendMessage(msg);
				LogUtil.v("CoolWeather", "HttpBrCallbackListener-sendMessage:"+sendMessage);
			}
			
			@Override
			public void onError(Exception e) {
				// TODO Auto-generated method stub
				LogUtil.e("CoolWeather", "������ʴ���!"+e);
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
