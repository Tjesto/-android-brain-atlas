package com.mm.brainatlas.activities;

import java.lang.reflect.Field;

import com.mm.brainatlas.activities.impl.AbstractBrainActivityWithMenus;
import com.mm.brainatlas.activities.impl.Changable;
import com.mm.brainatlas.listeners.ChangableOnTouchListener;
import com.mm.brainatlas.listeners.OnEmailToSendClickListener;
import com.mm.brainatlas.utils.ApplicationLog;
import com.mm.brainatlas.utils.IllegalStringFormatException;
import com.mm.brainatlas.utils.Pair;
import com.mm.brainatlas.utils.StringToLogParser;
import com.mm.brainatlas.utils.Utils;
import com.mm.brainatlas_android.R;

import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

public final class AppGuideActivity extends AbstractBrainActivityWithMenus
		implements Changable {
	
	public static final String WHERE_AM_I = "where_am_i";
	
	private int currentView;
	private SparseArray<Pair<Integer, String>> guidePages;
	private int currentContentView;
	
	private ChangableOnTouchListener listener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.helper);
		currentContentView = (R.layout.helper);
		listener = new ChangableOnTouchListener(this);
		guidePages = new SparseArray<Pair<Integer,String>>();
		if (getIntent().hasExtra(WHERE_AM_I)) {
			String name = getIntent().getStringExtra(WHERE_AM_I);
			currentView = initializeAndGetPageFromName(name);
		} else {
			initialize();
		}		
		//Version for contact only
		setContactView();
		//end
		updateView();
		listener.addClickListnener(new OnEmailToSendClickListener(this));		
	}
	
	private void setContactView() {
		setContentView(R.layout.contact);
		currentContentView = R.layout.contact;
		listener.block();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return listener.onTouch(getCurrentFocus(), event);
	}

	private void updateView() {
		if (currentView > 0) {	
			if (currentContentView != R.layout.helper) {
				changeContentView(R.layout.helper);
			}
			Pair<Integer, String> actualPage = guidePages.get(currentView);
			ApplicationLog.debugWithFilter("AppGuide", StringToLogParser.parseForWarningLog("AppGuide", "updateView()", actualPage.toString()));
			((ImageView) findViewById(R.id.guide_image)).setImageResource(actualPage.firstElement);
			((TextView) findViewById(R.id.guide_info)).setText(actualPage.secondElement);
		} else {
			if (currentContentView != R.layout.contact) {
				setContentView(R.layout.contact);
				currentContentView = R.layout.contact;
			}
		}
		
	}

	private void changeContentView(int viewId) {
		setContentView(viewId);
		currentContentView = viewId;
	}

	private int initializeAndGetPageFromName(String name) {
		initialize();
		if (name.contains("Brain")) {
			return 2;
		} else if (name.contains("FullImage")) {
			return 3;
		} else if (name.contains("DiseasePartComparer")) {
			return 4;
		}
		return 1;
	}

	private void initialize() {
		Class<?> rDrawable = R.drawable.class;
		SparseIntArray imagesRes = new SparseIntArray();
		for (Field f : rDrawable.getFields()) {
			if (f.getName().contains("guide__")) {
				try {
					imagesRes.put(Utils.getViewNumFromString(f.getName()), f.getInt(rDrawable));
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalStringFormatException e) {
					e.printStackTrace();
				}
			}
		}
		Class<?> rString = R.string.class;
		for (Field f : rString.getFields()) {
			if (f.getName().contains("guide__")) {
				try {
					int viewNum = Utils.getViewNumFromString(f.getName());
					guidePages.put(viewNum,
							new Pair<Integer, String>(imagesRes.get(viewNum),
									getText(f.getInt(rString)).toString()));
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalStringFormatException e) {
					e.printStackTrace();
				}
			}
		}
		currentView = 1;
	}

	@Override
	public void changeView() {
		Direction currentDirection = listener.getDirection();
		switch (currentDirection) {
		case LEFT:
			currentView = (currentView + 1) % (guidePages.size() + 1);			
			break;
		case RIGHT:
			currentView = (currentView - 1) % (guidePages.size() + 1);
			if (currentView < 0) {
				currentView = guidePages.size();
			}
			break;
		case UNKNOWN:
			ApplicationLog.informInternalError(this);
			break;
		}
		Log.wtf("wtf", currentView + "");
		updateView();		
	}

	public boolean isEmailToSend() {
		return (currentContentView == R.layout.contact) || (currentView == 0);
	}

}
