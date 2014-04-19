package com.mm.brainatlas.activities;

import com.mm.brainatlas.services.BrainService;
import com.mm.brainatlas_android.R;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class SourcesActivity extends AbstractBrainActivityWithMenus {
	
	public static final String TAG = "com.mm.brainatlas.activities.SourcesActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = new Intent(this, BrainService.class);
		intent.putExtra(BrainService.NOTIFY_ACTIVITY_CHANGE_KEY, TAG);
		intent.setAction(BrainService.ACTION_NOTIFY_ACTIVITY_CHANGE);
		startService(intent);
		setContentView(R.layout.sources);
	}
	
	@Override
	protected void showSourcesActivity() {
		Log.i(TAG, "Activity actually running");
	}	
}