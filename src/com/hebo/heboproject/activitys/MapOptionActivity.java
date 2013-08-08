package com.hebo.heboproject.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.hebo.heboproject.R;
import com.hebo.heboproject.utils.IDUtils;
import com.hebo.heboproject.utils.Utils;

public class MapOptionActivity extends Activity {

	private LinearLayout showSatelliteLayout, showTrafficLayout,
			showMyLocationLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_option);

		showMyLocationLayout = (LinearLayout) findViewById(R.id.showMyLocation);
		showMyLocationLayout.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				onBackPressed();
				MyMapActivity.mHandler.sendEmptyMessage(IDUtils.SHOW_MY_LOCATION);
			}
		});

		showSatelliteLayout = (LinearLayout) findViewById(R.id.showSatelliteLayout);
		showSatelliteLayout.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				onBackPressed();
				MyMapActivity.mHandler.sendEmptyMessage(IDUtils.SHOW_SATELLITE);
			}
		});

		showTrafficLayout = (LinearLayout) findViewById(R.id.showTrafficLayout);
		showTrafficLayout.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				onBackPressed();
				MyMapActivity.mHandler.sendEmptyMessage(IDUtils.SHOW_TRAFFIC);
			}
		});

	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		finish();
		return true;
	}


}
