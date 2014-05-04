package com.mm.brainatlas;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

import com.mm.brainatlas.activities.BrainInfoActivity;
import com.mm.brainatlas.services.BrainService;
import com.mm.brainatlas.utils.Utils;
import com.mm.brainatlas_android.R;

public class BrainNotification {
	
	public static final String TAG = "com.mm.brainatlas.BrainNotification";

	public static final int NOTIFICATION_ID = 17634;
	
	private BrainService brainService;
	
	private NotificationManager notificationManager;

	private String currentViewName = null;
	
	public BrainNotification(BrainService brainService) {
		this.brainService = brainService;
		notificationManager = (NotificationManager) brainService.getSystemService(Context.NOTIFICATION_SERVICE);
	}

	public Notification getNotification() {
		return getNotification("");
	}

	private Notification getNotification(String activityName) {
		return buildNotification(activityName);
	}
	
	public void putCurrentViewName(String name) {
		currentViewName = name;
	}

	private Notification buildNotification(String activityName) {
		Notification builderNotification;
		Bitmap largeIcon = BitmapFactory.decodeResource(brainService.getResources(), R.drawable.large_notification);
		NotificationCompat.Builder builder = new NotificationCompat.Builder(
				brainService).setSmallIcon(R.drawable.small_notification)
				.setLargeIcon(largeIcon)
				.setContentTitle(brainService.getText(R.string.app_name))
				.setContentText(Utils.getNameFromTag(activityName));
		if (activityName != null && !activityName.equals("")) {
			Intent intent = new Intent(brainService, Utils.getActivityFromNameRef(activityName));
			if (currentViewName  != null){
				intent.putExtra(BrainInfoActivity.INFO_TYPE, currentViewName);
				currentViewName = null;
			}
			PendingIntent pIntent = PendingIntent.getActivity(brainService, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
			builder.setContentIntent(pIntent);
		}
		builderNotification = builder.build();
		return builderNotification;
	}

	public void update(String name) {
		if (notificationManager != null) {
			notificationManager.cancel(NOTIFICATION_ID);
		}		
		notify(name);
	}

	private void notify(String name) {
		Notification notification = buildNotification(name);
		notificationManager.notify(NOTIFICATION_ID, notification);		
	}
	
	public void cancel() {
		if (notificationManager != null) {
			notificationManager.cancel(NOTIFICATION_ID);
		}	
	}

}
