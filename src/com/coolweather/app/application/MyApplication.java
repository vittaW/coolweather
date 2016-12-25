package com.coolweather.app.application;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
	private static Context context;

	@Override
	public void onCreate() {
		super.onCreate();
		// 获取全局的context对象 
		context = getApplicationContext();
	}
	public static Context getContext() {
		return context;
	}

}
