package com.coolweather.app.activity;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

import com.coolweather.app.R;
import com.coolweather.app.db.CoolWeatherDB;
import com.coolweather.app.model.City;
import com.coolweather.app.util.CityComparator;
import com.coolweather.app.view.OnTouchintLetterChangedListener;
import com.coolweather.app.view.SideBar;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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

public class ChooseAreaActivity extends Activity {
	private EditText choosedCity_et;
	private TextView title_tv;
	private ListView city_lv;
	private CoolWeatherDB coolWeatherDB;
	private SideBar sideBar;
	private TextView text_dialog;
	private List<City> listCity;
	private CityComparator cityComparator;
	private CityAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_choosearea);
		text_dialog = (TextView) findViewById(R.id.text_dialog);
		choosedCity_et = (EditText) findViewById(R.id.choosedCity_et);
		sideBar = (SideBar) findViewById(R.id.sidebar);
		title_tv = (TextView) findViewById(R.id.title_tv);
		title_tv.setText("请选择城市:");
		city_lv = (ListView) findViewById(R.id.city_lv);
		coolWeatherDB = CoolWeatherDB.getInstance(this);
		listCity = coolWeatherDB.listCity(null);
		cityComparator = new CityComparator();
		Collections.sort(listCity, cityComparator);
		
		adapter = new CityAdapter(listCity, this);
		city_lv.setAdapter(adapter);
		city_lv.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// 跳转去显示天气情况
				Toast.makeText(ChooseAreaActivity.this, listCity.get(position).getCityZh(), 0).show();
				//给editText设置显示
				choosedCity_et.setText(listCity.get(position).getCityZh());
				adapter.updateListView(listCity);
			}
		});
		
		
		//侧边栏sidebar 
		sideBar.setOnTouchintLetterChangedListener(new OnTouchintLetterChangedListener() {
			//回传被点击的字母
			@Override
			public void onTouchingLetterChanged(String letter) {
				//letter首字母首次出现的位置
				int sectionIndex = letter.charAt(0);
				int position = adapter.getPositionForSection(sectionIndex);
				if(position != -1){
					//跳转显示
					city_lv.setSelection(position);
				}
				//点击时显示textViewDialog
				sideBar.setTextView(text_dialog);
			}
		});
		
		
		/**
		 * EditText内容改变监听
		 */
		choosedCity_et.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				filterData(s.toString());
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
				filterData(s.toString());
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				filterData(s.toString());
			}
		});
		
	}
	
	/**根据输入框的值过滤数据并更新listView
	 * @param text 输入框中现存的text
	 */
	private void filterData(CharSequence text){
		//保存过滤后数据的新list
		List<City> filteredList = new ArrayList<City>();
		if(TextUtils.isEmpty(text)){
			//输入框为空,为list源数据
			filteredList = listCity;
		}else{
			//否则用text过滤数据
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
			//更新listView显示//BaseAdapter.notifyDataSetChanged()
			adapter.updateListView(filteredList);
		}
		
	}
}
