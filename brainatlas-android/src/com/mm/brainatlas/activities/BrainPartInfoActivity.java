package com.mm.brainatlas.activities;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.mm.brainatlas.data.DataFactory;
import com.mm.brainatlas.data.NormalBrainPartInfo;
import com.mm.brainatlas_android.R;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class BrainPartInfoActivity extends AbstractBrainActivityWithMenus {
	
	public static final String TAG = "com.mm.brainatlas.activities.BrainPartInfoActivity";
	
	private List<TextView> textViews;
	private List<ImageView> imageViews;
	private NormalBrainPartInfo brainInfo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.brain_parts);
		textViews = new ArrayList<TextView>();
		imageViews = new ArrayList<ImageView>();
		Class<?> refClass = R.id.class;
		for (Field f : refClass.getFields()) {
			if (f.getName().contains("bp_textview")) {
				try {
					textViews.add((TextView) findViewById(f.getInt(refClass)));
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				}
			} else if (f.getName().contains("bp_imageview")) {
				try {
					imageViews.add((ImageView) findViewById(f.getInt(refClass)));
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				}
			}
		}
		
		brainInfo = DataFactory.getInfoForClass(TAG);
		
	}

}
