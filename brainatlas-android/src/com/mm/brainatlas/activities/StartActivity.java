package com.mm.brainatlas.activities;

import com.mm.brainatlas.Utils;
import com.mm.brainatlas.services.BrainService;
import com.mm.brainatlas_android.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

public class StartActivity extends Activity {
	
	public static final String TAG = "com.mm.brainatlas.activities.StartActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		if (Utils.FOR_STUDIES) {
			((TextView) findViewById(R.id.studies_info_textview)).setVisibility(View.VISIBLE);
		} else {
			((TextView) findViewById(R.id.studies_info_textview)).setVisibility(View.INVISIBLE);
		}
		Intent intent = new Intent(this, BrainService.class);
		intent.setAction(BrainService.ACTION_START);
		startService(intent);
		new Thread(new Runnable(){

			@Override
			public void run() {				
				try {
					Thread.sleep(5000);					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				StartActivity.this.startActivity(new Intent(StartActivity.this, MainActivity.class));
				StartActivity.this.finish();
			}
			
		}).start();
	}
		
}
