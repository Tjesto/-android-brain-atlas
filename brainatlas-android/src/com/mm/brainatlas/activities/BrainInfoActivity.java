package com.mm.brainatlas.activities;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;

import com.mm.brainatlas.activities.impl.AbstractBrainActivityWithMenus;
import com.mm.brainatlas.data.impl.BrainInfo;
import com.mm.brainatlas.data.impl.BrainInfoLong;
import com.mm.brainatlas.utils.ApplicationLog;
import com.mm.brainatlas.utils.StringToLogParser;
import com.mm.brainatlas.utils.Utils;
import com.mm.brainatlas_android.R;

public abstract class BrainInfoActivity extends AbstractBrainActivityWithMenus {
	
	public static final String TAG = "com.mm.brainatlas.activities.BrainInfoActivity";
	public static final String INFO_TYPE = "is_it_part_or_disease";
	public static final String UNKNOWN_INFO = "incorect_type";
	
	protected List<TextView> textViews;
	protected List<ImageView> imageViews;
	protected BrainInfoLong brainInfo;
	
	protected void getLayoutParts(String prefix) {
		textViews = new ArrayList<TextView>();
		imageViews = new ArrayList<ImageView>();
		String text = prefix + "textview";
		String image = prefix + "imageview";
		Class<?> refClass = R.id.class;
		for (Field f : refClass.getFields()) {
			if (f.getName().contains(text)) {
				try {
					textViews.add((TextView) findViewById(f.getInt(refClass)));
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				}
			} else if (f.getName().contains(image)) {
				try {
					imageViews.add((ImageView) findViewById(f.getInt(refClass)));
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	protected void matchLayoutPartsWithData() {
		if (Utils.DEBUG_FLAG) {
			ApplicationLog.debugWithFilter(TAG, StringToLogParser
					.parseForWarningLog(TAG, "matchLayoutPartsWithData",
							textViews.size() + "", imageViews.size() + "",
							brainInfo.toString()));
		}
		for (TextView tv : textViews) {
			tv.setVisibility(View.GONE);
		}
		
		for (ImageView iv : imageViews) {
			iv.setVisibility(View.GONE);
		}
		
		for (int i = 1; i < textViews.size(); i++) {			
			String result = brainInfo.getContent(i);
			if (result.equals(BrainInfo.S_NOT_FOUND)) {
				continue;
			} else {
				textViews.get(i).setText(result);
				textViews.get(i).setVisibility(View.VISIBLE);
			}
			
		}
		textViews.get(0).setText(brainInfo.getName());
		textViews.get(0).setVisibility(View.VISIBLE);
		for (int i = 0; i < imageViews.size(); i++) {			
			int result = brainInfo.getImage(i+1);
			if (result == BrainInfo.I_NOT_FOUND) {
				continue;
			} else {
				imageViews.get(i).setImageBitmap(BitmapFactory.decodeResource(getResources(), result));
				imageViews.get(i).setVisibility(View.VISIBLE);
			}
		}
		
		//zeroDimensions();
	}

	private void zeroDimensions() {
		for (TextView tv : textViews) {
			if (tv.getVisibility() != View.VISIBLE) {
				tv.setLayoutParams(new LayoutParams(0, 0));
			}
		}
		
		for (ImageView iv : imageViews) {
			if (iv.getVisibility() != View.VISIBLE) {
				iv.setLayoutParams(new LayoutParams(0, 0));
			}
		}
		
	}
}
