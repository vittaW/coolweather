package com.coolweather.app.activity;

import android.app.Activity;
import android.os.Bundle;

public class BaseActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ActivityCollections.addActivity(this);
	}
	
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		ActivityCollections.removeActivity(this);
	}
	
}
