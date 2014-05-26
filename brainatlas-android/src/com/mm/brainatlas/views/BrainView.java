package com.mm.brainatlas.views;

import java.util.List;

import com.mm.brainatlas.activities.BrainInfoActivity;
import com.mm.brainatlas.activities.BrainPartInfoActivity;
import com.mm.brainatlas.activities.MainActivity;
import com.mm.brainatlas.utils.ApplicationLog;
import com.mm.brainatlas_android.R;

import android.R.color;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class BrainView extends View {
	
	private final Context context;
	private Paint paint;
	private final Bitmap background;
	private float leftPos;
	private float topPos;
	private final String text;
	private Canvas c;
	
	private final List<BrainPartView> brainPartViews;
	private MainActivity parentActivity;
	private boolean isBlocked = false;

	public BrainView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		background = BitmapFactory.decodeResource(getResources(), R.drawable.full_brain_top);
		brainPartViews = BrainPartView.createParts(context, this);
		text = context.getText(R.string.bp_rdzen_przedluzony_medulla_oblongata_name).toString();
	}
	
	public BrainView(Context context) {
		super(context);
		this.context = context;
		background = BitmapFactory.decodeResource(getResources(), R.drawable.full_brain_top);
		brainPartViews = BrainPartView.createParts(context, this);
		text = context.getText(R.string.bp_rdzen_przedluzony_medulla_oblongata_name).toString();
	}
	
	public void setActivity(MainActivity activity) {
		this.parentActivity = activity;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (c == null) {
			c = canvas;
		}
		if (paint == null) {
			paint = new Paint();
		}
		canvas.drawColor(color.background_dark);
		leftPos = (getWidth()-background.getWidth())/2;
		topPos = (getHeight()-background.getHeight())/2;
		canvas.drawBitmap(background, leftPos, topPos, paint);
		for (BrainPartView view : brainPartViews) {
			view.draw(canvas, leftPos, topPos);
		}		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (isBlocked) {
			return false;
		}
		if (event.getAction() == MotionEvent.ACTION_UP) {
			for (BrainPartView view : brainPartViews) {
				if (view.onTouchEvent(event)) {
					return true;
				}
			}
			if (checkCoordinates(event.getX(), event.getY())) {
				ApplicationLog.warn("BrainView", "No childView returned true");				
			}
		}
		return true;
	}
	
	private boolean checkCoordinates(float x, float y) {		
		return x > (c.getWidth() - background.getWidth()/2) / 2
				&& x < background.getWidth()
				&& y > (2*c.getHeight()/ 3)
				&& y < background.getHeight();
	}

	public MainActivity getActivity() {		 
		return parentActivity;
	}

	public void blockClicking() {
		isBlocked  = true;
		
	}

	public void unblockClicking() {
		isBlocked = false;
		
	}

}
