package com.mm.brainatlas.utils;

import com.mm.brainatlas_android.R;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

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

	public static void informInternalError(Context context) {
		Toast.makeText(context, R.string.internal_error, Toast.LENGTH_SHORT).show();

	}
	
}
