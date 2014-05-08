package com.mm.brainatlas.listeners;

import com.mm.brainatlas.activities.WebViewActivity;

import android.content.Context;
import android.content.Intent;

public class GoToWebsiteEventHandler {

	public static final String WEB_KEY = "url";
	
	private Context context;
	
	public GoToWebsiteEventHandler(Context context) {
		this.context = context;
	}

	public void handle(String link) {
		Intent webIntent = new Intent(context, WebViewActivity.class);
		webIntent.putExtra(WEB_KEY, link);
		context.startActivity(webIntent);
	}

}
