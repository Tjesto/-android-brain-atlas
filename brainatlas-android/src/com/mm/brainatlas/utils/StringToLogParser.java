package com.mm.brainatlas.utils;

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
	
}
