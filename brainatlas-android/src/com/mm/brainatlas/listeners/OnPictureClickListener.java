package com.mm.brainatlas.listeners;

import com.mm.brainatlas.activities.BrainDiseaseActivity;
import com.mm.brainatlas.activities.BrainInfoActivity;
import com.mm.brainatlas.activities.FullImageActivity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class OnPictureClickListener implements OnClickListener {

	private final BrainInfoActivity activity;
	private final int resId;
	private final String label;
	
	public OnPictureClickListener(BrainInfoActivity brainInfoActivity, int resId) {
		this(brainInfoActivity, resId, "");
	}

	public OnPictureClickListener(BrainInfoActivity brainInfoActivity,
			int resId, String label) {
		activity = brainInfoActivity;
		this.resId = resId;
		this.label = label;
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(activity, FullImageActivity.class);
		intent.putExtra(FullImageActivity.IMAGE_TO_ENLARGE, resId);
		if (!label.equals("")) {
			Toast.makeText(activity, label, Toast.LENGTH_LONG).show();
		}
		activity.startActivity(intent);
	}

}
