package com.hebo.heboproject.activitys;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hebo.heboproject.R;
import com.hebo.heboproject.entity.Express;
import com.hebo.heboproject.entity.ExpressEntity;

public class ExpressDetailActivity extends PublicActivity {

	public static String com;
	
	Button btn_expressquery;
	
	EditText edt_expressnum;
	
	TextView txt_expressresult;
	ExpressEntity expressEntity;
	HttpPost httpPost;
	String url;
	HttpResponse response;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expressdetail);
		initView();
	}
	
	public void initView(){
		
		btn_expressquery = (Button) findViewById(R.id.btn_expressquery);
		edt_expressnum = (EditText) findViewById(R.id.edt_expressnum);
		txt_expressresult = (TextView) findViewById(R.id.txt_expressresult);
		
		btn_expressquery.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//				new Handler().post(new Runnable() {
//					
//					@Override
//					public void run() {
//						url = "http://www.kuaidi100.com/chaxun?com="+com+"&nu="+edt_expressnum.getText().toString().trim();
//						httpGet = new HttpGet(url);
//						try {
//							response = new DefaultHttpClient().execute(httpGet);
//						} catch (ClientProtocolException e) {
//							e.printStackTrace();
//						} catch (IOException e) {
//							e.printStackTrace();
//						}
//						if(response.getStatusLine().getStatusCode() == 200){
//							
//						}
//					}
//				});
				new Thread(){

					@Override
					public void run() {
						//url = "http://www.kuaidi100.com/chaxun?com="+com+"&nu="+edt_expressnum.getText().toString().trim();
						url = "http://api.kuaidi100.com/api?id=d3d26f3c59bd5a9b&com="+com+"&nu="+edt_expressnum.getText().toString().trim()+"&order=asc";
						httpPost = new HttpPost(url);
						try {
							response = new DefaultHttpClient().execute(httpPost);
							if(response.getStatusLine().getStatusCode() == 200){
								HttpEntity httpEntity = response.getEntity();
								String result = EntityUtils.toString(httpEntity,HTTP.UTF_8);
								JSONObject json = new JSONObject(result);
								expressEntity = new ExpressEntity();
								expressEntity.setMesssage(json.getString("message"));
								expressEntity.setNu(json.getString("nu"));
								expressEntity.setIscheck(json.getString("ischeck"));
								expressEntity.setCom(json.getString("com"));
								expressEntity.setStatus(json.getString("status"));
								expressEntity.setCondition(json.getString("condition"));
								JSONArray jsonArray = json.getJSONArray("data");
								List<Express> list = new ArrayList<Express>();
								for (int i = 0; i < jsonArray.length(); i++) {
									Express e = new Express();
									e.setContext(((JSONObject)jsonArray.opt(i)).getString("context"));
									e.setTime(((JSONObject)jsonArray.opt(i)).getString("time"));
									list.add(e);
								}
								expressEntity.setData(list);
								mHandler.sendEmptyMessage(1);
							}
						} catch (Exception e) {
							e.printStackTrace();
						} 
					}
					
				}.start();
			}
		});
		
	}
	
	private Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			String result  = "";
			for (int i = 0; i < expressEntity.getData().size(); i++) {
				result += expressEntity.getData().get(i).getContext() + expressEntity.getData().get(i).getTime()+"\n";
			}
			txt_expressresult.setText(result);
		}
	};
	
}
