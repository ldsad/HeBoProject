package com.hebo.heboproject.listener;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.util.Log;

import com.baidu.mapapi.map.MKEvent;
import com.baidu.mapapi.map.PoiOverlay;
import com.baidu.mapapi.search.MKAddrInfo;
import com.baidu.mapapi.search.MKBusLineResult;
import com.baidu.mapapi.search.MKDrivingRouteResult;
import com.baidu.mapapi.search.MKPoiInfo;
import com.baidu.mapapi.search.MKPoiResult;
import com.baidu.mapapi.search.MKSearchListener;
import com.baidu.mapapi.search.MKSuggestionResult;
import com.baidu.mapapi.search.MKTransitRouteResult;
import com.baidu.mapapi.search.MKWalkingRouteResult;
import com.hebo.heboproject.activitys.MyMapActivity;
import com.hebo.heboproject.overlays.MyPoiOverlay;

public class MyMkSerarchListener implements MKSearchListener {

	private Activity activity;

	public MyMkSerarchListener(Activity activity) {
		this.activity = activity;
	}

	public void onGetAddrResult(MKAddrInfo info, int arg1) {
		MyMapActivity.showAddr.setText(info.strAddr);
	}

	public void onGetBusDetailResult(MKBusLineResult arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	public void onGetDrivingRouteResult(MKDrivingRouteResult arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	public void onGetPoiDetailSearchResult(int arg0, int arg1) {
		
	}

	public void onGetPoiResult(MKPoiResult res, int type, int error) {
		Log.i("============poiresult", "ressize======="+type+"====" + error);
		if (error == MKEvent.ERROR_RESULT_NOT_FOUND) {
			// 　Toast.makeText(MyMapActivity.this,
			// "抱歉，未找到结果",Toast.LENGTH_LONG).show();
			return;
		} else if (error != 0 || res == null) {
			// Toast.makeText(MyMapActivity.this, "搜索出错啦..",
			// Toast.LENGTH_LONG).show();
			return;
		}
		Log.i("============poisize", "poisize=======" + res);
		MyPoiOverlay poiOverlay = new MyPoiOverlay(activity, MyMapActivity.mMapView);

		// 当ePoiType为2（公交线路）或4（地铁线路）时， poi坐标为空
	
		if(type != 45 ){
			poiOverlay.setData(res.getAllPoi());
			MyMapActivity.mMapView.getOverlays().add(poiOverlay);

			MyMapActivity.mMapView.refresh();
			for (MKPoiInfo info : res.getAllPoi()) {
				if (info.pt != null) {
					MyMapActivity.mMapView.getController().animateTo(info.pt);
					break;
				}
			}
		}else{
			ArrayList<MKPoiInfo> l = new ArrayList<MKPoiInfo>();
			for (int i = 0; i <res.getMultiPoiResult().size(); i++) {
				for (int j = 0; j < res.getMultiPoiResult().get(i).getAllPoi().size(); j++) {
					l.add(res.getMultiPoiResult().get(i).getPoi(j));
				}
			}
			poiOverlay.setData(l);
			MyMapActivity.mMapView.getOverlays().add(poiOverlay);
			MyMapActivity.mMapView.refresh();
			for (int i = 0; i < l.size(); i++) {
				MKPoiInfo info = l.get(i);
				if (info.pt != null) {
					MyMapActivity.mMapView.getController().animateTo(info.pt);
					break;
				}
				break;
			}
		}
	
		

	}

	public void onGetSuggestionResult(MKSuggestionResult arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	public void onGetTransitRouteResult(MKTransitRouteResult arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	public void onGetWalkingRouteResult(MKWalkingRouteResult arg0, int arg1) {

	}

}
