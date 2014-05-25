package com.mm.brainatlas.data.impl;

import com.mm.brainatlas.utils.ApplicationLog;
import com.mm.brainatlas.utils.StringToLogParser;
import com.mm.brainatlas.utils.Utils;

import android.content.Context;
import android.util.SparseArray;
import android.util.SparseIntArray;

public abstract class BrainInfoLong implements BrainInfo{

	private static final String EMPTY_LABEL = "";
	protected final String name;
	protected final SparseArray<String> paragraphs = new SparseArray<String>();
	protected final SparseIntArray images = new SparseIntArray();
	protected final SparseArray<String> imageLabels = new SparseArray<String>();
	protected final Context context;
	
	public BrainInfoLong(Context context, String name) {
		ApplicationLog.error("STR",StringToLogParser.parseForErrorLog("BRAININFO", "", name, Utils.normalizeName(name)));
		this.name = context.getText(Utils.getStringFromName(Utils.normalizeName(name))).toString();
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
	
	public String getLabel(int imageNum) {
		return imageLabels.get(imageNum, EMPTY_LABEL);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(name);
		builder.append('\n');
		builder.append("Paragraphs number: ");
		builder.append(paragraphs.size()).append(";images number ").append(images.size());
		builder.append(";has labels ").append(imageLabels.size());
		return builder.toString();
	}
}
