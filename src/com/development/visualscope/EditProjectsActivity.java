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
import android.webkit.WebView;
import android.widget.Toast;
import android.os.Build;

public class EditProjectsActivity extends ActionBarActivity {

	private WebView editProjectWebView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_projects);
		
		editProjectWebView = (WebView) findViewById(R.id.editProjectWebView);
		editProjectWebView.getSettings().setJavaScriptEnabled(true);
		editProjectWebView.loadUrl("http://development.visualscope.org/admin/project_list.php");
	}
}
