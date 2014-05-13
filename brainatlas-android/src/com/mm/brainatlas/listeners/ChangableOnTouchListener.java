package com.mm.brainatlas.listeners;

import java.util.ArrayList;
import java.util.List;

import com.mm.brainatlas.activities.Changable;

import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;

public class ChangableOnTouchListener implements OnTouchListener {

	private final Changable activity;
	private float firstX = -1;
	private float firstY = -1;
	private boolean moved = false;
	private final List<OnLongClickListener> clickListeners;
	
	
	public ChangableOnTouchListener(Changable activity) {
		this.activity = activity;
		clickListeners = new ArrayList<View.OnLongClickListener>();
	}	

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		if (arg1.getAction() == MotionEvent.ACTION_DOWN) {
			firstX = arg1.getX();
			firstY = arg1.getY();
		} else if (arg1.getAction() == MotionEvent.ACTION_MOVE) {
			if (arg1.getX() > firstX + 20 || arg1.getX() < firstX -20) {
				moved = true;
				Log.wtf("listener", "onMove");
			}
		} else if (arg1.getAction() == MotionEvent.ACTION_UP) {
			if (arg1.getX() > firstX + 20 || arg1.getX() < firstX - 20) {
				firstX = -1;
			}
			boolean toMove = moved;
			moved = false;
			boolean clicked = !moved && firstY + 10 > arg1.getY() && firstY - 10 < arg1.getY();
			firstY = -1;
			if (toMove) {
				activity.changeView();
			} else if (clicked) {
				for (OnLongClickListener l : clickListeners) {
					l.onLongClick(arg0);
				}
			}
		}		
		return true;
	}
	
	public void addClickListnener(OnLongClickListener listener) {
		if (!clickListeners.contains(listener)) {
			clickListeners.add(listener);
		}
	}
	
	public void removeClickListener(OnLongClickListener listener) {
		if (clickListeners.contains(listener)) {
			clickListeners.remove(listener);
		}
	}

}
