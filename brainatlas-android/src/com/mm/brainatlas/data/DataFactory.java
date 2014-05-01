package com.mm.brainatlas.data;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.mm.brainatlas_android.R;

import android.content.Context;

public class DataFactory {
	public static final String GET_ITEMS = "items_to_get";
	public static final String DISEASES = "diseases";
	public static final String PARTS = "parts";
	public static final String ITEM_NAME = "name_of_item";	
	
	public static List<String> createItemsList(Context context, String itemsName) throws IllegalArgumentException{
		List<String> result = new ArrayList<String>();
		String type = "";
		if (itemsName.equals(DISEASES)) {
			type = "bd_";
		} else if (itemsName.equals(PARTS)) {
			type = "bp_";
		} else {
			throw new IllegalArgumentException("Items category not found");
		}
		Class<?> rStringClass = R.string.class;
		for (Field f : rStringClass.getFields()) {
			if (f.getName().contains(type) && f.getName().contains("_name")
					&& !f.getName().contains(type + "name")) {
				int resId = -1;
				try {
					resId = f.getInt(rStringClass);
					result.add(context.getText(resId).toString());
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		return result;
	}

	public static int getTitleForType(String type) {
		if (type.equals(DISEASES)) {
			return R.string.bd_title;
		}
		return R.string.bp_title;
	}

	public static BrainInfoLong getInfoForClass(Context context, String tag, String infoSubject) {
		BrainInfoLong result = null;
		if (tag.contains("BrainPartInfoActivity")) {
			result = new NormalBrainPartInfo(context, infoSubject);
		} else if (tag.contains("BrainDiseaseActivity")) {
			result = new BrainDiseaseInfo(context, infoSubject);
		}
		result.generateAllContent(infoSubject);
		
		return result;
	}	

}
