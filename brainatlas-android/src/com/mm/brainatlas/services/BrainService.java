package com.mm.brainatlas.services;

import com.mm.brainatlas.BrainNotification;
import com.mm.brainatlas.activities.BrainInfoActivity;
import com.mm.brainatlas.activities.StartActivity;
import com.mm.brainatlas.utils.ApplicationLog;
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
	
	private Class<?> lastUsedActivity;
	
	private Intent lastUsedIntent;
	
	private boolean serviceInitialized = false;

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
			if (!serviceInitialized) {
				initializeService();		
			}
			lastUsedIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(lastUsedIntent);
		} else if (action.equals(ACTION_NOTIFY_ACTIVITY_CHANGE)) {
			String name = intent.getStringExtra(NOTIFY_ACTIVITY_CHANGE_KEY);
			String viewName = "";
			lastUsedActivity = Utils.getActivityFromNameRef(name);
			lastUsedIntent = new Intent(this, lastUsedActivity);
			if (intent.hasExtra(BrainInfoActivity.INFO_TYPE)) {
				viewName = intent.getStringExtra(BrainInfoActivity.INFO_TYPE);
				lastUsedIntent.putExtra(BrainInfoActivity.INFO_TYPE, viewName);
				name = getText(Utils.getNamefromView(Utils.normalizeName(viewName))).toString();
			}
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
		lastUsedActivity = StartActivity.class;
		lastUsedIntent = new Intent(this, lastUsedActivity);
		serviceInitialized = true;
	}

}
