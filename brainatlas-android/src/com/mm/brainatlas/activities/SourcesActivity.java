package com.mm.brainatlas.activities;

import java.util.ArrayList;
import java.util.Map;

import com.mm.brainatlas.SourceInfoFactory;
import com.mm.brainatlas.SourcesAdapter;
import com.mm.brainatlas.activities.impl.AbstractBrainActivityWithMenus;
import com.mm.brainatlas.data.BookSourceInfo;
import com.mm.brainatlas.data.InterestingSourceInfo;
import com.mm.brainatlas.data.LectureSourceInfo;
import com.mm.brainatlas.data.WebsiteSourceInfo;
import com.mm.brainatlas.data.impl.SourceInfo;
import com.mm.brainatlas.listeners.OnSourceItemClickListener;
import com.mm.brainatlas.services.BrainService;
import com.mm.brainatlas_android.R;
import com.mm.brainatlas.utils.Pair;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class SourcesActivity extends AbstractBrainActivityWithMenus {
	
	public static final String TAG = "com.mm.brainatlas.activities.SourcesActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = new Intent(this, BrainService.class);
		intent.putExtra(BrainService.NOTIFY_ACTIVITY_CHANGE_KEY, TAG);
		intent.setAction(BrainService.ACTION_NOTIFY_ACTIVITY_CHANGE);
		startService(intent);
		setContentView(R.layout.sources);
		createSourceList();
		addMenuButton(this);
	}
	
	@Override
	protected void showSourcesActivity() {
		Log.i(TAG, "Activity actually running");
	}	
	
	private void createSourceList() {
		Map<String, Integer> stringsLables = SourceInfo.getFromResources();
		ArrayList<SourceInfo> sourceList = new ArrayList<SourceInfo>();
		ArrayList<InterestingSourceInfo> interestingList = new ArrayList<InterestingSourceInfo>();
		int i = 0;		
		sourceList.add(new BookSourceInfo(getText(R.string.source_ak).toString()));
		sourceList.add(new BookSourceInfo(getText(R.string.source_biologia).toString()));
		sourceList.add(new BookSourceInfo(getText(R.string.source_hw).toString()));
		sourceList.add(new BookSourceInfo(getText(R.string.source_tablicebiologiczne).toString()));
		sourceList.add(new LectureSourceInfo(getText(R.string.source_wd_name)
				.toString(), getText(R.string.source_wd_link).toString()));
		sourceList.add(new WebsiteSourceInfo(getText(
				R.string.source_biomed_name).toString(), getText(
				R.string.source_biomed_link).toString()));
		sourceList.add(new WebsiteSourceInfo(getText(
				R.string.source_padaczka_name).toString(), getText(
				R.string.source_padaczka_link).toString()));
		sourceList.add(new WebsiteSourceInfo(getText(
				R.string.source_radiopaedia_name).toString(), getText(
				R.string.source_radiopaedia_link).toString()));
		sourceList.add(new WebsiteSourceInfo(getText(
				R.string.source_neurology_name).toString(), getText(
				R.string.source_neurology_link).toString()));
		sourceList.add(new WebsiteSourceInfo(getText(
				R.string.source_alzheimerassociation_name).toString(), getText(
				R.string.source_alzheimerassociation_link).toString()));
		sourceList.add(new WebsiteSourceInfo(
				getText(R.string.source_neuroforma_name).toString(),
				getText(R.string.source_neuroforma_link).toString()));
		
		interestingList.add(new InterestingSourceInfo(this, getText(
				R.string.source_nowotwormozgu_name).toString(), getText(
				R.string.source_nowotwormozgu_link).toString()));
		interestingList.add(new InterestingSourceInfo(this, getText(
				R.string.source_tetniak1_name).toString(), getText(
				R.string.source_tetniak1_link).toString()));
		interestingList.add(new InterestingSourceInfo(this, getText(
				R.string.source_tetniak2_name).toString(), getText(
				R.string.source_tetniak2_link).toString()));
		interestingList.add(new InterestingSourceInfo(this, getText(
				R.string.source_tetniak3_name).toString(), getText(
				R.string.source_tetniak3_link).toString()));
		
		sourceList.addAll(interestingList);
		SourcesAdapter sourcesAdapter = new SourcesAdapter(this, R.layout.single_source_item, sourceList);
		
		ListView listView = (ListView) findViewById(R.id.sources_list);
		
		listView.setAdapter(sourcesAdapter);
		listView.setOnItemClickListener(new OnSourceItemClickListener(this, sourcesAdapter));
	}
}
