package com.mm.brainatlas.listeners;

import com.mm.brainatlas.activities.BrainPartInfoActivity;
import com.mm.brainatlas.activities.FullImageActivity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class OnPictureClickListener implements OnClickListener {

	private final BrainPartInfoActivity activity;
	private final int resId;
	
	public OnPictureClickListener(BrainPartInfoActivity brainPartInfoActivity, int resId) {
		activity = brainPartInfoActivity;
		this.resId = resId;
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(activity, FullImageActivity.class);
		intent.putExtra(FullImageActivity.IMAGE_TO_ENLARGE, resId);
		activity.startActivity(intent);
	}

}
