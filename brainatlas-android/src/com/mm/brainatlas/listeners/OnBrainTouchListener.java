package com.mm.brainatlas.listeners;

import com.mm.brainatlas.activities.MainActivity;
import com.mm.brainatlas.utils.ApplicationLog;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

@Deprecated
public class OnBrainTouchListener implements OnTouchListener {

	private final MainActivity activity;
	
	private final ImageView brainView;
	
	public OnBrainTouchListener(MainActivity mainActivity, ImageView brainView) {
		activity = mainActivity;
		this.brainView = brainView;
	}

	@Override
	public boolean onTouch(View view, MotionEvent event) {
		Bitmap map = ((BitmapDrawable)brainView.getDrawable()).getBitmap();
		int x = (int) event.getX();
		int y = (int) event.getY();
		int color = map.getPixel((x >= map.getWidth() ? map.getWidth()-1 : x),(y >= map.getHeight() ? map.getHeight()-1 : y));		
		ApplicationLog.debugWithFilter(
				"OnBrainTouchListener",
				"Color in " + event.getX() + ", " + event.getY() + ": " + color
						+ "(" + Integer.toHexString(color)
						+ ")\nbitmap size = " + map.getWidth() + "x"
						+ map.getHeight() + "\nimageView size = "
						+ brainView.getWidth() + "x" + brainView.getHeight());
		return true;
	}

}
