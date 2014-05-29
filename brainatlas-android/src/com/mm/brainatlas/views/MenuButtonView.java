package com.mm.brainatlas.views;

import com.mm.brainatlas.activities.impl.AbstractBrainActivityWithMenus;
import com.mm.brainatlas.utils.ApplicationLog;
import com.mm.brainatlas_android.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
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
		if (Build.VERSION.SDK_INT >= 11) {
			super.setLeft(x);
			super.setTop(y);
			super.setRight(x + icon.getWidth());
			super.setBottom(y + icon.getHeight());
		} else {
			super.setPadding(0, 0, icon.getWidth(), icon.getHeight());
		}
		super.setImageBitmap(icon);
	}
	
	public int getImageWidth() {
		return icon.getWidth();
	}
	public int getImageHeight() {
		return icon.getHeight();
	}

}
