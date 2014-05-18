package com.mm.brainatlas.utils;

import java.lang.reflect.Field;

import android.util.Log;

import com.mm.brainatlas.activities.MainActivity;
import com.mm.brainatlas.activities.SourcesActivity;
import com.mm.brainatlas.activities.StartActivity;
import com.mm.brainatlas_android.R;

public class Utils {
	public static final boolean DEBUG_FLAG = true;
	public static final boolean FOR_STUDIES = true;
	public static final boolean isTestVersion = true;
	public static final boolean ALPHA = true;
	
	public static Class<?> getActivityFromName(String activityName) {
		Class<?> result = null;
		/*if (StartActivity.TAG.contains(activityName)) {
			result = StartActivity.class;
		} else if (MainActivity.TAG.contains(activityName)) {
			result = MainActivity.class;
		} else if (SourcesActivity.TAG.contains(activityName)) {
			result = SourcesActivity.class;
		}*/
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

	public static int getStringFromName(String name) {
		Class<?> refRString = R.string.class;
		String infoName = normalizeName(name);
		for (Field f : refRString.getFields()) {
			if (f.getName().contains(infoName + "_name")){
				try {
					return f.getInt(refRString);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				}
			}				
		}
		return 0;
	}

	public static boolean isEmptyOrNull(String string) {
		if (string == null || string.isEmpty() || string.equals("") || string.length() < 1) {
			return true;
		}
		return false;
	}
	
	public static int getViewNumFromString(String name) throws IllegalStringFormatException{
		String[] vals = name.split("__");
		int result = -1;
		try {
			result = Integer.parseInt(vals[1]);
		} catch (Exception e) {
			throw new IllegalStringFormatException();
		}
		
		return result;
	}

	public static String normalizeName(String chosenName) {
		StringBuilder builder = new StringBuilder();
		for (Character c : chosenName.toLowerCase().toCharArray()) {
			if (c.equals('¹')) {
				builder.append('a');
			} else if (c.equals('æ')) {
				builder.append('c');
			} else if (c.equals('ê')){
				builder.append('e');
			} else if (c.equals('³')) {
				builder.append('l');
			} else if (c.equals('ñ')) {
				builder.append('n');
			} else if (c.equals('ó')) {
				builder.append('o');
			} else if (c.equals('œ')) {
				builder.append('s');
			} else if (c.equals('Ÿ') || c.equals('¿')) {
				builder.append('z');
			} else if (c.equals(' ') || c.equals('-')) {
				builder.append('_');
			} else if (c.equals('(') || c.equals(')')) {
					//do nothing
			} else {
				builder.append(c);
			}
		}
		return builder.toString();
	}

	public static int getNamefromView(String viewName) {
		Class<?> RString = R.string.class;
		
		for (Field field : RString.getFields()) {
			try {
				if (field.getName().contains(viewName + "_name")) {
					return field.getInt(RString);
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	

}
