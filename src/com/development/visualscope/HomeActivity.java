package com.development.visualscope;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.os.Build;

public class HomeActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
			
		if (!isNetworkOnline()) {
			new AlertDialog.Builder(this)
			    .setTitle("Network Connection")
			    .setMessage("Network is disconnected.")
			    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) { 
			            // continue with delete
			        }
			     })		    
			    .setIcon(android.R.drawable.ic_dialog_alert)
			    .show();
		}

//		startActivity(new Intent(this, SendSmsActivity.class));
	}

	public void createNewProject(View view) {	
		startActivity(new Intent(this, CreateNewProjectActivity.class));
	}	
	
	public void showAllProjects(View view) {
		startActivity(new Intent(this, ShowAllProjectsActivity.class));
	}
	
	public void editProjects(View view) {	
		startActivity(new Intent(this, EditProjectsActivity.class));
	}	
	
	public boolean isNetworkOnline() {
		boolean status = false;
		try {
			ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo netInfo = cm.getNetworkInfo(0);
			if (netInfo != null
					&& netInfo.getState() == NetworkInfo.State.CONNECTED) {
				status = true;
			} else {
				netInfo = cm.getNetworkInfo(1);
				if (netInfo != null
						&& netInfo.getState() == NetworkInfo.State.CONNECTED) {
					status = true;
				}
			}
		} catch (Exception e) {
			// e.printStackTrace();
			return false;
		}

		return status;
	}
}
