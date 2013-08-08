package com.hebo.heboproject.activitys;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.hebo.heboproject.R;

public class MapMenuActivity extends Activity {

	private ListView listView;
	private ArrayList<HashMap<String, Object>> mapMenuItemList;
	private HashMap<String, Object> hashMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_menu);
		listView = (ListView) findViewById(R.id.mapMenuListView);
		getMapOrderItem();
		// listView.setOnItemClickListener(new listViewItemClick());

	}
	
	
	
	/**
	 * 菜单项
	 */
	private void getMapOrderItem() {
		mapMenuItemList = new ArrayList<HashMap<String, Object>>();
		hashMap = new HashMap<String, Object>();
		hashMap.put("menuItem", "我的位置/定位");
		mapMenuItemList.add(hashMap);
		hashMap = new HashMap<String, Object>();
		hashMap.put("menuItem", "来这接我");
		mapMenuItemList.add(hashMap);
		hashMap = new HashMap<String, Object>();
		hashMap.put("menuItem", "路况信息");
		mapMenuItemList.add(hashMap);
		hashMap = new HashMap<String, Object>();
		hashMap.put("menuItem", "卫星图");
		mapMenuItemList.add(hashMap);
		hashMap = new HashMap<String, Object>();
		hashMap.put("menuItem", "检索服务");
		mapMenuItemList.add(hashMap);
		hashMap = new HashMap<String, Object>();
		hashMap.put("menuItem", "我的位置");
		mapMenuItemList.add(hashMap);
		hashMap = new HashMap<String, Object>();
		hashMap.put("menuItem", "我的位置");
		mapMenuItemList.add(hashMap);
		hashMap = new HashMap<String, Object>();
		hashMap.put("menuItem", "我的位置");
		mapMenuItemList.add(hashMap);
		hashMap = new HashMap<String, Object>();
		hashMap.put("menuItem", "我的位置");
		mapMenuItemList.add(hashMap);
		hashMap = new HashMap<String, Object>();
		hashMap.put("menuItem", "我的位置");
		mapMenuItemList.add(hashMap);
		hashMap = new HashMap<String, Object>();
		hashMap.put("menuItem", "我的位置");
		mapMenuItemList.add(hashMap);
		hashMap = new HashMap<String, Object>();
		hashMap.put("menuItem", "我的位置");
		mapMenuItemList.add(hashMap);

		SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(),
				mapMenuItemList, R.layout.map_menu_item,
				new String[] { "menuItem" }, new int[] { R.id.menuItem });
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new menuItemClick());

	}

	private class menuItemClick implements OnItemClickListener {

		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Intent intent = new Intent(MapMenuActivity.this,MyMapActivity.class);
			switch (position) {
			case 0:
				startActivity(intent);
				//MainActivity.tabHost.setCurrentTabByTag("myMap");
				break;
			case 1:
				MyMapActivity.showIcon = true;
				startActivity(intent);
				break;
			case 2:
				MyMapActivity.showTraffic = true;
				startActivity(intent);
				break;
			case 3:
				MyMapActivity.showSatellite = true;
				startActivity(intent);
				break;
			case 4:
				
				break;
			default:
				break;
			}
		}

	}

}
