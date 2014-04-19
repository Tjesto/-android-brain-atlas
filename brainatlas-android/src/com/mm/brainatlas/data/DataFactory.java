package com.mm.brainatlas.data;

import java.util.ArrayList;
import java.util.List;

import com.mm.brainatlas_android.R;

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
	
	public static List<String> createItemsList(Context context, String itemsName) throws IllegalArgumentException{
		List<String> result = new ArrayList<String>();
		if (itemsName.equals(DISEASES)) {
			result.add(context.getText(R.string.bd_aizhalmer).toString());
			result.add(context.getText(R.string.bd_aneurysm).toString());
			result.add(context.getText(R.string.bd_brain_cancer).toString());
			result.add(context.getText(R.string.bd_huntington).toString());
			result.add(context.getText(R.string.bd_meningitis).toString());
			result.add(context.getText(R.string.bd_multiple_sclerosis).toString());
			result.add(context.getText(R.string.bd_parkinson).toString());
			result.add(context.getText(R.string.bd_stroke).toString());
			result.add(context.getText(R.string.bd_tick_borne_encephalitis).toString());
		} else if (itemsName.equals(PARTS)) {
			result.add("Opcja w trakcie");
			result.add("implementacji");
		} else {
			throw new IllegalArgumentException("Items category not found");
		}
		return result;
	}

}
