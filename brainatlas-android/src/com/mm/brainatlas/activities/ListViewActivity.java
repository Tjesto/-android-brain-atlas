package com.mm.brainatlas.activities;

import java.util.ArrayList;
import java.util.List;

import com.mm.brainatlas.data.DataFactory;
import com.mm.brainatlas_android.R;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListViewActivity extends AbstractBrainActivityWithMenus {

	private ListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_d_or_p);
		listView = (ListView) findViewById(R.id.list_d_or_p);
		String itemsName = getIntent().getStringExtra(DataFactory.GET_ITEMS);
		List<String> items = new ArrayList<String>();		
		items.add("Test 1");
		items.add("Test2");
		//List<String> items = Utils.createItemsList(itemsName);
		listView.setAdapter(new ArrayAdapter<String>(this, R.layout.single_list_item, items));
		//listView.setOnItemClickListener(DataFactory.getOnItemClickListener(this, itemsName));
	}
	
	@Override
	protected void showDiseasesListActivity() {
		//doNothing
	}
}
