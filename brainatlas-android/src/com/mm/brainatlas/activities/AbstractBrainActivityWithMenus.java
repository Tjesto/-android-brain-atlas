package com.mm.brainatlas.activities;

import com.mm.brainatlas.data.DataFactory;
import com.mm.brainatlas.services.BrainService;
import com.mm.brainatlas_android.R;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public abstract class AbstractBrainActivityWithMenus extends Activity {

	private static final String EXIT_ACTIVITY = null;	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case R.id.action_show_diseases:
			showDiseasesListActivity();
			return true;
		case R.id.action_show_parts:
			showBrainPartsActivity();
			return true;
		case R.id.action_show_sources:
			showSourcesActivity();
			return true;
		case R.id.action_help_me:
			showHelpMeActivity();
			return true;
		case R.id.action_exit:
			exitAtlas();
			return true;
		default:
			return super.onContextItemSelected(item);
		}
	}
	
	protected void showHelpMeActivity() {
		Intent intent = new Intent(this, AppGuideActivity.class);
		startActivity(intent);
		Toast.makeText(this, R.string.not_implemented_yet, Toast.LENGTH_SHORT).show();
	}

	protected void showBrainPartsActivity() {
		Intent intent = new Intent(this, ListViewActivity.class);
		intent.putExtra(DataFactory.GET_ITEMS, DataFactory.PARTS);
		startActivity(intent);	
		finish();
	}

	protected void showDiseasesListActivity() {
		Intent intent = new Intent(this, ListViewActivity.class);
		intent.putExtra(DataFactory.GET_ITEMS, DataFactory.DISEASES);
		startActivity(intent);	
		finish();
	}

	@Override
	public void onBackPressed() {
		startActivity(new Intent(this, MainActivity.class));
		finish();
	}

	protected void showSourcesActivity() {
		startActivity(new Intent(this, SourcesActivity.class));
		finish();
	}

	protected void exitAtlas() {
		Intent exit = new Intent(this, BrainService.class);
		exit.setAction(BrainService.ACTION_EXIT);
		startService(exit);
		Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
        finish();
	}
	
}
