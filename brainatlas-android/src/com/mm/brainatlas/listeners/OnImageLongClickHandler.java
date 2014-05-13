package com.mm.brainatlas.listeners;

import android.content.Intent;

import com.mm.brainatlas.activities.DetailsViewComparerActivity;

public final class OnImageLongClickHandler {

	public static void handleDiseaseImageClick(
			DetailsViewComparerActivity activity) {
		activity.finish();
		
	}

	public static void handleBrainPartImageClick(
			DetailsViewComparerActivity activity, Intent startIntent) {
		android.util.Log.wtf("wtf", startIntent + "");
		activity.startActivity(startIntent);
		activity.finish();
		
	}

}
