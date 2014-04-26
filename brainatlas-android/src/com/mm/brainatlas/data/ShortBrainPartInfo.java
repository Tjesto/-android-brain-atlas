package com.mm.brainatlas.data;

import com.mm.brainatlas.utils.Utils;

import android.content.Context;

public class ShortBrainPartInfo implements BrainInfo {

	private String content;	
	
	public ShortBrainPartInfo(Context context, String name) {
		content = context.getString(Utils.getContentStringFromName(name, 0));
	}
	
	public String getContent() {
		return content;
	}

	@Override
	public String genrateName(int infoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String generateContent(String name, int paragraph) {
		//empty, implement this if you need
		return null;
	}

	@Override
	public int generateImage(String name, int imageNum) {
		//empty, implement this if you need
		return 0;
	}

}
