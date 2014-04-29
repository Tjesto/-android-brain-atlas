package com.mm.brainatlas.activities;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import android.widget.ImageView;
import android.widget.TextView;

import com.mm.brainatlas.data.BrainInfoLong;
import com.mm.brainatlas_android.R;

public abstract class BrainInfoActivity extends AbstractBrainActivityWithMenus {
	
	public static final String TAG = "com.mm.brainatlas.activities.BrainInfoActivity";
	public static final String INFO_TYPE = "is_it_part_or_disease";
	public static final String UNKNOWN_INFO = "incorect_type";
	
	protected List<TextView> textViews;
	protected List<ImageView> imageViews;
	protected BrainInfoLong brainInfo;
	
	public void getLayoutParts(String prefix) {
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
}
