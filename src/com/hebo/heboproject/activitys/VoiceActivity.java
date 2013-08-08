package com.hebo.heboproject.activitys;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.hebo.heboproject.R;
import com.hebo.heboproject.views.VoiceRecordButton;
import com.hebo.heboproject.views.VoiceRecordButton.OnFinishedRecordListener;

public class VoiceActivity extends PublicActivity {

	private VoiceRecordButton voiceRecordBtn = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_voice);
		
		
		voiceRecordBtn = (VoiceRecordButton)findViewById(R.id.btn_voice);
		
		String path =  Environment.getExternalStorageDirectory()
				.getAbsolutePath();
		
		path +="/mmmm.amr";
		
		
		voiceRecordBtn.setSavePath(path);
		
		voiceRecordBtn.setOnFinishedRecordListener(new OnFinishedRecordListener() {
			
			@Override
			public void onFinishedRecord(String audioPath) {
				Log.i("===============RECORD!!!", "===================finished!!!!!!!!!! save to "
						+ audioPath);
			}
		});
	}

	@Override
	public void initView() {
		
	}
	
	
	
}
