package com.coolweather.app.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

public class ActivityCollections {
	public static List<Activity> list = new ArrayList<Activity>();

	public static void addActivity(Activity activity){
		if(activity != null && !list.contains(activity)){
			list.add(activity);
		}
	}
	
	public static void removeActivity(Activity activity){
		if(activity != null && list.contains(activity)){
			list.remove(activity);
		}
	}
	
	public static void finishAll(){
		for(Activity a : list){
			if(!a.isFinishing()){
				a.finish();
			}
		}
	}
}
