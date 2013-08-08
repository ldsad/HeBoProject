package com.hebo.heboproject.listener;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.hebo.heboproject.activitys.ExpressDetailActivity;
import com.hebo.heboproject.adapter.ExpressAdapter;

public class ExpressItemOnclickListener implements OnItemClickListener{

	private Context context;
	
	
	
	public ExpressItemOnclickListener(Context context) {
		super();
		this.context = context;
	}




	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int position, long arg3) {
		Log.i("================","=============" + ExpressAdapter.expressMap.get(adapter.getItemAtPosition(position)));
		ExpressDetailActivity.com = ExpressAdapter.expressMap.get(adapter.getItemAtPosition(position));
		Intent i = new Intent(context,ExpressDetailActivity.class);
		context.startActivity(i);
	}

}
