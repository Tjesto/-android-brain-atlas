package com.mm.brainatlas.data;

import com.mm.brainatlas.utils.Utils;

import android.content.Context;

public class ShortBrainPartInfo implements BrainInfo {

	private String content;	
	
	public ShortBrainPartInfo(Context context, String name) {
		//content = context.getString(Utils.getContentStringFromName(name, 0));
	}
	
	public String getContent() {
		return content;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getContent(int paragraph) {
		//empty, implement this if you need
		return null;
	}

	@Override
	public int getImage(int imageNum) {
		//empty, implement this if you need
		return 0;
	}

	@Override
	public void generateAllContent(String name) {
		// TODO Auto-generated method stub
		
	}

}
