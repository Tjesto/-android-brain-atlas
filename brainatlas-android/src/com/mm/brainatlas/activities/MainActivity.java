package com.mm.brainatlas.activities;

import com.mm.brainatlas.listeners.MainOnTouchListener;
import com.mm.brainatlas.services.BrainService;
import com.mm.brainatlas.utils.ApplicationLog;
import com.mm.brainatlas.utils.StringToLogParser;
import com.mm.brainatlas_android.R;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AbstractBrainActivityWithMenus {
	
	enum ViewMode {
		TOP, INNER;
	}
	
	public static final String TAG = "com.mm.brainatlas.activities.MainActivity";
	
	private TextView topView;
	
	private TextView innerView;
	
	private ViewMode currentViewMode = ViewMode.TOP; 
	
	private MainOnTouchListener listener;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = new Intent(this, BrainService.class);
		intent.putExtra(BrainService.NOTIFY_ACTIVITY_CHANGE_KEY, TAG);
		intent.setAction(BrainService.ACTION_NOTIFY_ACTIVITY_CHANGE);
		startService(intent);
		setContentView(R.layout.main);
		topView = (TextView) findViewById(R.id.view_mode_top);
		innerView = (TextView) findViewById(R.id.view_mode_inner);
		listener = new MainOnTouchListener(this);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return listener.onTouch(getCurrentFocus(), event);
	}
	@Override
	public void onBackPressed() {
		finish();
	}
	@SuppressLint("ResourceAsColor")
	public void changeView() {
		if (currentViewMode == ViewMode.TOP) {
			topView.setTextColor(getResources().getColor(R.color.gray));
			innerView.setTextColor(getResources().getColor(R.color.white));			
			prepareInnerView();			
		} else {
			topView.setTextColor(getResources().getColor(R.color.white));
			innerView.setTextColor(getResources().getColor(R.color.gray));
			prepareTopView();
		}
	}
	private void prepareTopView() {
		// TODO to implement
		((ImageView) findViewById(R.id.part_view)).setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.tmp_shot1));
		currentViewMode = ViewMode.TOP;
		
	}
	private void prepareInnerView() {
		// TODO to implement
		((ImageView) findViewById(R.id.part_view)).setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.tmp_shot3));
		currentViewMode = ViewMode.INNER;
		
	}
	
}
