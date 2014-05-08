package com.mm.brainatlas;

import java.util.List;

import com.mm.brainatlas.data.BookSourceInfo;
import com.mm.brainatlas.data.SourceInfo;
import com.mm.brainatlas_android.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SourcesAdapter extends ArrayAdapter<SourceInfo> {

	private final LayoutInflater inflater;
	
	public SourcesAdapter(Context context, int resource,
			List<SourceInfo> objects) {
		super(context, resource, objects);
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View dropDownView = inflater.inflate(R.layout.single_source_item, null);
		
		SourceInfo item = getItem(position);
		if (item instanceof BookSourceInfo) {
			dropDownView.findViewById(R.id.s_item_link).setVisibility(View.GONE);			
		} else {
			dropDownView.findViewById(R.id.s_item_link).setVisibility(View.VISIBLE);
			((TextView) dropDownView.findViewById(R.id.s_item_link)).setText(item.getLink());
		}
		((TextView) dropDownView.findViewById(R.id.s_item_id)).setText(Integer.toString(position + 1));
		((TextView) dropDownView.findViewById(R.id.s_item_book)).setText(item.getTitleString());		
		
		return dropDownView; 
	}

}
