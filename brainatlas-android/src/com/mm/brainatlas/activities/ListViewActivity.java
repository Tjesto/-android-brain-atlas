package com.mm.brainatlas.activities;

import java.util.List;

import com.mm.brainatlas.activities.impl.AbstractBrainActivityWithMenus;
import com.mm.brainatlas.data.DataFactory;
import com.mm.brainatlas.listeners.OnListItemClickListener;
import com.mm.brainatlas_android.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListViewActivity extends AbstractBrainActivityWithMenus {

	private ListView listView;
	private String type;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_d_or_p);
		listView = (ListView) findViewById(R.id.list_d_or_p);
		type = getIntent().getStringExtra(DataFactory.GET_ITEMS);
		((TextView) findViewById(R.id.l_title)).setText(DataFactory.getTitleForType(type));
		List<String> items = DataFactory.createItemsList(this, type);
		listView.setAdapter(new ArrayAdapter<String>(this, R.layout.single_list_item, items));
		listView.setOnItemClickListener(new OnListItemClickListener(this, type));
	}
	
	@Override
	protected void showDiseasesListActivity() {
		if (type.equals(DataFactory.PARTS)) {
			super.showBrainPartsActivity();
		}
	}
	
	@Override
	protected void showBrainPartsActivity() {
		if (type.equals(DataFactory.DISEASES)) {
			super.showBrainPartsActivity();
		}
	}

	public void startBrainInfoActivity(Intent intent) {
		startActivity(intent);
		finish();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			return true;
		}
		return super.onKeyUp(keyCode, event);
	}
}
