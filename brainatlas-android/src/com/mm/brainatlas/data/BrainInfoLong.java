package com.mm.brainatlas.data;

import com.mm.brainatlas.utils.Utils;

import android.content.Context;
import android.util.SparseArray;
import android.util.SparseIntArray;

public abstract class BrainInfoLong implements BrainInfo{

	protected final String name;
	protected final SparseArray<String> paragraphs = new SparseArray<String>();
	protected final SparseIntArray images = new SparseIntArray();
	protected final Context context;
	
	public BrainInfoLong(Context context, String name) {
		this.name = context.getText(Utils.getStringFromName(name)).toString();
		this.context = context;
	}
	
	//TODO getters
	@Override
	public String generateContent(String name, int paragraph) {
		String contentPart = context.getText(Utils.getContentStringFromName(name, paragraph)).toString();
		paragraphs.put(paragraph, contentPart);
		return null;
	}
	
}
