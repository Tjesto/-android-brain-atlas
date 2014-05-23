package com.mm.brainatlas.data.impl;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.mm.brainatlas_android.R;

public abstract class SourceInfo {	
	
	private static int sourceId = 1;
	
	protected final int currentSourceId;
	
	protected SourceInfo() {
		currentSourceId = sourceId++;
	}
	
	public abstract String getTitleString();
	
	public abstract String getLink();
	
	public int getSourceId() {
		return currentSourceId;
	}
	
	public static Map<String, Integer> getFromResources() {
		Map<String, Integer> result = new HashMap<String, Integer>();
		Class<?> rStrings = R.string.class;
		for (Field f : rStrings.getFields()) {
			if (f.getName().contains("source_")) {
				try {
					result.put(f.getName(), f.getInt(rStrings));
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}		
	
}
