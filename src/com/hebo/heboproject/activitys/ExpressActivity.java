package com.hebo.heboproject.activitys;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;

import com.hebo.heboproject.R;
import com.hebo.heboproject.adapter.ExpressAdapter;
import com.hebo.heboproject.listener.ExpressItemOnclickListener;
import com.hebo.heboproject.views.MySideBar;

public class ExpressActivity extends PublicActivity {

	private ListView lv_express;

	private MySideBar indexBar;

	private TextView mDialogText;

	private WindowManager mWindowManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_express);
		 mWindowManager = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
		findView();
	}

	private void findView() {
		lv_express = (ListView) findViewById(R.id.lv_express);

		lv_express.setAdapter(new ExpressAdapter(this));

		lv_express.setOnItemClickListener(new ExpressItemOnclickListener(this));
		
		indexBar = (MySideBar) findViewById(R.id.mysidebar);

		indexBar.setListView(lv_express);

		mDialogText = (TextView) LayoutInflater.from(this).inflate(
				R.layout.activity_expressposition, null);
		mDialogText.setVisibility(View.GONE);
		WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.TYPE_APPLICATION,
				WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
						| WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
				PixelFormat.TRANSLUCENT);

		mWindowManager.addView(mDialogText, lp);
		indexBar.setTextView(mDialogText);

	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		
	}

}
