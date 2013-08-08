package com.hebo.heboproject.adapter;

import com.hebo.heboproject.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class WinGridViewAdapter extends BaseAdapter{

	private View [] itemViews;
	
	
	
	public WinGridViewAdapter(int [] mImageIds,int [] titleTexts,Context context) {
		itemViews = new View[mImageIds.length];
		for (int i = 0; i < itemViews.length; i++) {
			itemViews[i] = makeItemView(mImageIds[i],titleTexts[i],context);
		}
	}

	private View makeItemView(int strmImageIds,int titleTexts,Context context){
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View itemView  =  inflater.inflate(R.layout.win_gridview_item, null);
		TextView title = (TextView)itemView.findViewById(R.id.TextItemId);
		title.setText(titleTexts);
		ImageView image = (ImageView)itemView.findViewById(R.id.ImageItemId);
		image.setImageResource(strmImageIds);
		image.setScaleType(ImageView.ScaleType.FIT_CENTER);
		return itemView;
	}
	
	public int getCount() {
		// TODO Auto-generated method stub
		return itemViews.length;
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return itemViews[position];
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		  if (convertView == null)  
              return itemViews[position];  
           return convertView;  
	}
	
}
