package com.hebo.heboproject.activitys;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.hebo.heboproject.R;

public class MoreActivity extends PublicActivity implements OnClickListener{

	Button btn_hebox;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_more);
		
		initView();
		
	}
	
	public void initView(){
		btn_hebox = (Button)findViewById(R.id.btn_hebox);
		btn_hebox.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_hebox:
			
			break;

		default:
			break;
		}
	}
	
}
