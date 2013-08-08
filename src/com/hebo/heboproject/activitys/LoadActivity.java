package com.hebo.heboproject.activitys;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.hebo.heboproject.R;

public class LoadActivity extends PublicActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_load);
		initView();
		new Handler().postDelayed(new Runnable() {
			
			public void run() {
				   toNext(LoadActivity.this, WelcomeActivity.class);
                   LoadActivity.this.finish();
			}
		}, 2000);
	}

	@Override
	public void initView() {
		Log.i("==========init", "==============init");
	}
	
}
