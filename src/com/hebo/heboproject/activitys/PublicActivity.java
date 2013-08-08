package com.hebo.heboproject.activitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public abstract class PublicActivity extends Activity{

	ProgressBar myProgressBar;
	
	public void toNext(Context context,Class<?> className){
		Intent intent = new Intent(context, className);
		startActivity(intent);
	}
	
	public void toNext(Context context,Class<?> className,Bundle bundle){
		Intent intent = new Intent(context, className);
		intent.putExtras(bundle);
		startActivity(intent);
	}
	
	public void titleBack(View view){
		onBackPressed();
	}
	
	public abstract void initView();
	
}
