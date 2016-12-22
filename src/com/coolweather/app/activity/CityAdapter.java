package com.coolweather.app.activity;

import java.util.List;
import com.coolweather.app.R;
import com.coolweather.app.model.City;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

/**city列表的适配器
 * @author Administrator
 * enable fast scrolling between sections of an AbsListView
 */
@SuppressLint("DefaultLocale")
public class CityAdapter extends BaseAdapter implements SectionIndexer{

	private List<City> list;
	private Context context;
	
	public CityAdapter(List<City> list, Context context) {
		this.list = list;
		this.context = context;
	}

	/**
	 * 当listView数据变化时,调用此方法来更新listView
	 */
	public void updateListView(List<City> list) {
		this.list = list;
		notifyDataSetChanged();
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = null;
		ViewHolder viewHolder = null;
		if(convertView == null){
			viewHolder = new ViewHolder();
			v = LayoutInflater.from(context).inflate(R.layout.item_city, null);
			viewHolder.tvLetter = (TextView) v.findViewById(R.id.tv_letter);
			viewHolder.tvCityName = (TextView) v.findViewById(R.id.tv_cityName);
			v.setTag(viewHolder);
		}else{
			v = convertView;
			viewHolder = (ViewHolder) v.getTag();
		}
		
		int section = getSectionForPosition(position);//position所在的分区
		if(position == getPositionForSection(section)){//分区letter第一次出现的position
			viewHolder.tvLetter.setVisibility(View.VISIBLE);
			viewHolder.tvLetter.setText(""+list.get(position).getCityEn().toUpperCase().charAt(0));
		}else{
			viewHolder.tvLetter.setVisibility(View.GONE);
		}
		viewHolder.tvCityName.setText(list.get(position).getCityZh());
		return v;
	}
	
	class ViewHolder {
		TextView tvLetter;
		TextView tvCityName;
	}
	
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public Object[] getSections() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPositionForSection(int sectionIndex) {
		// 根据当前城市分区获取分区的起始位置.第一次,出现该字母的position(因为都排好序了)
		for(int i = 0;i<getCount();i++){
			int firstChar = list.get(i).getCityEn().toUpperCase().charAt(0);//城市首字母char
			if(firstChar == sectionIndex){
				return i;//返回分区的起始位置
			}
		}
		
		return -1;
	}

	@Override
	public int getSectionForPosition(int position) {
		// 返回当前城市首字母的char值----char值相同,字母相同,同一分区
		return list.get(position).getCityEn().toUpperCase().charAt(0);
	}
	
}
