package com.hebo.heboproject.adapter;


import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hebo.heboproject.R;

public class SelectFristMenuAdapter extends BaseAdapter {
	Context context;
	private List<String> lst;
	LayoutInflater inflater;
	String [] foods;
	int last_item;
	int [] image;
	private int selectedPosition = -1;     
	public void setSelectedPosition(int position) {   
	selectedPosition = position;   
	}   
	          

	/*public myadapter(Context context,List<String > lst){
		this.context = context;
		this.lst = lst;
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); 

	}*/
	public SelectFristMenuAdapter(Context context,String [] foods,int[] image,int last_item){
	this.context = context;
	this.foods = foods;
	this.image = image;
	this.last_item = last_item;
	inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); 

    }
	public int getCount() {
		return foods.length;
	}

	public Object getItem(int position) {
		return foods[position];
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder  holder = null;
	    if(convertView==null){
	    convertView = inflater.inflate(R.layout.first_listview_item, null);
	    holder = new ViewHolder();
        holder.textView =(TextView)convertView.findViewById(R.id.textview);
        holder.imageView =(ImageView)convertView.findViewById(R.id.imageview);
        holder.layout=(LinearLayout)convertView.findViewById(R.id.colorlayout);
        convertView.setTag(holder);
	    }
	    else{
	    holder=(ViewHolder)convertView.getTag();
	    }
	 /*   if(last_item == position){
	    holder.textView.setBackgroundColor(R.color.bg);
	    }*/
	    // 设置选中效果    
	     if(selectedPosition == position)   
	    {   
	    	 holder.textView.setTextColor(Color.BLUE);   

	    
	     holder.layout.setBackgroundColor(Color.LTGRAY);   
	   } else {   
		   holder.textView.setTextColor(Color.WHITE);   
	    holder.layout.setBackgroundColor(Color.TRANSPARENT);   
	     }   

	   
	    holder.textView.setText(foods[position]);
	    holder.textView.setTextColor(Color.BLACK);
	    holder.imageView.setBackgroundResource(image[position]);
	    return convertView;
	}
	
	public static class ViewHolder{
		public TextView textView;
		public ImageView  imageView;
		public LinearLayout layout;
	}

}
