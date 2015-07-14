package com.development.visualscope;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class CreateProjectSuccessActivity extends ActionBarActivity {

	private String pdfLink;
	private TextView pdfLinkText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_project_success);	
		
		pdfLinkText = (TextView) findViewById(R.id.pdfLinkText);
		
		Bundle extras = getIntent().getExtras();
	    if (extras != null) {
	    	pdfLink = extras.getString("PDF_LINK");
//	    	pdfLinkText.setText(pdfLink);
	    	pdfLinkText.setText(Html.fromHtml(
	    			"<a href='" + pdfLink + "'>" + pdfLink + "</a>"));	    		    
	    	pdfLinkText.setMovementMethod(LinkMovementMethod.getInstance());
	    }
	}
	
	public void sharePdfLink(View view) {
		AlertDialog shareMethodDialog;
		
		final CharSequence[] items = { "SMS", "E-mail" };
		
        // Creating and Building the Dialog 
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select the share method");
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int item) {	        	
	        	
	            switch(item)
	            {
	                case 0: // sms
	                	Intent smsActivity = new Intent(CreateProjectSuccessActivity.this, SendSmsActivity.class);
	                	smsActivity.putExtra("PDF_LINK", pdfLink);
	                	startActivity(smsActivity);	                	
	                    break;
	                case 1: // email
	                	Intent emailActivity = new Intent(CreateProjectSuccessActivity.this, SendEmailActivity.class);
	                	emailActivity.putExtra("PDF_LINK", pdfLink);
	                	startActivity(emailActivity);
	                    break;                
	                
	            }
	            dialog.dismiss();	               
	        }
        });
        
        shareMethodDialog = builder.create();
        shareMethodDialog.show();		
	}
	
	public void backToHome(View view) {
		startActivity(new Intent(this, HomeActivity.class));
	}
	
	public void exitApplication(View view) {		
		this.finish();
	    Intent intent = new Intent(Intent.ACTION_MAIN);
	    intent.addCategory(Intent.CATEGORY_HOME);
	    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    startActivity(intent);
	}
}
