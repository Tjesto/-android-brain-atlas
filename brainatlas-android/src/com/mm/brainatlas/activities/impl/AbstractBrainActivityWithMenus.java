package com.mm.brainatlas.activities.impl;

import java.util.ArrayList;
import java.util.List;

import com.mm.brainatlas.MenuAdapter;
import com.mm.brainatlas.activities.AppGuideActivity;
import com.mm.brainatlas.activities.ContactActivity;
import com.mm.brainatlas.activities.ListViewActivity;
import com.mm.brainatlas.activities.MainActivity;
import com.mm.brainatlas.activities.SourcesActivity;
import com.mm.brainatlas.data.DataFactory;
import com.mm.brainatlas.listeners.OnMenuItemClickListener;
import com.mm.brainatlas.services.BrainService;
import com.mm.brainatlas.utils.MenuAction;
import com.mm.brainatlas.views.MenuButtonView;
import com.mm.brainatlas_android.R;

import android.content.Intent;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;

public abstract class AbstractBrainActivityWithMenus extends FragmentActivity {

	protected MenuButtonView menuButton;

	protected boolean menuOpened;
	
	protected Animation show;
	protected Animation hide;
	protected ListView lv;
	
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
	
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			if (menuOpened) {
				closeMenu();
			} else {
				openMenu();
			}
			return true;
		}
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (menuOpened) {
				closeMenu();
				return true;
			}
		}
		return super.onKeyUp(keyCode, event);
	}
	
	private void closeMenu() {
		lv.startAnimation(hide);
		lv.setVisibility(View.GONE);
		menuOpened = false;
		if (Build.VERSION.SDK_INT < 11) {
			menuButton.setVisibility(View.VISIBLE);
		}
	}

	private void openMenu() {
		lv.startAnimation(show);
		lv.setVisibility(View.VISIBLE);
		menuOpened = true;
		if (Build.VERSION.SDK_INT < 11) {
			menuButton.setVisibility(View.GONE);
		}
	}

	public boolean executeMenuAction(MenuAction action) {
		switch(action) {
		case SHOW_DISEASES:
			showDiseasesListActivity();
			return true;
		case SHOW_PARTS:
			showBrainPartsActivity();
			return true;
		case SHOW_SOURCES:
			showSourcesActivity();
			return true;
		case CONTACT_US:
			showContactActivity();
			return true;
		case EXIT:
			exitAtlas();
			return true;
		case BACK:
			closeMenu();
			return true;
		default:
			return false;
		}
	}
	
	private void showContactActivity() {
		Intent intent = new Intent(this, ContactActivity.class);
		startActivity(intent);
	}

	protected void showHelpMeActivity() {
		Intent intent = new Intent(this, AppGuideActivity.class);
		startActivity(intent);
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
	
	protected void addMenuButton(final AbstractBrainActivityWithMenus activity) {
		if (menuButton == null) {
			int x = calculateX();
			int y = calculateY();
			menuButton = new MenuButtonView(activity, activity, x, y);
		}
		lv = (ListView) findViewById(R.id.menu_drawer);
		lv.setPadding(menuButton.getWidth(), menuButton.getHeight(), 0, 0);
		MenuAdapter adapter = new MenuAdapter(activity, R.layout.single_list_menu_item, createMenuItems(), null);
		lv.setAdapter(adapter);
		lv.setVisibility(View.GONE);
		show = AnimationUtils.loadAnimation(this, R.anim.anim_show);
		hide = AnimationUtils.loadAnimation(this, R.anim.anim_hide);
		addContentView(menuButton, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		lv.setOnItemClickListener(new OnMenuItemClickListener(activity, adapter));
		menuButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (menuOpened) {
					closeMenu();
				} else {
					openMenu();
				}
			}
		});
		
	}

	private List<String> createMenuItems() {
		List<String> result = new ArrayList<String>();
		result.add(getText(R.string.action_show_diseases).toString());
		result.add(getText(R.string.action_show_parts).toString());
		result.add(getText(R.string.action_show_sources).toString());
		result.add(getText(R.string.action_show_contact).toString());
		//option removed until guideView will be improved		
		//result.add(getText(R.string.action_help_me).toString());
		// end of removement
		result.add(getText(R.string.action_exit).toString());
		if (Build.VERSION.SDK_INT < 11) {
			result.add(getText(R.string.action_back).toString());
		}
		return result;
	}

	private int calculateY() {
		return getResources().getDisplayMetrics().heightPixels;
	}

	private int calculateX() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
}
