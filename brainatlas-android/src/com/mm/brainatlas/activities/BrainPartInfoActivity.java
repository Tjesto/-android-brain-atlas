package com.mm.brainatlas.activities;

import com.mm.brainatlas.data.DataFactory;
import com.mm.brainatlas.utils.Utils;
import com.mm.brainatlas_android.R;

import android.os.Bundle;

public class BrainPartInfoActivity extends BrainInfoActivity {
	
	public static final String TAG = "com.mm.brainatlas.activities.BrainPartInfoActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.brain_parts);
		getLayoutParts("bp_");
		String infoSubject = getIntent().getStringExtra(INFO_TYPE);
		if (Utils.isEmptyOrNull(infoSubject)) {
			infoSubject = UNKNOWN_INFO;
		}
		brainInfo = DataFactory.getInfoForClass(this, TAG, infoSubject);
		matchLayoutPartsWithData();
	}

}
