package com.mm.brainatlas.utils;

import android.util.Log;

import com.mm.brainatlas.activities.MainActivity;
import com.mm.brainatlas.activities.SourcesActivity;
import com.mm.brainatlas.activities.StartActivity;

public class Utils {
	public static final boolean DEBUG_FLAG = true;
	public static final boolean FOR_STUDIES = true;
	
	public static Class<?> getActivityFromName(String activityName) {
		Class<?> result = null;
		if (StartActivity.TAG.contains(activityName)) {
			result = StartActivity.class;
		} else if (MainActivity.TAG.contains(activityName)) {
			result = MainActivity.class;
		} else if (SourcesActivity.TAG.contains(activityName)) {
			result = SourcesActivity.class;
		}
		return result;
	}
	
	public static Class<?> getActivityFromNameRef(String activityName) {
		Class<?> result = null;		
		try {
			result = Class.forName(activityName);
		} catch (ClassNotFoundException e) {
			ApplicationLog.error("Utils", e.toString());
		}
		return result;
	}
	
	public static String getNameFromTag(String name) {
		String[] parts = name.split("\\.");
		return parts[parts.length - 1];
	}
	

}
