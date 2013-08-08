package com.hebo.heboproject.activitys;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

import com.hebo.heboproject.R;
import com.hebo.heboproject.adapter.WinGridViewAdapter;

public class WinActivity extends PublicActivity{

	private GridView gridView;
	
	
	private int [] mImageIds = {
			R.drawable.camera,
			R.drawable.video,
			R.drawable.compass,
			R.drawable.light,
			R.drawable.kuaidi,
			R.drawable.voice,
			R.drawable.mail,
			R.drawable.mail,
			R.drawable.music,
	};
	
	private int [] TitleTexts = {
			R.string.calculator,
			R.string.video,
			R.string.compass,
			R.string.light,
			R.string.express,
			R.string.voice,
			R.string.fileExplorer,
			R.string.mail,
			R.string.multimedia,
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_win);
		gridView = (GridView) findViewById(R.id.winGridView);
		gridView.setAdapter(new WinGridViewAdapter(mImageIds, TitleTexts, this));
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				switch (position) {
				case 0:
					toNext(WinActivity.this, CameraActivity.class);
					//MainActivity.tabHost.setCurrentTabByTag("camera");
					break;
				case 1:
					//MainActivity.tabHost.setCurrentTabByTag("video");
					break;
				case 2:
					toNext(WinActivity.this, CompassActivity.class);
					break;
				case 3:
					toNext(WinActivity.this, LightActivity.class);
					break;
				case 4:
					toNext(WinActivity.this, ExpressActivity.class);
					break;
				case 5:
					toNext(WinActivity.this, VoiceActivity.class);
					break;
				case 6:
					
					break;
				case 7:
					toNext(WinActivity.this, MailActivity.class);
					break;
				}
			}
		});
	}
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if(!(MainActivity.tabHost.getCurrentTabTag().equals("heboInfo"))){
				MainActivity.tabHost.setCurrentTabByTag("heboInfo");
			}else{
				Toast.makeText(this, "exit exit exit", 3).show();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	@Override
	public void initView() {
		
	}
	
	
	
}
