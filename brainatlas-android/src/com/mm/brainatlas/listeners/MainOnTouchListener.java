package com.mm.brainatlas.listeners;

import com.mm.brainatlas.activities.MainActivity;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class MainOnTouchListener implements OnTouchListener {

	private final MainActivity activity;
	private float firstX = -1;
	private boolean moved = false;
	
	
	public MainOnTouchListener(MainActivity activity) {
		this.activity = activity;
	}

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		if (arg1.getAction() == MotionEvent.ACTION_DOWN) {
			firstX = arg1.getX();
		} else if (arg1.getAction() == MotionEvent.ACTION_MOVE) {
			if (arg1.getX() > firstX + 20 || arg1.getX() < firstX -20) {
				moved = true;
				Log.wtf("listener", "onMove");
			}
		} else if (arg1.getAction() == MotionEvent.ACTION_UP) {
			if (arg1.getX() > firstX + 20 || arg1.getX() < firstX -20) 
			firstX = -1;
			boolean toMove = moved;
			moved = false;
			if (toMove) {
				activity.changeView();
			}
		}		
		return true;
	}

}
