package com.mm.brainatlas.data;

import com.mm.brainatlas.utils.Utils;

import android.content.Context;

public class ShortBrainPartInfo extends BrainInfo {

	private String content;	
	
	public ShortBrainPartInfo(Context context, InfoName name) {
		super(context, name);
		content = context.getString(Utils.getStringContentForName(name, Type.SHORT));
	}
	
	public String getContent() {
		return content;
	}

}
