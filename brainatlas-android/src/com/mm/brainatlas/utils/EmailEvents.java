package com.mm.brainatlas.utils;

import com.mm.brainatlas_android.R;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public final class EmailEvents {

	private static final String CONTACT_SUBJECT = "Temat";
	private static final String EMAIL_ADDRESS = "atlas.mozgu@gmail.com";
	private static final String TEST_SUBJECT = "Wiadomoœæ testowa";
	private static final String TEST_MESSAGE = "To jest wiadomoœæ testowa wys³ana z aplikacji Atlas Mózgu. Jak widaæ wysy³anie maili dzia³a";

	public static void sendContactMail(Context context) {		
		Intent intent = new Intent(Intent.ACTION_SENDTO);
		intent.setType("message/rfc822");
		intent.putExtra(Intent.EXTRA_SUBJECT, TEST_SUBJECT);
		intent.setData(Uri.parse("mailto:" + EMAIL_ADDRESS));
		try {
			context.startActivity(intent);
		} catch (ActivityNotFoundException e) {
			Toast.makeText(context, "Nie znaleziono klienta e-mail", Toast.LENGTH_SHORT).show();
		}
	}
	
	public static void sendTestMail(Context context) {
		Intent intent = new Intent(Intent.ACTION_SENDTO);
		intent.setType("message/rfc822");
		intent.putExtra(Intent.EXTRA_SUBJECT, TEST_SUBJECT);
		intent.putExtra(Intent.EXTRA_TEXT, TEST_MESSAGE);
		intent.setData(Uri.parse("mailto:" + EMAIL_ADDRESS));
		context.startActivity(intent);
		/*try {
			context.startActivity(Intent.createChooser(intent, context.getText(R.string.email_send_request)));
		} catch (ActivityNotFoundException e) {
			Toast.makeText(context, "Nie znaleziono klienta e-mail", Toast.LENGTH_SHORT).show();
		}*/
	}

}
