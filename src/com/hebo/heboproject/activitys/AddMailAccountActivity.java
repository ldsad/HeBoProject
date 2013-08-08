package com.hebo.heboproject.activitys;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.hebo.heboproject.R;
import com.hebo.heboproject.utils.Utils;

public class AddMailAccountActivity extends PublicActivity {

	private EditText edt_mailaccount,edt_mailpassword;
	
	private Button btn_savemailaccount;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addmailaccount);
	}

	@Override
	public void initView() {
		edt_mailaccount = (EditText) findViewById(R.id.edt_mailaccount);
		edt_mailpassword = (EditText) findViewById(R.id.edt_mailpassword);
		
		btn_savemailaccount = (Button)findViewById(R.id.btn_savemailaccount);
		
		btn_savemailaccount.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//myProgressBar.
			}
		});
		
	}

}
