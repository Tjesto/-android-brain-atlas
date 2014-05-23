package com.mm.brainatlas.data;

import java.lang.reflect.Field;

import com.mm.brainatlas.data.impl.BrainInfoLong;
import com.mm.brainatlas.utils.IllegalStringFormatException;
import com.mm.brainatlas.utils.Utils;
import com.mm.brainatlas_android.R;

import android.content.Context;
import android.util.SparseIntArray;

public class BrainDiseaseInfo extends BrainInfoLong {

	private final SparseIntArray links = new SparseIntArray();
	
	public BrainDiseaseInfo(Context context, String name) {
		super(context, name);
	}

	@Override
	public void generateAllContent(String name) {
		Class<?> reflectionRString = R.string.class;
		Class<?> reflectionRDrawable = R.drawable.class;
		String normalizedName = Utils.normalizeName(name);
		
		for (Field f : reflectionRString.getFields()) {
			if (f.getName().contains("bd_" + normalizedName) && !f.getName().contains("_name") && !f.getName().contains("_label") && !f.getName().contains("_link")){
				try {
					String info = context.getText(f.getInt(reflectionRString)).toString();
					paragraphs.put(Utils.getViewNumFromString(f.getName()), info);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalStringFormatException e) {
					e.printStackTrace();
				}
			}
		}
		
		for (Field f : reflectionRString.getFields()) {
			if (f.getName().contains("bd_" + normalizedName) && !f.getName().contains("_name") && f.getName().contains("_label") && !f.getName().contains("_link")){
				try {
					String info = context.getText(f.getInt(reflectionRString)).toString();
					imageLabels.put(Utils.getViewNumFromString(f.getName()), info);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalStringFormatException e) {
					e.printStackTrace();
				}
			}
		}
		
		for (Field f : reflectionRString.getFields()) {
			if (f.getName().contains("bd_" + normalizedName) && !f.getName().contains("_name") && !f.getName().contains("_label") && f.getName().contains("_link")){
				try {
					String link = context.getText(f.getInt(reflectionRString)).toString();
					int linkId = 0;
					try {
						linkId = reflectionRDrawable.getField(link).getInt(reflectionRDrawable);
					} catch (NoSuchFieldException e) {
						if (link.contains("normal_brain_1")) {
							linkId = R.drawable.normal_brain_1;
						} else if(link.contains("normal_brain_2")) {
							linkId = R.drawable.normal_brain_2;
						}
						e.printStackTrace();
					}
					links.put(Utils.getViewNumFromString(f.getName()), linkId);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalStringFormatException e) {
					e.printStackTrace();
				}
			}
		}
		
		for (Field f : reflectionRDrawable.getFields()) {
			if (f.getName().contains("bd_" + normalizedName) && !f.getName().contains("_name")){
				try {
					images.put(Utils.getViewNumFromString(f.getName()), f.getInt(reflectionRDrawable));
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalStringFormatException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public boolean hasLink(int photo) {
		int foundInt = links.get(photo, -123);
		return foundInt != -123;
	}
	
	public int getLink(int photo) {
		return links.get(photo);
	}

}
