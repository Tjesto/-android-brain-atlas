package com.mm.brainatlas;

import java.util.List;

import com.mm.brainatlas.utils.MenuAction;

import android.content.Context;
import android.widget.ArrayAdapter;

public class MenuAdapter extends ArrayAdapter<String> {

	private final MenuAction action;

	public MenuAdapter(Context context, int resource, List<String> objects, MenuAction action) {
		super(context, resource, objects);
		this.action = action;
	}
	
	public MenuAction getItemsAction(int position) {
		return MenuAction.values()[position];
	}
	

}
