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

/**city�б��������
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
	 * ��listView���ݱ仯ʱ,���ô˷���������listView
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
		
		int section = getSectionForPosition(position);//position���ڵķ���
		if(position == getPositionForSection(section)){//����letter��һ�γ��ֵ�position
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
		// ���ݵ�ǰ���з�����ȡ��������ʼλ��.��һ��,���ָ���ĸ��position(��Ϊ���ź�����)
		for(int i = 0;i<getCount();i++){
			int firstChar = list.get(i).getCityEn().toUpperCase().charAt(0);//��������ĸchar
			if(firstChar == sectionIndex){
				return i;//���ط�������ʼλ��
			}
		}
		
		return -1;
	}

	@Override
	public int getSectionForPosition(int position) {
		// ���ص�ǰ��������ĸ��charֵ----charֵ��ͬ,��ĸ��ͬ,ͬһ����
		return list.get(position).getCityEn().toUpperCase().charAt(0);
	}
	
}
