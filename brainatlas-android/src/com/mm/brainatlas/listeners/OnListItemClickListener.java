package com.mm.brainatlas.listeners;

import com.mm.brainatlas.activities.BrainDiseaseActivity;
import com.mm.brainatlas.activities.BrainPartInfoActivity;
import com.mm.brainatlas.activities.ListViewActivity;
import com.mm.brainatlas.data.DataFactory;
import com.mm.brainatlas.utils.Utils;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class OnListItemClickListener implements OnItemClickListener {

	private final ListViewActivity activity;
	
	private final String type;
	
	public OnListItemClickListener(ListViewActivity activity, String type) {
		this.activity = activity;
		this.type = type;
	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View arg1, int arg2, long arg3) {
		String chosenName = (String) adapter.getItemAtPosition(arg2);
		String normalizedName = Utils.normalizeName(chosenName);
		Intent intent = new Intent(activity,
				(type.equals(DataFactory.PARTS) ? BrainPartInfoActivity.class
						: BrainDiseaseActivity.class));
		intent.putExtra(DataFactory.ITEM_NAME, normalizedName);		
		activity.startBrainInfoActivity(intent);
		
	}

}
