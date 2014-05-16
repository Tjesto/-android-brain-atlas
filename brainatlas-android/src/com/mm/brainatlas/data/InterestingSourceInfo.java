package com.mm.brainatlas.data;

import android.content.Context;
import com.mm.brainatlas_android.R;

public class InterestingSourceInfo extends WebsiteSourceInfo {

	private final int background;
	private final int color;
	private final Context context;
	
	public InterestingSourceInfo(Context context, String name, String hyperlink) {
		super(name, hyperlink);
		background = R.color.interesting_background;
		color = R.color.interesting_font_color;
		this.context = context;
	}
	
	public int getBackground() {
		return context.getResources().getColor(background);
	}
	
	public int getFontColor() {
		return context.getResources().getColor(color);
	}
}
