package com.mm.brainatlas.listeners.impl;

import java.util.ArrayList;
import java.util.List;

import com.mm.brainatlas.activities.impl.Changable;
import com.mm.brainatlas.activities.impl.Changable.Direction;

import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;

public abstract class BrainOnTouchEventListener implements OnTouchListener {
	protected final Changable activity;
	protected float firstX = -1;
	protected float firstY = -1;
	protected boolean moved = false;
	protected final List<OnLongClickListener> clickListeners;
	protected Direction currentDirection = Direction.UNKNOWN;
	
	
	public BrainOnTouchEventListener(Changable activity) {
		this.activity = activity;
		clickListeners = new ArrayList<View.OnLongClickListener>();
	}
}
