package com.hebo.heboproject.activitys;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.BDNotifyListener;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.LocationData;
import com.baidu.mapapi.map.MKMapViewListener;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MyLocationOverlay;
import com.baidu.mapapi.search.MKSearch;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.hebo.heboproject.R;
import com.hebo.heboproject.listener.MyMkSerarchListener;
import com.hebo.heboproject.utils.IDUtils;
import com.hebo.heboproject.utils.JieWoIcon;
import com.hebo.heboproject.utils.MeIcon;
import com.hebo.heboproject.utils.MyMapView;

public class MyMapActivity extends PublicActivity {

	public static boolean showIcon, showSatellite, showTraffic = false;

	public static TextView showAddr;
	public MyApplication app;
	public static MyMapView mMapView = null;

	public MKMapViewListener mMapListener = null;
	MyLocationOverlay myLocationOverlay = null;

	public NotifyLister mNotifyer = null;
	public MyLocationListenner myListener = new MyLocationListenner();
	LocationData locData = null;
	private MapController mMapController = null;

	public static MKSearch mkSearch;

	public static Button btn_distance, mapOptionBtn, selectSortBtn;
	public static boolean isSatellite, isTraffic;

	public static GeoPoint currentGeoPoint;
	
	public static Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			// Toast.makeText(MyMapActivity.this, "msg:" + msg.what,
			// Toast.LENGTH_SHORT).show();
			switch (msg.what) {
			case IDUtils.SHOW_MY_LOCATION:
				MainActivity.mLocClient.requestLocation();
				break;
			case IDUtils.SHOW_SATELLITE:
				isSatellite = mMapView.isSatellite() ? false : true;
				mMapView.setSatellite(isSatellite);
				MainActivity.mLocClient.requestLocation();
				break;
			case IDUtils.SHOW_TRAFFIC:
				isTraffic = mMapView.isTraffic() ? false : true;
				mMapView.setTraffic(isTraffic);
				MainActivity.mLocClient.requestLocation();
				break;
			default:
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		showAddr = (TextView) findViewById(R.id.showAddr);
		btn_distance = (Button) findViewById(R.id.btn_distance);
		mapOptionBtn = (Button) findViewById(R.id.mapOption);
		selectSortBtn = (Button) findViewById(R.id.selectSortBtn);

		selectSortBtn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				toNext(MyMapActivity.this, SelectSortActivity.class);
			}
		});

		mapOptionBtn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				toNext(MyMapActivity.this, MapOptionActivity.class);
			}
		});

		btn_distance.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				toNext(MyMapActivity.this, DistanceActivity.class);
			}
		});

		// if (showIcon) {
		MeIcon me = new MeIcon(this);

		// 在屏幕中心点添加接我图标
		getWindow().addContentView(
				me,
				new LayoutParams(LayoutParams.FILL_PARENT,
						LayoutParams.FILL_PARENT));
		JieWoIcon jiewo = new JieWoIcon(this);
		getWindow().addContentView(
				jiewo,
				new LayoutParams(LayoutParams.FILL_PARENT,
						LayoutParams.FILL_PARENT));
		showAddr.setVisibility(View.VISIBLE);
		// }

		mMapView = (MyMapView) findViewById(R.id.bmapsView);
		mMapController = mMapView.getController();

		initMapView();
		app = MyApplication.getInstance();
		mkSearch = new MKSearch();
		mkSearch.init(app.mBMapManager, new MyMkSerarchListener(this));

		// MainActivity.mLocClient = new LocationClient(this);
		MainActivity.mLocClient.registerLocationListener(myListener);
		// //位置提醒相关代码
		// mNotifyer = new NotifyLister();
		// mNotifyer.SetNotifyLocation(31.192695,121.42712,3000,"bd09ll");//4个参数代表要位置提醒的点的坐标，具体含义依次为：纬度，经度，距离范围，坐标系类型(gcj02,gps,bd09,bd09ll)
		// mLocClient.registerNotify(mNotifyer);

		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);
		option.setCoorType("bd09ll");
		option.setScanSpan(300000);
		MainActivity.mLocClient.setLocOption(option);
		MainActivity.mLocClient.start();
		mMapView.getController().setZoom(16);
		mMapView.getController().enableClick(true);

		if (showTraffic) {
			mMapView.setTraffic(showTraffic);
		}
		if (showSatellite) {
			mMapView.setSatellite(showSatellite);
		}

		mMapView.displayZoomControls(true);
		mMapListener = new MKMapViewListener() {

			public void onMapMoveFinish() {
			}

			public void onClickMapPoi(MapPoi mapPoiInfo) {
				// TODO Auto-generated method stub
				String title = "";
				if (mapPoiInfo != null) {
					title = mapPoiInfo.strText;
					Toast.makeText(MyMapActivity.this, title,
							Toast.LENGTH_SHORT).show();
				}
			}

			public void onGetCurrentMap(Bitmap arg0) {
				// TODO Auto-generated method stub

			}

			public void onMapAnimationFinish() {
				// TODO Auto-generated method stub

			}
		};
		mMapView.regMapViewListener(MyApplication.getInstance().mBMapManager,
				mMapListener);
		myLocationOverlay = new MyLocationOverlay(mMapView);
		locData = new LocationData();
		myLocationOverlay.setData(locData);
		mMapView.getOverlays().add(myLocationOverlay);
		myLocationOverlay.enableCompass();
		mMapView.refresh();
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			showIcon = false;
			showSatellite = false;
			showTraffic = false;
			onBackPressed();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	private void initMapView() {
		mMapView.setLongClickable(true);
		// mMapController.setMapClickEnable(true);
		// mMapView.setSatellite(false);
	}

	/**
	 * 监听函数，又新位置的时候，格式化成字符串，输出到屏幕中
	 */
	public class MyLocationListenner implements BDLocationListener {
		public void onReceiveLocation(BDLocation location) {
			if (location == null)
				return;
			locData.latitude = location.getLatitude();
			locData.longitude = location.getLongitude();
			locData.direction = 2.0f;
			locData.accuracy = location.getRadius();
			locData.direction = location.getDerect();
			Log.d("loctest",
					String.format("before: lat: %f lon: %f",
							location.getLatitude(), location.getLongitude()));
			// GeoPoint p = CoordinateConver.fromGcjToBaidu(new
			// GeoPoint((int)(locData.latitude* 1e6), (int)(locData.longitude *
			// 1e6)));
			// Log.d("loctest",String.format("before: lat: %d lon: %d",
			// p.getLatitudeE6(),p.getLongitudeE6()));
			myLocationOverlay.setData(locData);
			mMapView.refresh();

			// mMapController.setCenter(new GeoPoint((int) (locData.latitude *
			// 1e6),
			// (int) (locData.longitude * 1e6)));
			currentGeoPoint = new GeoPoint((int) (locData.latitude * 1e6),
					(int) (locData.longitude * 1e6));
			mMapController
					.animateTo(currentGeoPoint, mHandler
							.obtainMessage(1));
		}

		public void onReceivePoi(BDLocation poiLocation) {
			if (poiLocation == null) {
				return;
			}
		}
	}

	public class NotifyLister extends BDNotifyListener {
		public void onNotify(BDLocation mlocation, float distance) {
		}
	}

	@Override
	protected void onPause() {
		mMapView.onPause();
		super.onPause();
	}

	@Override
	protected void onResume() {
		mMapView.onResume();
		super.onResume();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mMapView.onSaveInstanceState(outState);

	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		mMapView.onRestoreInstanceState(savedInstanceState);
	}

	public static void getPosition(GeoPoint g) {
		mkSearch.reverseGeocode(g);
	}
	
	public static  void searchByKey(String key){
		mkSearch.poiSearchNearBy(key, currentGeoPoint, 5000);
	}
	
	public static void searchByKey(String [] keys){
		Log.i("======================this is searchByKey", "======================this is searchByKey");
		mkSearch.poiMultiSearchNearBy(keys, currentGeoPoint, 5000);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		Log.i("======================this is mymap", "======================this is mymap");
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			break;

		default:
			break;
		}
		return super.onTouchEvent(event);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		
	}

	
	
}
