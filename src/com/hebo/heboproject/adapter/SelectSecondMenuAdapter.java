package com.hebo.heboproject.adapter;


import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hebo.heboproject.R;

public class SelectSecondMenuAdapter extends BaseAdapter {
	List<String> lst1;
	Context context;
	LayoutInflater layoutInflater;
	String[][] cities;
	public int foodpoition;

	/*
	 * public adapter1(Context context, List<String> lst1) { this.context =
	 * context; this.lst1 = lst1; layoutInflater =
	 * (LayoutInflater)context.getSystemService
	 * (Context.LAYOUT_INFLATER_SERVICE); }
	 */
	public SelectSecondMenuAdapter(Context context, String[][] cities,int position) {
		this.context = context;
		this.cities = cities;
		layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.foodpoition = position;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return cities.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return getItem(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.second_list_item, null);
			viewHolder = new ViewHolder();
			viewHolder.textView = (TextView) convertView
					.findViewById(R.id.textview1);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.textView.setText(cities[foodpoition][position]);
		viewHolder.textView.setTextColor(Color.BLACK);
		return convertView;
	}

	public static class ViewHolder {
		public TextView textView;
	}

}
