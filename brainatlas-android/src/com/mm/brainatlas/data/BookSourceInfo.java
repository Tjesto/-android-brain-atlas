package com.mm.brainatlas.data;

import com.mm.brainatlas.data.impl.SourceInfo;

public class BookSourceInfo extends SourceInfo {

	private String bookRecord;
	
	public BookSourceInfo(String name) {
		super();
		bookRecord = name;
	}
	
	@Override
	public String getTitleString() { 
		return bookRecord;
	}

	@Override
	public String getLink() {
		return null;
	}

}
