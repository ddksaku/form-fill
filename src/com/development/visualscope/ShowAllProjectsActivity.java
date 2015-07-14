package com.development.visualscope;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.os.Build;

public class ShowAllProjectsActivity extends ListActivity {
	
	// Progress Dialog
    private ProgressDialog progressDialog;
 
    // Creating JSON Parser object
    private JSONParser jsonParser = new JSONParser();
 
    private ArrayList<HashMap<String, String>> pdfsList;
 
    // url to get all pdfs list
//    private static String url_all_pdfs = "http://192.168.1.102:8889/visualscope/admin/project_list_service.php";
    private static String url_all_pdfs = "http://development.visualscope.org/admin/project_list_service.php";
 
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PDFS = "pdfs";    
    private static final String TAG_NAME = "name";
    private static final String TAG_LINK = "link";
    private static final String TAG_CREATED_DATE = "created_date";
 
    // pdfs JSONArray
    private JSONArray pdfs = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_all_projects);	
		
		// Hashmap for ListView
        pdfsList = new ArrayList<HashMap<String, String>>();
 
        // Loading pdfs in Background Thread
        new LoadAllPdfs().execute();        
	}	
		   
    /**
     * Background Async Task to Load all pdfs by making HTTP Request
     * */
    class LoadAllPdfs extends AsyncTask<String, String, String> {
 
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(ShowAllProjectsActivity.this);
            progressDialog.setMessage("Loading Projects. Please wait..");
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
 
        /**
         * getting All pdfs from url
         * */
        protected String doInBackground(String... args) {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // getting JSON string from URL
            JSONObject json = jsonParser.makeHttpRequest(url_all_pdfs, "GET", params);
 
            // Check your log cat for JSON reponse
            Log.d("All Pdfs: ", json.toString());
 
            try {            	
                // Checking for SUCCESS TAG            	
                int success = json.getInt(TAG_SUCCESS);
 
                if (success == 1) {
                    // pdfs found
                    // Getting Array of Pdfs
                    pdfs = json.getJSONArray(TAG_PDFS);
 
                    // looping through All Pdfs
                    for (int index = 0; index < pdfs.length(); index++) {
                        JSONObject jsonObject = pdfs.getJSONObject(index);
 
                        // Storing each json item in variable                        
                        String name = jsonObject.getString(TAG_NAME);
                        String link = jsonObject.getString(TAG_LINK);
                        String createdDate = jsonObject.getString(TAG_CREATED_DATE);                        
 
                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();
 
                        // adding each child node to HashMap key => value                        
                        map.put(TAG_NAME, name);
                        map.put(TAG_LINK, link);
                        map.put(TAG_CREATED_DATE, createdDate);                        
 
                        // adding HashList to ArrayList
                        pdfsList.add(map);
                    }                    
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
 
            return null;
        }
 
        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all pdfs
            progressDialog.dismiss();
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    /**
                     * Updating parsed JSON data into ListView
                     * */
                    ListAdapter adapter = new SimpleAdapter(ShowAllProjectsActivity.this,	                    		
                    		pdfsList,
                            R.layout.pdf_list_item, 
                            new String[] {TAG_NAME, TAG_LINK, TAG_CREATED_DATE},
                            new int[] {R.id.pdfName, R.id.pdfLink, R.id.pdfCreatedDate});
                    // updating listview
                    setListAdapter(adapter);
                    
                    ListView listView = getListView(); 
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    	  @Override
                    	  public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {                    		                      		                     		
                    		  Intent intent = new Intent(Intent.ACTION_VIEW);
                    		  intent.setDataAndType(Uri.parse(pdfsList.get(position).get(TAG_LINK)), "text/html");
                    		  startActivity(intent);
                    	  }
                    });
                }
            }); 
        } 
    }
    
	public void backToHome(View view) {
		startActivity(new Intent(this, HomeActivity.class));
	}
}
