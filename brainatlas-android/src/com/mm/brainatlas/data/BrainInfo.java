package com.mm.brainatlas.data;

public interface BrainInfo {

	String genrateName(int infoId);
	String generateContent(String name, int paragraph);
	int generateImage(String name, int imageNum);
}
