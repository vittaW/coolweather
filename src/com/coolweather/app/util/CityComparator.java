package com.coolweather.app.util;

import java.util.Comparator;

import com.coolweather.app.model.City;

public class CityComparator implements Comparator<City>{

	@Override
	public int compare(City lhs, City rhs) {
		int i = 0;
		i = lhs.getCityEn().compareTo(rhs.getCityEn());
		if(i == 0){//����ĸ��ͬ,�Ƚϵڶ�����ĸ
			return -1;
		}
		return i;
	}

}
