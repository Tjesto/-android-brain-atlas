package com.mm.brainatlas.listeners;

import com.mm.brainatlas.SourcesAdapter;
import com.mm.brainatlas.activities.SourcesActivity;
import com.mm.brainatlas.data.LectureSourceInfo;
import com.mm.brainatlas.data.SourceInfo;
import com.mm.brainatlas.data.WebsiteSourceInfo;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class OnSourceItemClickListener implements OnItemClickListener {

	private final SourcesActivity sourcesActivity;
	private final SourcesAdapter sourcesAdapter;
	private final GoToWebsiteEventHandler handler;

	public OnSourceItemClickListener(SourcesActivity sourcesActivity,
			SourcesAdapter sourcesAdapter) {
		this.sourcesActivity = sourcesActivity;
		this.sourcesAdapter = sourcesAdapter;
		handler = new GoToWebsiteEventHandler(sourcesActivity);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		SourceInfo item = sourcesAdapter.getItem(position);
		if (item instanceof WebsiteSourceInfo || item instanceof LectureSourceInfo) {
			handler.handle(item.getLink());
		}
		
	}

}
