package com.mm.brainatlas.activities;

import com.mm.brainatlas.activities.impl.Changable;
import com.mm.brainatlas.listeners.ChangableOnTouchListener;
import com.mm.brainatlas.listeners.OnLongItemClickListnere;
import com.mm.brainatlas.utils.Utils;
import com.mm.brainatlas_android.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;

public class DetailsViewComparerActivity extends Activity implements Changable{

	private int diseaseId;
	private int partId;
	private boolean currentlyIsDisease = true;
	
	private ChangableOnTouchListener listener;
	
	private OnLongItemClickListnere longClickListener;
	
	private ImageView view;
	@Override
	protected void onCreate(Bundle savedInstanceState) {		 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bp_image);
		diseaseId = getIntent().getIntExtra(DiseasePartComparerActivity.DISEASE_RES_ID, R.drawable.full_brain_top);
		partId = getIntent().getIntExtra(DiseasePartComparerActivity.PART_RES_ID, R.drawable.full_brain_top);
		view = (ImageView) findViewById(R.id.bp_image_big);
		view.setImageResource(diseaseId);		
		longClickListener = new OnLongItemClickListnere(this, createStartIntent());
		listener = new ChangableOnTouchListener(this);
		listener.addClickListnener(longClickListener);		
	}
	
	private Intent createStartIntent() {
		Intent startIntent = new Intent(this, BrainPartInfoActivity.class);
		startIntent.putExtra(BrainInfoActivity.INFO_TYPE, getNameForResId(partId));
		return startIntent;
	}

	private String getNameForResId(int resId) {
		String chosenName = "Mó¿d¿ek (cerebellum)";
		return Utils.normalizeName(chosenName);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return listener.onTouch(view, event);
	}
	@Override
	public void changeView() {
		 if (currentlyIsDisease) {
			 view.setImageResource(partId);
			 currentlyIsDisease = false;	
			 Toast.makeText(this,
						R.string.normal_brain, Toast.LENGTH_SHORT).show();
		 } else {
			 view.setImageResource(diseaseId);
			 currentlyIsDisease = true;
			 Toast.makeText(this,
						R.string.sick_brain, Toast.LENGTH_SHORT).show();
		 }
		longClickListener.notifyViewChange(currentlyIsDisease);
	}
}
