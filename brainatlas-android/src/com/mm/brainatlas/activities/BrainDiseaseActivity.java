package com.mm.brainatlas.activities;

import com.mm.brainatlas.data.BrainDiseaseInfo;
import com.mm.brainatlas.data.DataFactory;
import com.mm.brainatlas.listeners.OnPictureClickListener;
import com.mm.brainatlas.services.BrainService;
import com.mm.brainatlas.utils.ApplicationLog;
import com.mm.brainatlas.utils.Utils;
import com.mm.brainatlas_android.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class BrainDiseaseActivity extends BrainInfoActivity {

	public static final String TAG = "com.mm.brainatlas.activities.BrainDiseaseActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.brain_disease);
		getLayoutParts("bd_");
		Intent intent = new Intent(this, BrainService.class);
		intent.putExtra(BrainService.NOTIFY_ACTIVITY_CHANGE_KEY, TAG);
		intent.setAction(BrainService.ACTION_NOTIFY_ACTIVITY_CHANGE);
		String infoSubject = getIntent().getStringExtra(INFO_TYPE);
		if (Utils.isEmptyOrNull(infoSubject)) {
			infoSubject = UNKNOWN_INFO;
			ApplicationLog.informInternalError(this);
			finish();
		}
		brainInfo = DataFactory.getInfoForClass(this, TAG, infoSubject);
		intent.putExtra(BrainInfoActivity.INFO_TYPE, infoSubject);
		startService(intent);
		matchLayoutPartsWithData();
		setListeners();
	}
	
	private void setListeners() {
		for (final ImageView view : imageViews) {
			if (brainInfo instanceof BrainDiseaseInfo) {
				final int photo = imageViews.indexOf(view) + 1;
				if (((BrainDiseaseInfo) brainInfo).hasLink(photo)) {
					view.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							Intent intent = new Intent(
									BrainDiseaseActivity.this,
									DiseasePartComparerActivity.class);
							intent.putExtra(
									DiseasePartComparerActivity.DISEASE_RES_ID,
									brainInfo.getImage(photo));
							intent.putExtra(
									DiseasePartComparerActivity.PART_RES_ID,
									((BrainDiseaseInfo) brainInfo)
											.getLink(photo));
							startActivity(intent);
							String label = brainInfo.getLabel(photo);
							if (!label.equals("")) {
								Toast.makeText(BrainDiseaseActivity.this,
										label, Toast.LENGTH_LONG).show();
							}
						}
					});
				}
			} else {
				view.setOnClickListener(new OnPictureClickListener(this, brainInfo.getImage(imageViews.indexOf(view)+1), brainInfo.getLabel(imageViews.indexOf(view)+1)));
			}
		}
	}

	protected int getPartImageForDiseaseImage(int image) {
		return R.drawable.full_brain_top;
	}
	
	@Override
	protected void showHelpMeActivity() {
		Intent intent = new Intent(this, AppGuideActivity.class);
		intent.putExtra(AppGuideActivity.WHERE_AM_I, TAG);
		startActivity(intent);
	}
}
