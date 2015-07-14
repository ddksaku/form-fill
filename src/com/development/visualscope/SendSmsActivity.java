package com.development.visualscope;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.os.Build;
import android.provider.ContactsContract;

public class SendSmsActivity extends ActionBarActivity {		
	private List<Contact> contactList = new ArrayList<Contact>();
	
	private EditText phoneNumberEdit;
	private EditText smsBodyEdit;
	private Spinner contactListSpinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send_sms);	
		
		phoneNumberEdit = (EditText) findViewById(R.id.phoneNumberEdit);
		smsBodyEdit = (EditText) findViewById(R.id.smsBodyEdit);
		contactListSpinner = (Spinner) findViewById(R.id.contactListSpinner);
		
		Bundle extras = getIntent().getExtras();
	    if (extras != null) {
	    	String pdfLink = extras.getString("PDF_LINK");
	    	smsBodyEdit.setText(pdfLink);
	    }
	    
	    fetchAllContacts();
	    ContactKeyValueSpinner adapter = new ContactKeyValueSpinner(this, contactList);		
	    contactListSpinner.setAdapter(adapter);
	    
	    contactListSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
	    	@Override	    	
	    	public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
	    		phoneNumberEdit.setText(contactList.get(pos).phoneNumber);
	    	}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub				
			}	           
	    });
	}
	
	public void sendSms(View view) {				
		if (checkForm()) {					
			try {				
				String phoneNumber = phoneNumberEdit.getText().toString();
				String smsBody = smsBodyEdit.getText().toString();
				
				SmsManager smsManager = SmsManager.getDefault();
				smsManager.sendTextMessage(phoneNumber, null, smsBody, null, null);
				Toast.makeText(this, "SMS Sent.", Toast.LENGTH_LONG).show();
				
				// startActivity(new Intent(this, CreateProjectSuccessActivity.class));
			} catch (Exception e) {
				Toast.makeText(this, "SMS faild, please try again later.", Toast.LENGTH_LONG).show();
				e.printStackTrace();
			}
		}	
	}
	
	private boolean checkForm() {
		boolean isValid = true;
		if (phoneNumberEdit.getText().toString().isEmpty()) {
			phoneNumberEdit.setError(getResources().getString(R.string.required_field_empty_error));			
			isValid = false;
		}
		
		if (smsBodyEdit.getText().toString().isEmpty()) {
			smsBodyEdit.setError(getResources().getString(R.string.required_field_empty_error));
			isValid = false;
		}		
		
		return isValid;
	}
	
	public void fetchAllContacts() {		
//		String phoneNumber = null;
//		String email = null;

		Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
		String _ID = ContactsContract.Contacts._ID;
		String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
		String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;

		Uri PhoneCONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
		String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
		String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;

		Uri EmailCONTENT_URI =  ContactsContract.CommonDataKinds.Email.CONTENT_URI;
		String EmailCONTACT_ID = ContactsContract.CommonDataKinds.Email.CONTACT_ID;
		String DATA = ContactsContract.CommonDataKinds.Email.DATA;

//		StringBuffer output = new StringBuffer();

		ContentResolver contentResolver = getContentResolver();

		Cursor cursor = contentResolver.query(CONTENT_URI, null,null, null, null);	

		// Loop for every contact in the phone
		if (cursor.getCount() > 0) {
			while (cursor.moveToNext()) {								
				String contact_id = cursor.getString(cursor.getColumnIndex( _ID ));
				String name = cursor.getString(cursor.getColumnIndex( DISPLAY_NAME ));
				
				int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex( HAS_PHONE_NUMBER )));
				if (hasPhoneNumber > 0) {
//					output.append("\n First Name:" + name);

					// Query and loop for every phone number of the contact
					Cursor phoneCursor = contentResolver.query(PhoneCONTENT_URI, null, Phone_CONTACT_ID + " = ?", new String[] { contact_id }, null);					
//					while (phoneCursor.moveToNext()) {
//						phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER));
//						output.append("\n Phone number:" + phoneNumber);
//					}
					if (phoneCursor.moveToFirst()) {
						String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER));
						contactList.add(new Contact(name, phoneNumber));
					}
					phoneCursor.close();

//					// Query and loop for every email of the contact
//					Cursor emailCursor = contentResolver.query(EmailCONTENT_URI,	null, EmailCONTACT_ID+ " = ?", new String[] { contact_id }, null);
//					while (emailCursor.moveToNext()) {
//						email = emailCursor.getString(emailCursor.getColumnIndex(DATA));
//						output.append("\nEmail:" + email);
//					}
//					emailCursor.close();
				}
//				output.append("\n");
			}		
		}							
	}
}
