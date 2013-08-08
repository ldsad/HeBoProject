package com.hebo.heboproject.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.hebo.heboproject.R;

public class DistanceActivity extends Activity implements OnClickListener {

	private TextView txt_1000, txt_2000, txt_3000, txt_5000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_distance);
		initView();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		finish();
		return true;
	}

	private void initView() {
		txt_1000 = (TextView) findViewById(R.id.txt_1000);
		txt_2000 = (TextView) findViewById(R.id.txt_2000);
		txt_3000 = (TextView) findViewById(R.id.txt_3000);
		txt_5000 = (TextView) findViewById(R.id.txt_5000);
		txt_1000.setOnClickListener(this);
		txt_2000.setOnClickListener(this);
		txt_3000.setOnClickListener(this);
		txt_5000.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.txt_1000:
			MyMapActivity.btn_distance.setText("1000米");
			this.finish();
			break;
		case R.id.txt_2000:

			break;
		case R.id.txt_3000:

			break;
		case R.id.txt_5000:

			break;

		default:
			break;
		}
	}

}
