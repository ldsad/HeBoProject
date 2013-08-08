package com.hebo.heboproject.activitys;

import com.hebo.heboproject.R;

import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends PublicActivity {

	private VideoView myVideoView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video);
		
		myVideoView = (VideoView)findViewById(R.id.video_my_video);
		myVideoView.setVideoPath("/mnt/sdcard/hebo_video/test.mp4");
		myVideoView.requestFocus();
		myVideoView.setMediaController(new MediaController(this));
		myVideoView.start();
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		
	}
	
}
