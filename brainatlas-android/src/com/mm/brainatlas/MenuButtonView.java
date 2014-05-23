package com.mm.brainatlas;

import com.mm.brainatlas.activities.impl.AbstractBrainActivityWithMenus;
import com.mm.brainatlas.utils.ApplicationLog;
import com.mm.brainatlas_android.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

public class MenuButtonView extends ImageView {
	
	private final AbstractBrainActivityWithMenus activity;
	private final Bitmap icon;
	
	public MenuButtonView(Context context,
			AbstractBrainActivityWithMenus activity, int xPosition,
			int yPosition) {
		super(context);
		icon = BitmapFactory.decodeResource(getResources(), R.drawable.menu);
		this.activity = activity;
		int x = xPosition - icon.getWidth();
		int y = yPosition;
		ApplicationLog.debugWithFilter("MenuButtonView", "Position: " + x + ";" + y);
		super.setLeft(x);
		super.setTop(y);
		super.setRight(x + icon.getWidth());
		super.setBottom(y + icon.getHeight());
		super.setImageBitmap(icon);
	}
		
	
	

	
	

}
