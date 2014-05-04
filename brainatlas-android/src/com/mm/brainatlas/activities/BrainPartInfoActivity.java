package com.mm.brainatlas.activities;

import com.mm.brainatlas.data.DataFactory;
import com.mm.brainatlas.services.BrainService;
import com.mm.brainatlas.utils.Utils;
import com.mm.brainatlas_android.R;

import android.content.Intent;
import android.os.Bundle;

public class BrainPartInfoActivity extends BrainInfoActivity {
	
	public static final String TAG = "com.mm.brainatlas.activities.BrainPartInfoActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.brain_parts);
		Intent intent = new Intent(this, BrainService.class);
		intent.putExtra(BrainService.NOTIFY_ACTIVITY_CHANGE_KEY, TAG);
		intent.setAction(BrainService.ACTION_NOTIFY_ACTIVITY_CHANGE);
		getLayoutParts("bp_");
		String infoSubject = getIntent().getStringExtra(INFO_TYPE);
		if (Utils.isEmptyOrNull(infoSubject)) {
			infoSubject = UNKNOWN_INFO;
		}
		brainInfo = DataFactory.getInfoForClass(this, TAG, infoSubject);
		intent.putExtra(BrainInfoActivity.INFO_TYPE, infoSubject);
		startService(intent);
		matchLayoutPartsWithData();
	}

}
