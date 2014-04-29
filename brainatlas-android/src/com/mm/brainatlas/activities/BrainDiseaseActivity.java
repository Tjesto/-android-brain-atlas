package com.mm.brainatlas.activities;

import com.mm.brainatlas.data.DataFactory;
import com.mm.brainatlas.utils.Utils;
import com.mm.brainatlas_android.R;

import android.os.Bundle;

public class BrainDiseaseActivity extends BrainInfoActivity {

	public static final String TAG = "com.mm.brainatlas.activities.BrainDiseaseActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.brain_disease);
		getLayoutParts("bd_");
		String infoSubject = getIntent().getStringExtra(INFO_TYPE);
		if (Utils.isEmptyOrNull(infoSubject)) {
			infoSubject = UNKNOWN_INFO;
		}
		brainInfo = DataFactory.getInfoForClass(TAG, infoSubject);
	}
	
}
