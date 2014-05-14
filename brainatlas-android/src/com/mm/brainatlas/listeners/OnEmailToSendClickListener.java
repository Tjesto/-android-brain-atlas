package com.mm.brainatlas.listeners;

import android.content.Intent;
import android.view.View;
import android.view.View.OnLongClickListener;

import com.mm.brainatlas.activities.AppGuideActivity;
import com.mm.brainatlas.activities.DetailsViewComparerActivity;
import com.mm.brainatlas.utils.EmailEvents;

public class OnEmailToSendClickListener implements OnLongClickListener{

	private final AppGuideActivity activity;

	public OnEmailToSendClickListener(AppGuideActivity activity)
    {
		this.activity = activity;
	}

	@Override
	public boolean onLongClick(View v) {
		if (activity.isEmailToSend())
		EmailEvents.sendTestMail(activity);
		//EmailEvents.sendContactMail(activity);
		return true;
	}

		
}
