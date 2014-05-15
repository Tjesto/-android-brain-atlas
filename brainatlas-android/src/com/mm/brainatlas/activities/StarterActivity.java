package com.mm.brainatlas.activities;

import java.lang.Thread.UncaughtExceptionHandler;

import com.mm.brainatlas.BrainAtlasUncoughtExceptionHandler;
import com.mm.brainatlas.services.BrainService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class StarterActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = new Intent(this, BrainService.class);
		intent.setAction(BrainService.ACTION_START);
		startService(intent);
		UncaughtExceptionHandler handler = Thread.getDefaultUncaughtExceptionHandler();
		Thread.setDefaultUncaughtExceptionHandler(new BrainAtlasUncoughtExceptionHandler(this, handler));
		finish();
	}
}
