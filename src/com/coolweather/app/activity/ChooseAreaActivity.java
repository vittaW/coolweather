package com.coolweather.app.activity;

import java.io.InputStream;
import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

import com.coolweather.app.R;
import com.coolweather.app.db.CoolWeatherDB;
import com.coolweather.app.model.City;
import com.coolweather.app.util.CityComparator;
import com.coolweather.app.util.HttpBrCallbackListener;
import com.coolweather.app.util.HttpUtil;
import com.coolweather.app.util.JSONUtil;
import com.coolweather.app.util.LogUtil;
import com.coolweather.app.view.OnTouchintLetterChangedListener;
import com.coolweather.app.view.SideBar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ChooseAreaActivity extends BaseActivity {
	private static final int SAVE_TABLE_CITY = 0;
	private EditText choosedCity_et;
	private TextView title_tv;
	private ListView city_lv;
	private CoolWeatherDB coolWeatherDB;
	private SideBar sideBar;
	private TextView text_dialog;
	private List<City> listCity;
	private CityComparator cityComparator;
	private CityAdapter adapter;
	private List<City> filteredList;
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			if(msg.what == SAVE_TABLE_CITY){
				setListVeiwAdapter();
			}
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_choosearea);
		initView();
		//��������json��,������������ݿ��
//		initCityTable();
		//���߳̽������������city����setAdapter��ʾlistVeiw����
		setListVeiwAdapter();
		listenerSideBar();
		listenEditText();
	}

	private void listenEditText() {
		/**
		 * EditText���ݸı����
		 */
		choosedCity_et.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				if(TextUtils.isEmpty(choosedCity_et.getText())){
					filteredList = listCity;
				}else{
					filterData(s.toString());
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
				if(TextUtils.isEmpty(choosedCity_et.getText())){
					filteredList = listCity;
				}else{
					filterData(s.toString());
				}
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if(TextUtils.isEmpty(choosedCity_et.getText())){
					filteredList = listCity;
				}else{
					filterData(s.toString());
				}
			}
		});
	}

	private void listenerSideBar() {
		//�����sidebar 
		sideBar.setOnTouchintLetterChangedListener(new OnTouchintLetterChangedListener() {
			//�ش����������ĸ
			@Override
			public void onTouchingLetterChanged(String letter) {
				//letter����ĸ�״γ��ֵ�λ��
				int sectionIndex = letter.charAt(0);
				int position = adapter.getPositionForSection(sectionIndex);
				if(position != -1){
					//��ת��ʾ
					city_lv.setSelection(position);
				}
				//���ʱ��ʾtextViewDialog
				sideBar.setTextView(text_dialog);
			}
		});
	}

	/**
	 * ��listView����adapter,���������
	 */
	private void setListVeiwAdapter() {
		coolWeatherDB = CoolWeatherDB.getInstance(this);
		listCity = coolWeatherDB.listCity(null);
		LogUtil.e("CoolWeather", "listCity[1]:"+listCity.get(1));
		cityComparator = new CityComparator();
		Collections.sort(listCity, cityComparator);
		
		adapter = new CityAdapter(listCity, this);
		city_lv.setAdapter(adapter);
		city_lv.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// ��תȥ��ʾ�������
				if(filteredList == null){
					filteredList = listCity;
				}
				Intent intent = new Intent(ChooseAreaActivity.this, WeatherActivity.class);
				intent.putExtra("city", filteredList.get(position));
				startActivity(intent);
				LogUtil.v("CoolWeather", filteredList.get(position).getCityZh());
			}
		});
	}

	/**
	 * ��ʼ������view���
	 */
	private void initView() {
		text_dialog = (TextView) findViewById(R.id.text_dialog);
		choosedCity_et = (EditText) findViewById(R.id.choosedCity_et);
		sideBar = (SideBar) findViewById(R.id.sidebar);
		title_tv = (TextView) findViewById(R.id.title_tv);
		title_tv.setText("��ѡ�����:");
		city_lv = (ListView) findViewById(R.id.city_lv);
	}

	/**����������ֵ�������ݲ�����listView
	 * @param text ��������ִ��text
	 */
	private void filterData(CharSequence text){
		filteredList = new ArrayList<City>();
		if(TextUtils.isEmpty(text)){
			//�����Ϊ��,ΪlistԴ����
			filteredList = listCity;
		}else{
			//������text��������
			filteredList.clear();
			for(City city : listCity){
				String cityEn = city.getCityEn();
				
				if(cityEn.toUpperCase().indexOf(text.toString().toUpperCase()) != -1
						||
						city.getCityZh().startsWith(text.toString())){
					filteredList.add(city);
				}
			}
			Collections.sort(filteredList, cityComparator);
			//����listView��ʾ//BaseAdapter.notifyDataSetChanged()
			adapter.updateListView(filteredList);
			//???????���º��listView�б�position����ԭ����λ��?????
			
		}
	}

	/**
	 * ���������������,����json��,���浽city����
	 */
	private void initCityTable() {
		coolWeatherDB = CoolWeatherDB.getInstance(this);
		String address = "http://files.heweather.com/china-city-list.json";
		// TODO Auto-generated method stub
		HttpUtil.sendHttpRequest(address, new HttpBrCallbackListener() {
			
			@Override
			public void onFinish(InputStream is) {
				JSONUtil.handleJSONCity(coolWeatherDB, is);
				//�������߳����ݿ�洢���,���Խ�������Ķ�ȡ���ݿ����
				handler.sendEmptyMessage(SAVE_TABLE_CITY);
			}
			
			@Override
			public void onError(Exception e) {
			}
		});
	}
}
