package com.mm.brainatlas.activities;

import com.mm.brainatlas.listeners.GoToWebsiteEventHandler;
import com.mm.brainatlas_android.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class WebViewActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String url = getIntent().getStringExtra(GoToWebsiteEventHandler.WEB_KEY);
		if (url == null || url.equals("")) {
			startActivity(new Intent(this, SourcesActivity.class));
			finish();
			return;
		} 
		setContentView(R.layout.web);
		WebView webView = (WebView) findViewById(R.id.web_view);
		webView.loadUrl(url);
	}
}
