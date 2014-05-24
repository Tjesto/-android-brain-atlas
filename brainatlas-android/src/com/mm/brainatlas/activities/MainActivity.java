package com.mm.brainatlas.activities;

import com.mm.brainatlas.activities.impl.AbstractBrainActivityWithMenus;
import com.mm.brainatlas.activities.impl.Changable;
import com.mm.brainatlas.data.DataFactory;
import com.mm.brainatlas.data.ShortBrainPartInfo;
import com.mm.brainatlas.listeners.ChangableOnTouchListener;
import com.mm.brainatlas.listeners.OnBrainTouchListener;
import com.mm.brainatlas.services.BrainService;
import com.mm.brainatlas.utils.ApplicationLog;
import com.mm.brainatlas.utils.StringToLogParser;
import com.mm.brainatlas.utils.Utils;
import com.mm.brainatlas.views.BrainView;
import com.mm.brainatlas_android.R;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AbstractBrainActivityWithMenus implements Changable{
	
	enum ViewMode {
		TOP, INNER;
	}
	
	public static final String TAG = "com.mm.brainatlas.activities.MainActivity";
	
	private TextView topView;
	
	private TextView innerView;
	
	private ViewMode currentViewMode = ViewMode.TOP; 
	
	private BrainView brainView;
	
	private ChangableOnTouchListener listener;
	
	private static Context context;
	
	private static Intent intentToStart;
	
	private String currentlyFocusedPart;

	private static MainActivity activity;
	
	private static ShortBrainPartInfo shortBrainPartInfo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = new Intent(this, BrainService.class);
		intent.putExtra(BrainService.NOTIFY_ACTIVITY_CHANGE_KEY, TAG);
		intent.setAction(BrainService.ACTION_NOTIFY_ACTIVITY_CHANGE);
		startService(intent);
		setContentView(R.layout.main);
		topView = (TextView) findViewById(R.id.view_mode_top);
		innerView = (TextView) findViewById(R.id.view_mode_inner);
		//currently, until the innerView screen implemented
		topView.setVisibility(View.INVISIBLE);
		innerView.setVisibility(View.INVISIBLE);
		//end of current temporary block
		brainView = (BrainView) findViewById(R.id.part_view);
		brainView.setActivity(this);
		listener = new ChangableOnTouchListener(this);
		context = this;
		activity = this;
		/*((ImageView) findViewById(R.id.menu_button)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				openOptionsMenu();
			}
		});*/
		addMenuButton(this);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		//return listener.onTouch(getCurrentFocus(), event);
		return super.onTouchEvent(event);
	}
	@Override
	public void onBackPressed() {
		Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		finish();
	}
	
	@Override
	public void changeView() {
		if (currentViewMode == ViewMode.TOP) {
			topView.setTextColor(getResources().getColor(R.color.gray));
			innerView.setTextColor(getResources().getColor(R.color.white));			
			prepareInnerView();			
		} else {
			topView.setTextColor(getResources().getColor(R.color.white));
			innerView.setTextColor(getResources().getColor(R.color.gray));
			prepareTopView();
		}
	}
	private void prepareTopView() {
		//brainView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.full_brain_top));
		currentViewMode = ViewMode.TOP;
		
	}
	private void prepareInnerView() {
		//brainView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.tmp_shot3));
		currentViewMode = ViewMode.INNER;
		
	}	
	
	public void startInfoActivity(Intent intent, String part) {	
		shortBrainPartInfo = new ShortBrainPartInfo(context, Utils.normalizeName(part));
		intentToStart = intent;
		showDialog();
	}		
	
	private static AlertDialog createInfoDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(shortBrainPartInfo.getName()).setIcon(shortBrainPartInfo.getImage(0));
        StringBuilder sBuilder = new StringBuilder(shortBrainPartInfo.getContent());

        builder.setMessage(sBuilder.toString());
        builder.setPositiveButton(context.getString(R.string.brain_part_dialog_positive), new DialogInterface.OnClickListener() {
            
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onPositiveClick();                
            }
			
        });
        builder.setNegativeButton(context.getString(R.string.brain_part_dialog_negative), new DialogInterface.OnClickListener() {
            
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onNegativeClick();                
            }
        });
        AlertDialog dialog = builder.create();

        return dialog;
    }
    
    

    protected static void onNegativeClick() {		
		
	}

	protected static void onPositiveClick() {
		context.startActivity(intentToStart);
		activity.finish();
	}

	private void showDialog() {
        DialogFragment newFragment = InfoDialogFragment.newInstance();
        newFragment.setCancelable(false);
        newFragment.show(getFragmentManager(), TAG);

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
