package com.mm.brainatlas.listeners;

import com.mm.brainatlas.activities.MainActivity;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class MainOnTouchListener implements OnTouchListener {

	private final MainActivity activity;
	private float firstX = -1;
	
	
	public MainOnTouchListener(MainActivity activity) {
		this.activity = activity;
	}

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		if (arg1.getAction() == MotionEvent.ACTION_DOWN) {
			firstX = arg1.getX();
		} else if (arg1.getAction() == MotionEvent.ACTION_UP) {
			if (arg1.getX() > firstX + 5 || arg1.getX() < firstX -5) 
			firstX = -1;
			activity.changeView();
		}		
		return true;
	}

}
