package com.mm.brainatlas.data;

import com.mm.brainatlas.utils.Utils;

import android.content.Context;
import android.util.SparseArray;
import android.util.SparseIntArray;

public abstract class BrainInfo {
	
	public enum Type {
		SHORT, NORMAL;
	}

	protected final String name;
	protected final SparseArray<String> paragraphs = new SparseArray<String>();
	protected final SparseIntArray images = new SparseIntArray();
	protected final Context context;
	
	public BrainInfo(Context context, InfoName name) {
		this.name = context.getText(Utils.getStringFromName(name)).toString();
		this.context = context;
	}
	
	//TODO getters
	
	
}
