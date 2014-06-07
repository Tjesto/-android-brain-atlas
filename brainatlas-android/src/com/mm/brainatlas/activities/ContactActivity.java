package com.mm.brainatlas.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.mm.brainatlas.activities.impl.AbstractBrainActivityWithMenus;
import com.mm.brainatlas.utils.EmailEvents;
import com.mm.brainatlas_android.R;

public class ContactActivity extends AbstractBrainActivityWithMenus {

	protected Context context;
	
	@Override
	protected void onCreate(Bundle bundle) {		
		super.onCreate(bundle);
		setContentView(R.layout.contact);
		context = (Context) this;
		findViewById(R.id.authors_contact).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EmailEvents.sendContactMail(context);				
			}
		});
		addMenuButton(this);
	}
}
