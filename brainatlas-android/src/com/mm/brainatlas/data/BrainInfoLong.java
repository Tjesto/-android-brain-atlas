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
	public String getContent(int paragraph) {
		return paragraphs.get(paragraph, S_NOT_FOUND);
	}
	
	@Override
	public int getImage(int imageNum) {
		return images.get(imageNum, I_NOT_FOUND);
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(name);
		builder.append('\n');
		builder.append("Paragraphs number: ");
		builder.append(paragraphs.size()).append(";images number").append(images.size());
		return builder.toString();
	}
}
