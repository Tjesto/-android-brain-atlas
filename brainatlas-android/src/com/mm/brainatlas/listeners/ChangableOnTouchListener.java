package com.mm.brainatlas.listeners;

import com.mm.brainatlas.activities.Changable;

import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;

public class ChangableOnTouchListener implements OnTouchListener, OnDragListener {

	private final Changable activity;
	private float firstX = -1;
	private boolean moved = false;
	
	
	public ChangableOnTouchListener(Changable activity) {
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

	@Override
	public boolean onDrag(View v, DragEvent event) {
		if (event.getAction() == DragEvent.ACTION_DRAG_STARTED) {
			firstX = event.getX();					
		} else if (event.getAction() == DragEvent.ACTION_DRAG_ENDED) {
			if (event.getX() > firstX + 20 || event.getX() < firstX -20) 
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
