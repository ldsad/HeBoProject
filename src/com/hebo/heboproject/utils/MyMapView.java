package com.hebo.heboproject.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.baidu.mapapi.map.MapView;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.hebo.heboproject.activitys.MyMapActivity;

public class MyMapView extends MapView{

	public MyMapView(Context arg0, AttributeSet arg1) {
		super(arg0, arg1);
	}

	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		MyMapActivity.showAddr.setText("获取地址中...");
		GeoPoint g = this.getProjection().fromPixels(MeIcon.w, MeIcon.h);
		MyMapActivity.getPosition(g);
		return super.onTouchEvent(arg0);
	}

	

}
