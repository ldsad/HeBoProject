package com.hebo.heboproject.activitys;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.Toast;

import com.baidu.location.LocationClient;
import com.hebo.heboproject.R;
import com.hebo.heboproject.utils.MoveBg;

public class MainActivity extends TabActivity {

	public static TabHost tabHost;
	TabHost.TabSpec tabSpec;
	RadioGroup radioGroup;
	RelativeLayout bottom_layout;
	ImageView img;
	int startLeft;
	
	public static LocationClient mLocClient;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mLocClient = new LocationClient(getApplicationContext());
		
		
		
		bottom_layout = (RelativeLayout) findViewById(R.id.layout_bottom);

		tabHost = getTabHost();
		tabHost.addTab(tabHost.newTabSpec("heboInfo").setIndicator("heboInfo")
				.setContent(new Intent(this, HeBoInfoActivity.class)));
		
//		tabHost.addTab(tabHost.newTabSpec("mapMenu").setIndicator("mapMenu")
//				.setContent(new Intent(this, MapMenuActivity.class)));
		
		tabHost.addTab(tabHost.newTabSpec("mapMenu").setIndicator("mapMenu")
		.setContent(new Intent(this, MyMapActivity.class)));
		
		tabHost.addTab(tabHost.newTabSpec("webView").setIndicator("webView").setContent(new
				Intent(this, MyWebViewActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("win").setIndicator("win").setContent(new
				Intent(this, WinActivity.class)));
//		tabHost.addTab(tabHost.newTabSpec("camera").setIndicator("camera").setContent(new
//				Intent(this, CameraActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("more").setIndicator("more").setContent(new
				Intent(this, MoreActivity.class)));
//		tabHost.addTab(tabHost.newTabSpec("video").setIndicator("video").setContent(new
//				Intent(this, VideoActivity.class)));
//		tabHost.addTab(MainActivity.tabHost.newTabSpec("myMap").setIndicator("myMap").setContent(new
//				 Intent(this, MyMapActivity.class)));
		//
		radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
		radioGroup.setOnCheckedChangeListener(checkedChangeListener);
		img = new ImageView(this);
		img.setImageResource(R.drawable.tab_front_bg);
		bottom_layout.addView(img);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	private OnCheckedChangeListener checkedChangeListener = new OnCheckedChangeListener() {

		public void onCheckedChanged(RadioGroup group, int checkedId) {
			switch (checkedId) {
			case R.id.radio_news:
				tabHost.setCurrentTabByTag("heboInfo");
				// moveFrontBg(img, startLeft, 0, 0, 0);
				MoveBg.moveFrontBg(img, startLeft, 0, 0, 0);
				startLeft = 0;
				break;
			case R.id.radio_topic:
				tabHost.setCurrentTabByTag("mapMenu");
				MoveBg.moveFrontBg(img, startLeft, img.getWidth(), 0, 0);
				startLeft = img.getWidth();
				break;
			case R.id.radio_pic:
				tabHost.setCurrentTabByTag("webView");
				MoveBg.moveFrontBg(img, startLeft, img.getWidth() * 2, 0, 0);
				startLeft = img.getWidth() * 2;
				break;
			case R.id.radio_follow:
				tabHost.setCurrentTabByTag("win");
				MoveBg.moveFrontBg(img, startLeft, img.getWidth() * 3, 0, 0);
				startLeft = img.getWidth() * 3;
				break;
			case R.id.radio_vote:
				tabHost.setCurrentTabByTag("more");
				MoveBg.moveFrontBg(img, startLeft, img.getWidth() * 4, 0, 0);
				startLeft = img.getWidth() * 4;
				break;

			default:
				break;
			}
		}
	};

	
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if(!(this.tabHost.getCurrentTabTag().equals("heboInfo"))){
				this.tabHost.setCurrentTabByTag("heboInfo");
			}else{
				Toast.makeText(this, "exit exit exit", 3).show();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}


}
