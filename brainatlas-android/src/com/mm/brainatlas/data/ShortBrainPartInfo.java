package com.mm.brainatlas.data;

import com.mm.brainatlas.data.impl.BrainInfo;
import com.mm.brainatlas_android.R;

import android.content.Context;

public class ShortBrainPartInfo implements BrainInfo {

	private String title;
	
	private String content;
	
	private int image;
	
	public ShortBrainPartInfo(Context context, String name) {
		Class<?> rstring = R.string.class;	
		Class<?> rdrawable = R.drawable.class;	
		title = "Nie znaleziono";
		content = "";
		image = 0;
		try {
			title = context.getText((rstring.getField("bp_" + name + "_name").getInt(rstring))).toString();
			content = context.getText((rstring.getField("bp_" + name + "__1").getInt(rstring))).toString();
			image = rdrawable.getField("bp_" + name + "_mini").getInt(rdrawable);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
	}
	
	public String getContent() {
		return getContent(0);
	}

	@Override
	public String getName() {
		return title;
	}

	@Override
	public String getContent(int paragraph) {
		return content;
	}

	@Override
	public int getImage(int imageNum) {
		return image;
	}

	@Override
	public void generateAllContent(String name) {
		//not necessary, implement if you need
		
	}

}
