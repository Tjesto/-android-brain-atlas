package com.mm.brainatlas.listeners;

import com.mm.brainatlas.MenuAdapter;
import com.mm.brainatlas.activities.impl.AbstractBrainActivityWithMenus;
import com.mm.brainatlas.utils.MenuAction;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class OnMenuItemClickListener implements OnItemClickListener {
	
	private final AbstractBrainActivityWithMenus activity;
	private final MenuAdapter adapter;
	
	public OnMenuItemClickListener(AbstractBrainActivityWithMenus activity, MenuAdapter adapter) {
		this.activity = activity;
		this.adapter = adapter;
	}
	

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		MenuAction action = adapter.getItemsAction(arg2);
		activity.executeMenuAction(action);
		
	}



}
