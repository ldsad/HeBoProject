package com.hebo.heboproject.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.hebo.heboproject.R;

public class MyWebViewActivity extends Activity {

	WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);
		webView = (WebView) findViewById(R.id.myMapView);

		WebSettings webSettings = webView.getSettings();
		webSettings.setSavePassword(false);
		webSettings.setSaveFormData(true);
		webSettings.setJavaScriptEnabled(true);
		webSettings.setSupportZoom(false);

		webView.setWebChromeClient(new MyWebChromeClient());

		// webView.addJavascriptInterface(new DemoJavaScriptInterface(),
		// "demo");

		webView.loadUrl("http://www.heboot.com");

	}
	 final class MyWebChromeClient extends WebChromeClient {
	        @Override
	        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
	           // Log.d(LOG_TAG, message);
	            result.confirm();
	            return true;
	        }
	    }

}
