package com.mm.brainatlas.utils;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.util.Date;

import android.text.Html;

public final class StringToLogParser {
	public static String parseForErrorLog(String tag, String methodName, String...other) {
		StringBuilder builder = new StringBuilder("An error occured in ");
		builder.append(Utils.getNameFromTag(tag));
		builder.append(" during executing method ");
		builder.append(methodName);
		if (other != null && other.length > 0){
			builder.append(" other info:\n");		
			for (String s : other) {					
				builder.append(s).append(";");
				builder.append("\n");
			}
			builder.deleteCharAt(builder.length() - 1);
		}
		return builder.toString();
	}
	
	public static String parseForWarningLog(String tag, String methodName, String...other) {
		StringBuilder builder = new StringBuilder("Unusual situation in class ");
		builder.append(Utils.getNameFromTag(tag));
		builder.append(" during executing method ");
		builder.append(methodName);
		if (other != null && other.length > 0){
			builder.append(" other info:\n");		
			for (String s : other) {					
				builder.append(s).append(";");
				builder.append("\n");
			}
			builder.deleteCharAt(builder.length() - 1);
		}
		return builder.toString();
	}

	public static String getExceptionEmailSubject(Throwable ex) {
		StringBuilder subject = new StringBuilder("[");
		subject.append(ex.getClass().getName());
		subject.append("] ").append("Application crash");
		return subject.toString();
	}

	public static String getExceptionEmailText(Thread thread, Throwable ex) {
		StringBuilder text = new StringBuilder("Application crashed on ");
		StringWriter stackTraceWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stackTraceWriter);
		ex.printStackTrace(writer);
		text.append(DateFormat.getDateTimeInstance(DateFormat.FULL,
				DateFormat.FULL).format(new Date(System.currentTimeMillis())));
		text.append("\n\n").append("Thread info:\n");
		text.append(thread.toString()).append("\n\n");
		text.append("Throwable info:\n");
		text.append(ex.toString()).append("\n\n");
		text.append(stackTraceWriter.toString());
		return text.toString();
	}
	
}
