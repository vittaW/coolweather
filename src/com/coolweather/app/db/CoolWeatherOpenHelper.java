package com.coolweather.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CoolWeatherOpenHelper extends SQLiteOpenHelper {

	/**
	 * Province�������
	 */
	private static final String CREATE_PROVINCE = 
			"create table Province(_id integer primary key autoincrement,"
									+ "province_name varchar(10),"
									+ "province_code varchar(10))";
	
	/**City�������
	 * 
	 */
	private static final String CREATE_CITY = 
			"create table City(_id integer primary key autoincrement,"
									+ "city_name varchar(10),"
									+ "city_code varchar(10),"
									+ "province_id integer(10))";
	
	/**
	 * Country�������
	 */
	private static final String CREATE_COUNTRY = 
			"create table Country(_id integer primary key autoincrement,"
									+ "country_name varchar(10),"
									+ "country_code varchar(10),"
									+ "city_id integer(10))";
	
	public CoolWeatherOpenHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_PROVINCE);//����Province��
		db.execSQL(CREATE_CITY);//����City��
		db.execSQL(CREATE_COUNTRY);//����Country��
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

}
