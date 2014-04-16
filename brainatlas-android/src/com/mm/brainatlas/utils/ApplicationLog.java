package com.mm.brainatlas.utils;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;

public class ApplicationLog {
	
	public static void error(String tag, String message) {
		Log.e(tag, message);
	}
	
	public static void warn(String tag, String message) {
		Log.w(tag, message);		
	}
	
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	public static void debugWithFilter(String tag, String message) {
		Log.wtf(tag, message);
	}
	
}
