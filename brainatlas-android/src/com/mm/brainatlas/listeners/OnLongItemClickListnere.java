package com.mm.brainatlas.listeners;

import com.mm.brainatlas.activities.DetailsViewComparerActivity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnLongClickListener;

public class OnLongItemClickListnere implements OnLongClickListener {

	private DetailsViewComparerActivity activity;
	private final Intent startIntent;
	
	private boolean isDisease = true;	

	public OnLongItemClickListnere(DetailsViewComparerActivity activity, Intent startIntent) {
		this.activity = activity;
		this.startIntent = startIntent;
	}
	
	public void notifyViewChange(boolean isDisease) {
		this.isDisease = isDisease;
	}
	
	@Override
	public boolean onLongClick(View v) {
		if (isDisease) {
			OnImageLongClickHandler.handleDiseaseImageClick(activity);
		} else {
			OnImageLongClickHandler.handleBrainPartImageClick(activity, startIntent);
		}
		return true;
	}

}
