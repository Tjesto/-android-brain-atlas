package com.mm.brainatlas.data.impl;

public interface BrainInfo {
	
	public final static String S_NOT_FOUND = "string_not_found";
	public final static int I_NOT_FOUND = -1;

	String getName();
	String getContent(int paragraph);
	int getImage(int imageNum);
	void generateAllContent(String name);
}
