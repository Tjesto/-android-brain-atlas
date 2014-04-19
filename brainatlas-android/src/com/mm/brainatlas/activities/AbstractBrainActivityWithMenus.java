package com.mm.brainatlas.activities;

import com.mm.brainatlas.services.BrainService;
import com.mm.brainatlas_android.R;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public abstract class AbstractBrainActivityWithMenus extends Activity {

	private static final Object EXIT_ACTIVITY = null;

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
		case R.id.action_show_parts:
			Toast.makeText(this, "Opcja w trakcie implementacji", Toast.LENGTH_LONG).show();
			return true;
		case R.id.action_show_sources:
			showSourcesActivity();
			return true;
		case R.id.action_exit:
			exitAtlas();
			return true;
		default:
			return super.onContextItemSelected(item);
		}
	}
	
	protected void showDiseasesListActivity() {
		startActivity(new Intent(this, ListViewActivity.class));	
	}

	@Override
	public void onBackPressed() {
		startActivity(new Intent(this, MainActivity.class));
		finish();
	}

	protected void showSourcesActivity() {
		startActivity(new Intent(this, SourcesActivity.class));		
	}

	protected void exitAtlas() {
		Intent exit = new Intent(this, BrainService.class);
		exit.setAction(BrainService.ACTION_EXIT);
		startService(exit);
		finish();
	}
}
