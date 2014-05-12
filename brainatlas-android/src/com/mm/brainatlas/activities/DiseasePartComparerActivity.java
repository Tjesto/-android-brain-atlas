package com.mm.brainatlas.activities;

import com.mm.brainatlas_android.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;

public class DiseasePartComparerActivity extends Activity {

	public static final String DISEASE_RES_ID = "dri";
	public static final String PART_RES_ID = "pri";
	private int diseaseId;
	private int partId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bd_comparer);
		diseaseId = getIntent().getIntExtra(DiseasePartComparerActivity.DISEASE_RES_ID, R.drawable.full_brain_top);
		partId = getIntent().getIntExtra(DiseasePartComparerActivity.PART_RES_ID, R.drawable.full_brain_top);
		((ImageView) findViewById(R.id.bd_disease_image)).setImageResource(diseaseId);
		((ImageView) findViewById(R.id.bd_part_image)).setImageResource(partId);
		findViewById(R.id.bd_disease_image).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {				
				showDetailsActivity();
			}
		});
		findViewById(R.id.bd_disease_image).setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				finish();
				return true;
			}
		});		
		findViewById(R.id.bd_part_image).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {				
				showDetailsActivity();
			}
		});
		findViewById(R.id.bd_part_image).setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				finish();
				return true;
			}
		});		
	}

	protected void showDetailsActivity() {
		Intent intent = new Intent(this, DetailsViewComparerActivity.class);
		intent.putExtra(DISEASE_RES_ID, diseaseId);
		intent.putExtra(PART_RES_ID, partId);
		startActivity(intent);
		
	}	
}
