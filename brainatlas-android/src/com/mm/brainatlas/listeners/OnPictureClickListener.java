package com.mm.brainatlas.listeners;

import com.mm.brainatlas.activities.BrainPartInfoActivity;
import com.mm.brainatlas.activities.FullImageActivity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class OnPictureClickListener implements OnClickListener {

	private final BrainPartInfoActivity activity;
	private final int resId;
	private final String label;
	
	public OnPictureClickListener(BrainPartInfoActivity brainPartInfoActivity, int resId) {
		this(brainPartInfoActivity, resId, "");
	}

	public OnPictureClickListener(BrainPartInfoActivity brainPartInfoActivity,
			int resId, String label) {
		activity = brainPartInfoActivity;
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
