package com.mm.brainatlas.data;

import java.util.List;

import android.content.Context;
import android.widget.AdapterView.OnItemClickListener;

public class DataFactory {
	public static final String GET_ITEMS = "items_to_get";
	public static final String DISEASES = "diseases";
	public static final String PARTS = "parts";

	public static OnItemClickListener getOnItemClickListener(
			Context context, String itemsName) {
		if (itemsName.equals(DISEASES)) {
			//return new listener for diseases
		} else if (itemsName.equals(PARTS)) {
			//return new listener for parts
		} else {
			throw new IllegalArgumentException("Items category not found");
		}
		
		return null;
	}	
	
	public static List<String> createItemsList(String itemsName) throws IllegalArgumentException{
		if (itemsName.equals(DISEASES)) {
			//return list of diseases
		} else if (itemsName.equals(PARTS)) {
			//return list of parts
		} else {
			throw new IllegalArgumentException("Items category not found");
		}
		return null;
	}

}
