package com.mm.brainatlas;

import java.lang.Thread.UncaughtExceptionHandler;

import com.mm.brainatlas.utils.EmailEvents;

import android.content.Context;

public class BrainAtlasUncoughtExceptionHandler implements
		UncaughtExceptionHandler {
	
	private Context context;
	private UncaughtExceptionHandler handler;

	public BrainAtlasUncoughtExceptionHandler(Context context, UncaughtExceptionHandler defaultHandler) {
		this.context = context;
		this.handler = defaultHandler;
	}

	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		EmailEvents.sendExceptionInfo(context, thread, ex);	
		handler.uncaughtException(thread, ex);
	}

}
