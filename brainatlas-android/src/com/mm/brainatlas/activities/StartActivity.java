package com.mm.brainatlas.activities;

import com.mm.brainatlas.utils.Utils;
import com.mm.brainatlas_android.R;

import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

public class StartActivity extends Activity {
	
	public static final String TAG = "com.mm.brainatlas.activities.StartActivity";
	protected static Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		if (Utils.FOR_STUDIES) {
			((TextView) findViewById(R.id.studies_info_textview)).setVisibility(View.VISIBLE);
		} else {
			((TextView) findViewById(R.id.studies_info_textview)).setVisibility(View.VISIBLE);
			((TextView) findViewById(R.id.studies_info_textview)).setText(R.string.authors_text);
		}
		context = this;
		if (Utils.isTestVersion) {
			if (Build.VERSION.SDK_INT >= 11) {
				showTestInfoDialog();
			} else {
				showTestInfoDialogAndroid2();
			}
		}
		new Thread(new Runnable(){

			@Override
			public void run() {				
				try {
					Thread.sleep(5000);						
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				StartActivity.this.startActivity(new Intent(StartActivity.this, MainActivity.class));
				StartActivity.this.finish();
			}
			
		}).start();
		
	}
	
	protected void showTestInfoDialog() {
		showInfoDialog();		
	}
	
	protected void showTestInfoDialogAndroid2() {
		showAndroid2InfoDialog();
	}

	@Override
	public void onBackPressed() {
		if(Utils.DEBUG_FLAG) {
			Log.d(TAG, "BackKey pressed");
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME) {
			if(Utils.DEBUG_FLAG) {
				Log.d(TAG, "BackKey pressed");
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	private static AlertDialog createInfoDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(android.R.drawable.ic_dialog_info);        
        builder.setMessage(Utils.ALPHA ? R.string.test_alpha : R.string.test_beta);
        builder.setNeutralButton(context.getString(R.string.text_ok), new DialogInterface.OnClickListener() {
            
            @Override
            public void onClick(DialogInterface dialog, int which) {
            	dialog.dismiss();
            }
			
        });
        AlertDialog dialog = builder.create();        
        return dialog;
    }
	
	private void showInfoDialog() {
        DialogFragment newFragment = InfoDialogFragment.newInstance();
        newFragment.setCancelable(false);
        newFragment.show(getFragmentManager(), TAG);

    }
	
	private void showAndroid2InfoDialog() {
		AlertDialog dialog = createInfoDialog();
		dialog.setCancelable(false);
		dialog.show();
		
	}

    public static class InfoDialogFragment extends DialogFragment {

        static InfoDialogFragment newInstance() {
            InfoDialogFragment instance = new InfoDialogFragment();
            return instance;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog dialog = createInfoDialog();
            return dialog;

        }

    }
}
