package com.mm.brainatlas.listeners;

import java.util.ArrayList;
import java.util.List;

import com.mm.brainatlas.activities.impl.Changable;
import com.mm.brainatlas.activities.impl.Changable.Direction;
import com.mm.brainatlas.listeners.impl.BrainOnTouchEventListener;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;

public class ChangableOnTouchListener extends BrainOnTouchEventListener {		

	public ChangableOnTouchListener(Changable activity) {
		super(activity);
	}

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		if (arg1.getAction() == MotionEvent.ACTION_DOWN) {
			firstX = arg1.getX();
			firstY = arg1.getY();
			currentDirection = Direction.UNKNOWN;
		} else if (arg1.getAction() == MotionEvent.ACTION_MOVE) {
			if (arg1.getX() > firstX + 20 || arg1.getX() < firstX -20) {
				moved = true;
				Log.wtf("listener", "onMove");
			}
		} else if (arg1.getAction() == MotionEvent.ACTION_UP) {
			if (arg1.getX() > firstX + 20 || arg1.getX() < firstX - 20) {
				if (firstX > arg1.getX()) {
					currentDirection = Direction.LEFT;
				} else if (firstX < arg1.getX()) {
					currentDirection = Direction.RIGHT;
				}
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

	public Direction getDirection() {
		return currentDirection;
	}

}
