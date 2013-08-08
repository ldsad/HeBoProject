package com.hebo.heboproject.overlays;

import android.app.Activity;

import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.PoiOverlay;
import com.baidu.mapapi.search.MKPoiInfo;
import com.hebo.heboproject.activitys.MyMapActivity;


public class MyPoiOverlay extends PoiOverlay{

	public MyPoiOverlay(Activity activity, MapView mapView) {
		super(activity, mapView);
	}

	@Override
	protected boolean onTap(int i) {
		MKPoiInfo info =getPoi(i);
		if(info.hasCaterDetails){
			MyMapActivity.mkSearch.poiDetailSearch(info.uid);
		}else{
			
		}
		return true;
	}
	
	
	
	
	
	
	
	
	
	
}
