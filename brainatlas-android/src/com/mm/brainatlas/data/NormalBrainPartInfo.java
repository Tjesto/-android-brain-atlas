package com.mm.brainatlas.data;

import java.lang.reflect.Field;

import com.mm.brainatlas.utils.IllegalStringFormatException;
import com.mm.brainatlas.utils.Utils;
import com.mm.brainatlas_android.R;

import android.content.Context;

public class NormalBrainPartInfo extends BrainInfoLong {

	public NormalBrainPartInfo(Context context, String name) {
		super(context, name);
	}

	@Override
	public void generateAllContent(String name) {
		Class<?> reflectionRString = R.string.class;
		Class<?> reflectionRDrawable = R.drawable.class;
		String normalizedName = Utils.normalizeName(name);
		
		for (Field f : reflectionRString.getFields()) {
			if (f.getName().contains("bp_" + normalizedName) && !f.getName().contains("_name")){
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
		
		for (Field f : reflectionRDrawable.getFields()) {
			if (f.getName().contains("bp_" + normalizedName) && !f.getName().contains("_name")){
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

}
