package com.hebo.heboproject.activitys;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.hebo.heboproject.R;
import com.hebo.heboproject.adapter.HeBoInfoPagerAdapter;
import com.hebo.heboproject.entity.WeatherEntity;
import com.hebo.heboproject.utils.IDUtils;
import com.hebo.heboproject.utils.WeatherUtil;

public class HeBoInfoActivity extends Activity {

	private ViewPager mPager; // 页卡内容
	private List<View> listViews; // Tab页面列表
	private ImageView cursor; // 动画图片
	private TextView t1, t2, t3, t4; // 页卡头标
	private int offset = 0; // 动画图片偏移量
	private int currentIndex = 0; // 当前页卡编号
	private int bmpW; // 动画图片宽度
	
	WeatherEntity w;
	private TextView firstWeatherText,secondWeatherText,thirdWeatherText,fourthWeatherText,fifthWeatherText,sixthWeatherText,
	weekText1,weekText2,weekText3,weekText4,weekText5,weekText6;
	private ImageView firstWeatherImg,secondWeatherImg,thirdWeatherImg,fourthWeatherImg,fifthWeatherImg,sixthWeatherImg;
	private List<TextView> tvs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hebo_info);
		
		InitImageView();
		InitTextView();
		InitViewPager();
		
//		new Handler().post(new Runnable() {
//			
//			@Override
//			public void run() {
//				getWeather();
//			}
//		});
		new Thread(){

			@Override
			public void run() {
				getWeather();
			}
			
		}.start();
		
		
	}

	/** 
	 * 初始化动画
	 */
	private void InitImageView() {
		cursor = (ImageView) findViewById(R.id.cursor);
		bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.a)
				.getWidth();
		
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels; // 获取分辨率宽度
		Matrix matrix = new Matrix();
		matrix.postTranslate(offset, 0);
	}

	/**
	 * 初始化头标
	 */
	private void InitTextView() {
		t1 = (TextView) findViewById(R.id.text1);
		t2 = (TextView) findViewById(R.id.text2);
		t3 = (TextView) findViewById(R.id.text3);
		t4 = (TextView) findViewById(R.id.text4);

		t1.setOnClickListener(new MyOnClickListener(0));
		t2.setOnClickListener(new MyOnClickListener(1));
		t3.setOnClickListener(new MyOnClickListener(2));
		t4.setOnClickListener(new MyOnClickListener(3));

	}

	/**
	 * 头标点击监听
	 */
	public class MyOnClickListener implements OnClickListener {
		private int index = 0;

		public void onClick(View v) {
			mPager.setCurrentItem(index);
		}

		public MyOnClickListener(int i) {
			super();
			this.index = i;
		}

	}

	/**
	 * 初始化ViewPager
	 */
	private void InitViewPager() {
		mPager = (ViewPager) findViewById(R.id.vPager);
		listViews = new ArrayList<View>();
		LayoutInflater mInflater = getLayoutInflater();
		
		View weatherView = mInflater.inflate(R.layout.hebo_info_two,null);
		firstWeatherText = (TextView) weatherView.findViewById(R.id.firstWeatherText);
		firstWeatherImg = (ImageView) weatherView.findViewById(R.id.firstWeatherImg);
		//weekText1 = (TextView) view.findViewById(R.id.firstWeatherText);
		
		secondWeatherText = (TextView) weatherView.findViewById(R.id.secondWeatherText);
		secondWeatherImg = (ImageView) weatherView.findViewById(R.id.secondWeatherImg);
		weekText2 = (TextView) weatherView.findViewById(R.id.weekText2);
		
		thirdWeatherText  = (TextView) weatherView.findViewById(R.id.thirdWeatherText);
		thirdWeatherImg = (ImageView) weatherView.findViewById(R.id.thirdWeatherImg);
		weekText3 = (TextView) weatherView.findViewById(R.id.weekText3);
		
		fourthWeatherText = (TextView) weatherView.findViewById(R.id.fourthWeatherText);
		fourthWeatherImg = (ImageView) weatherView.findViewById(R.id.fourthWeatherImg);
		weekText4 = (TextView) weatherView.findViewById(R.id.weekText4);
		
//		fifthWeatherText = (TextView) weatherView.findViewById(R.id.fifthWeatherText);
//		fifthWeatherImg = (ImageView) weatherView.findViewById(R.id.fifthWeatherImg);
//		weekText5 = (TextView) weatherView.findViewById(R.id.weekText5);
//		
//		sixthWeatherText = (TextView) weatherView.findViewById(R.id.sixthWeatherText);
//		sixthWeatherImg = (ImageView) weatherView.findViewById(R.id.sixthWeatherImg);
//		weekText6 = (TextView) weatherView.findViewById(R.id.weekText6);
		
		//tvs.add(weekText1);
		tvs = new ArrayList<TextView>();
		tvs.add(weekText2);
		tvs.add(weekText3);
		tvs.add(weekText4);
//		tvs.add(weekText5);
//		tvs.add(weekText6);
		
		listViews.add(mInflater.inflate(R.layout.hebo_info_one, null));
		listViews.add(weatherView);
		listViews.add(mInflater.inflate(R.layout.hebo_info_three, null));
		listViews.add(mInflater.inflate(R.layout.hebo_info_four, null));

		mPager.setAdapter(new HeBoInfoPagerAdapter(listViews));
		mPager.setCurrentItem(0);
		mPager.setOnPageChangeListener(new MyOnPageChangeListener());

	}

	/*
	 * 页卡切换监听
	 */
	public class MyOnPageChangeListener implements OnPageChangeListener {

		int one = offset * 2 + bmpW; // 页卡1 -> 页卡2 偏移量
		int two = one * 2; // 页卡1 -> 页卡3 偏移量
		int three = one * 3;

		public void onPageScrollStateChanged(int arg0) {

		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		public void onPageSelected(int arg0) {
			Animation animation = null;
			switch (arg0) {
			case 0:
				if (currentIndex == 1) {
					animation = new TranslateAnimation(one, 0, 0, 0);
				} else if (currentIndex == 2) {
					animation = new TranslateAnimation(two, 0, 0, 0);
				} else if (currentIndex == 3) {
					animation = new TranslateAnimation(three, 0, 0, 0);
				}

				break;
			case 1:
				if (currentIndex == 0) {
					animation = new TranslateAnimation(offset, one, 0, 0);
				} else if (currentIndex == 2) {
					animation = new TranslateAnimation(two, one, 0, 0);
				} else if (currentIndex == 3) {
					animation = new TranslateAnimation(three, one, 0, 0);
				}
				break;
			case 2:
				if (currentIndex == 0) {
					animation = new TranslateAnimation(offset, two, 0, 0);
				} else if (currentIndex == 1) {
					animation = new TranslateAnimation(one, two, 0, 0);
				} else if (currentIndex == 3) {
					animation = new TranslateAnimation(three, two, 0, 0);
				}

				break;
			case 3:
				if (currentIndex == 0) {
					animation = new TranslateAnimation(offset, three, 0, 0);
				} else if (currentIndex == 1) {
					animation = new TranslateAnimation(one, two, 0, 0);
				} else if (currentIndex == 2) {
					animation = new TranslateAnimation(two, three, 0, 0);
				}
				break;
			}
			currentIndex = arg0;
			animation.setFillAfter(true);
			animation.setDuration(300);
			cursor.startAnimation(animation);
		}

	}

	private void getWeather() {
		HttpClient client = new DefaultHttpClient();
		//HttpPost post = new HttpPost(Utils.WEATHER_URL);
		HttpGet get = new HttpGet(IDUtils.WEATHER_URL);
		HttpResponse response;
		try {
			response = client.execute(get);
			String result = EntityUtils.toString(response.getEntity());
			Log.i("================", "==========" + result);
			
			JSONObject json = new JSONObject(result).getJSONObject("weatherinfo");
			w = new WeatherEntity();
			w.setCity(json.getString("city"));
			w.setDate_y(json.getString("date_y"));
			w.setWeek(json.getString("week"));
			w.setTemp1(json.getString("temp1"));
			w.setTemp2(json.getString("temp2"));
			w.setTemp3(json.getString("temp3"));
			w.setTemp4(json.getString("temp4"));
			w.setTemp5(json.getString("temp5"));
			w.setTemp6(json.getString("temp6"));
			w.setWeather1(json.getString("weather1"));
			w.setWeather2(json.getString("weather2"));
			w.setWeather3(json.getString("weather3"));
			w.setWeather4(json.getString("weather4"));
//			w.setWeather5(json.getString("weather5"));
//			w.setWeather6(json.getString("weather6"));
			w.setWind1(json.getString("wind1"));
			Log.i("================", "==========" + w);
			handler.sendEmptyMessage(showWeather);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	private final int showWeather = 01;
	
	public Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case showWeather:
				firstWeatherText.setText("风向：" +  w.getWind1() + "\n" + "天气：" + w.getWeather1() +"\n" + "气温：" + w.getTemp1());
				//firstWeatherImg.setBackgroundResource(R.drawable.duoyun);
				
				WeatherUtil.weekDays(WeatherUtil.matchDayofWeek(w.getWeek()),tvs);
				
				secondWeatherText.setText(w.getWeather2() + "\n" + w.getTemp2());
				thirdWeatherText.setText(w.getWeather3() + "\n" + w.getTemp3());
				fourthWeatherText.setText(w.getWeather4() + "\n" + w.getTemp4());
//				fifthWeatherText.setText(w.getWeather5() + "\n" + w.getTemp5());
//				sixthWeatherText.setText(w.getWeather6() + "\n" + w.getTemp6());
				WeatherUtil.setWeatherImg(w.getWeather1(), firstWeatherImg);
				WeatherUtil.setWeatherImg(w.getWeather2(), secondWeatherImg);
				WeatherUtil.setWeatherImg(w.getWeather3(), thirdWeatherImg);
				WeatherUtil.setWeatherImg(w.getWeather4(), fourthWeatherImg);
//				WeatherUtil.setWeatherImg(w.getWeather5(), fifthWeatherImg);
//				WeatherUtil.setWeatherImg(w.getWeather6(), sixthWeatherImg);
				break;
			}
		}
		
	};
	
	
	
}
