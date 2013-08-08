package com.hebo.heboproject.utils;

import java.util.List;

import android.widget.ImageView;
import android.widget.TextView;

import com.hebo.heboproject.R;

public class WeatherUtil {

	public static final String CLEAR = "晴";
	public static final String RAIN = "小雨";
	public static final String RAIN2 = "中雨";
	public static final String RAIN3 = "大雨";
	public static String SNOW = "雪";
	public static String CLOUDY = "云";
	//public static String THUNDERSTORMS = "大雨";
	public static String FOG = "雾";
	public static String HAZE = "霾";
	public static String OVERCAST = "阴";
	
	public static void setWeatherImg(String weatherInfo,ImageView imageView){
		
		
		if(weatherInfo.indexOf(CLEAR) != -1){
			imageView.setBackgroundResource(R.drawable.qing);
			return;
		}else if(weatherInfo.indexOf(RAIN) != -1){
			imageView.setBackgroundResource(R.drawable.xiaoyu);
			return;
		}else if(weatherInfo.indexOf(RAIN2) != -1){
			imageView.setBackgroundResource(R.drawable.zhongyu);
			return;
		}else if(weatherInfo.indexOf(RAIN3) != -1){
			imageView.setBackgroundResource(R.drawable.dayu);
			return;
		}else if(weatherInfo.indexOf(SNOW) != -1){
			imageView.setBackgroundResource(R.drawable.xue);
			return;
		}else if(weatherInfo.indexOf(CLOUDY) != -1){
			imageView.setBackgroundResource(R.drawable.duoyun);
			return;
		}else if(weatherInfo.indexOf(FOG) != -1){
			imageView.setBackgroundResource(R.drawable.wu);
			return;
		}else if(weatherInfo.indexOf(HAZE) != -1){
			imageView.setBackgroundResource(R.drawable.mai);
			return;
		}else if(weatherInfo.indexOf(HAZE) != -1){
			imageView.setBackgroundResource(R.drawable.yin);
			return;
		}
		
	}
	
	
	/**
	 * 方法太笨拙了。
	 * 
	 * @param day_of_week
	 * @return 当天星期几
	 */
	public static int matchDayofWeek(String day_of_week) {

		if (day_of_week.equals("星期一")) {
			return 1;
		} else if (day_of_week.equals("星期二")) {
			return 2;
		} else if (day_of_week.equals("星期三")) {
			return 3;
		} else if (day_of_week.equals("星期四")) {
			return 4;
		} else if (day_of_week.equals("星期五")) {
			return 5;
		} else if (day_of_week.equals("星期六")) {
			return 6;
		} else {
			return 7;
		}

	}

	/**
	 * 
	 * 这个方法太弱智了。没有去考虑好的方法~
	 * 
	 * @return 星期的数组
	 */
	public static void weekDays(int i,List<TextView> tvs) {
		String[] days = null;
		switch (i) {
		case 1:
			days = new String[] { "星期二", "星期三", "星期四"};
			break;
		case 2:
			days = new String[] { "星期三", "星期四", "星期五"};
			break;
		case 3:
			days = new String[] { "星期四", "星期五", "星期六"};
			break;
		case 4:
			days = new String[] { "星期五", "星期六", "星期日"};
			break;
		case 5:
			days = new String[] { "星期六", "星期日", "星期一"};
			break;
		case 6:
			days = new String[] { "星期日", "星期一", "星期二"};
		case 7:
			days = new String[] { "星期一", "星期二", "星期三"};
			break;

		}
		
		for (int j = 0; j < days.length; j++) {
			tvs.get(j).setText(days[j]);
		}
		
	}
	
	

}
