package com.hebo.heboproject.activitys;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.hebo.heboproject.R;

public class LightActivity extends PublicActivity {

	Button btn_light;
	
	public static boolean kaiguan = true;
	
	private Camera camera = null;
	
	private Parameters parameters = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_light);
		
		initView();
		
	}
	
	public void initView(){
		btn_light = (Button) findViewById(R.id.btn_light);
		btn_light.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(kaiguan){
					btn_light.setBackgroundResource(R.drawable.shou_on);
					camera = Camera.open();
					parameters = camera.getParameters();
					parameters.setFlashMode(Parameters.FLASH_MODE_TORCH);
					camera.setParameters(parameters);
					kaiguan = false;
				}else{
					btn_light.setBackgroundResource(R.drawable.shou_off);
					parameters.setFlashMode(Parameters.FLASH_MODE_OFF);// 关闭
					camera.setParameters(parameters);
					kaiguan = true;
					camera.release();
				}
			}
		});
	}

	@Override
	public void titleBack(View view) {
		if (kaiguan) {// 开关关闭时
			LightActivity.this.finish();
			//android.os.Process.killProcess(android.os.Process.myPid());// 关闭进程
		} else if (!kaiguan) {// 开关打开时
			camera.release();
			LightActivity.this.finish();
			//android.os.Process.killProcess(android.os.Process.myPid());// 关闭进程
			kaiguan = true;// 避免，打开开关后退出程序，再次进入不打开开关直接退出时，程序错误
		}
		super.titleBack(view);
	}
	
	
	
}
