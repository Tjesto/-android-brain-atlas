package com.mm.brainatlas.activities;

import com.mm.brainatlas_android.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class FullImageActivity extends Activity {
	public static final String IMAGE_TO_ENLARGE = "ite";

	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bp_image);
		ImageView view = (ImageView) findViewById(R.id.bp_image_big);
		int resId = getIntent().getIntExtra(IMAGE_TO_ENLARGE, R.drawable.full_brain_top);
		view.setImageResource(resId);
		view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FullImageActivity.this.finish();
				
			}
		});
	}
}
