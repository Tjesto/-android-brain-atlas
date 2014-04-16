package com.mm.brainatlas.services;

import com.mm.brainatlas.BrainNotification;
import com.mm.brainatlas.utils.Utils;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class BrainService extends Service {
	
	public static final String TAG = "com.mm.brainatlas.services.BrainService";

	public static final String ACTION_START = TAG + ".ACTION_START";
	public static final String ACTION_NOTIFY_ACTIVITY_CHANGE = TAG + ".ACTION_NOTIFY_ACTIVITY_CHANGE";
	public static final String NOTIFY_ACTIVITY_CHANGE_KEY = "NOTIFY_ACTIVITY_CHANGE";

	public static final String ACTION_EXIT = TAG + ".ACTION_EXIT";
	
	private BrainNotification brainNotification;

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		String action = intent.getAction();
		if (action == null) {
			return super.onStartCommand(intent, flags, startId);
		}
		
		if (action.equals(ACTION_START)) {
			initializeService();			
		} else if (action.equals(ACTION_NOTIFY_ACTIVITY_CHANGE)) {
			String name = intent.getStringExtra(NOTIFY_ACTIVITY_CHANGE_KEY);
			if (brainNotification != null) {
				if (name == null) {
					name = "";
				}	
				brainNotification.update(name);
			}
		} else if (action.equals(ACTION_EXIT)) {
			brainNotification.cancel();
			stopSelf();
		}
		
		return START_STICKY;		
	}

	private void initializeService() {
		brainNotification = new BrainNotification(this);
		startForeground(BrainNotification.NOTIFICATION_ID, brainNotification.getNotification());		
	}

}
