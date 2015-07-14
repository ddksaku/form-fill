package com.development.visualscope;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;

public class SendEmailActivity extends ActionBarActivity {
	
	private EditText emailRecipientEdit;	
	private EditText emailSubjectEdit;	
	private EditText emailBodyEdit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send_email);
		
		emailRecipientEdit = (EditText) findViewById(R.id.emailRecipientEdit);
		emailSubjectEdit = (EditText) findViewById(R.id.emailSubjectEdit);
		emailBodyEdit = (EditText) findViewById(R.id.emailBodyEdit);	
		
		Bundle extras = getIntent().getExtras();
	    if (extras != null) {
	    	String pdfLink = extras.getString("PDF_LINK");
	    	emailBodyEdit.setText(pdfLink);
	    }
	}
	
	public void sendEmail(View view) {				
		if (checkForm()) {					
			String[] recipients = { emailRecipientEdit.getText().toString() };
			Intent email = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
			
			// prompts email clients only
			email.setType("message/rfc822");
			
			email.putExtra(Intent.EXTRA_EMAIL, recipients);
			email.putExtra(Intent.EXTRA_SUBJECT, emailSubjectEdit.getText().toString());
			email.putExtra(Intent.EXTRA_TEXT, emailBodyEdit.getText().toString());
			
			try {			
			    // the user can choose the email client
				startActivity(Intent.createChooser(email, "Choose an email client from..."));				
			} catch (android.content.ActivityNotFoundException ex) {
			     Toast.makeText(this, "No email client installed.", Toast.LENGTH_LONG).show();
			}
		}	
	}
	
	private boolean checkForm() {
		boolean isValid = true;
		if (emailRecipientEdit.getText().toString().isEmpty()) {
			emailRecipientEdit.setError(getResources().getString(R.string.required_field_empty_error));			
			isValid = false;
		}
		
		if (emailSubjectEdit.getText().toString().isEmpty()) {
			emailSubjectEdit.setError(getResources().getString(R.string.required_field_empty_error));
			isValid = false;
		}
		
		if (emailBodyEdit.getText().toString().isEmpty()) {
			emailBodyEdit.setError(getResources().getString(R.string.required_field_empty_error));
			isValid = false;
		}
		
		return isValid;
	}
}
