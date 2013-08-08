package com.hebo.heboproject.activitys;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.hebo.heboproject.R;

public class MailActivity extends PublicActivity{

	private ListView lv_mailaccountlist;
	
	private Button btn_addmailaccount;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mail);
	}

	@Override
	public void initView() {
		lv_mailaccountlist = (ListView) findViewById(R.id.lv_mailaccountlist);
		btn_addmailaccount = (Button)findViewById(R.id.btn_addmailaccount);
		btn_addmailaccount.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				toNext(MailActivity.this, AddMailAccountActivity.class);
			}
		});
	}
	
	
}
