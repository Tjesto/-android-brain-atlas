package com.mm.brainatlas.views;

import java.util.ArrayList;
import java.util.List;

import com.mm.brainatlas.activities.BrainInfoActivity;
import com.mm.brainatlas.activities.BrainPartInfoActivity;
import com.mm.brainatlas_android.R;

import android.R.color;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class BrainPartView extends View {

	private final Context context;
	private Paint paint;
	private final Bitmap background;
	private float leftPos;
	private float topPos;
	private String partName;
	private final BrainView parent;
	
	public BrainPartView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		background = BitmapFactory.decodeResource(getResources(), R.drawable.full_brain_top);
		parent = null;
	}
	
	public BrainPartView(Context context, int part, String partName, BrainView parent) {
		super(context);
		this.context = context;
		background = BitmapFactory.decodeResource(getResources(), part);
		this.partName = partName;
		this.parent = parent;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (paint == null) {
			paint = new Paint();
		}
		canvas.drawColor(color.background_dark);
		canvas.drawBitmap(background, leftPos, topPos, paint);
	}

	public static List<BrainPartView> createParts(Context context, BrainView parent) {
		List<BrainPartView> result = new ArrayList<BrainPartView>();
		result.add(new BrainPartView(context, R.drawable.bp_mozdzek_view, context.getText(R.string.bp_mozdzek_cerebellum_name).toString(), parent));
		result.add(new BrainPartView(context, R.drawable.bp_plat_ciemieniowy_view, context.getText(R.string.bp_plat_ciemieniowy_lobus_parientalis_name).toString(), parent));
		result.add(new BrainPartView(context, R.drawable.bp_plat_czolowy_view, context.getText(R.string.bp_plat_czolowy_lobus_frontalis_name).toString(), parent));
		result.add(new BrainPartView(context, R.drawable.bp_plat_potyliczny_view, context.getText(R.string.bp_plat_potyliczny_lobus_occipitalis_name).toString(), parent));
		result.add(new BrainPartView(context, R.drawable.bp_plat_skroniowy_view, context.getText(R.string.bp_plat_skroniowy_lobus_temporalis_name).toString(), parent));
		return result;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_UP && isSomethingHere(event.getX()-leftPos, event.getY() - topPos)) {
			Intent intent = new Intent(context, BrainPartInfoActivity.class);
			intent.putExtra(BrainInfoActivity.INFO_TYPE, partName);
			parent.getActivity().startInfoActivity(intent, partName);
			return true;
		}
		return false;
	}

	private boolean isSomethingHere(float x, float y) {
		if (x > background.getWidth() || y > background.getHeight() || x < 0 || y < 0) {
			return false;
		}
		if (background.getPixel((int)x, (int)y) != 0) {
			return true;
		}
		return false;
	}

	public void draw(Canvas canvas, float leftPos, float topPos) {
		this.leftPos = leftPos;
		this.topPos = topPos;
		draw(canvas);
		
	}
}
