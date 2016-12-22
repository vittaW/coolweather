package com.coolweather.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CoolWeatherOpenHelper extends SQLiteOpenHelper {

	/**City建表语句
	 * 
	 */
	private static final String CREATE_CITY = 
			"create table City(_id integer primary key autoincrement,"
									+ "city_id varchar(10),"
									+ "city_en varchar(10),"
									+ "city_zh varchar(10),"
									+ "country_code varchar(10),"
									+ "country_en varchar(10),"
									+ "country_zh varchar(10),"
									+ "province_en varchar(10),"
									+ "province_zh varchar(10),"
									+ "leader_en varchar(10),"
									+ "leader_zh varchar(10),"
									+ "lat double(10),"
									+ "lon double(10)"
									+ ")";
	
	public CoolWeatherOpenHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_CITY);//创建City表
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

}
