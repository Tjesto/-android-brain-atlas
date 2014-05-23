package com.mm.brainatlas.data;

import com.mm.brainatlas.data.impl.SourceInfo;

public class WebsiteSourceInfo extends SourceInfo {

	private final String name;
	
	private final String hyperlink;
	
	public WebsiteSourceInfo(String name, String hyperlink) {
		super();
		this.name = name;
		this.hyperlink = hyperlink;
	}
	
	@Override
	public String getTitleString() {
		return name;
	}

	@Override
	public String getLink() {
		return hyperlink;
	}

}
