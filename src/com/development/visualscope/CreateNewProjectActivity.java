package com.development.visualscope;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
// import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;
import android.provider.MediaStore;

public class CreateNewProjectActivity extends ActionBarActivity {	
	private Bitmap siteImageBitmap = null;
	
	private final int GALLERY_REQUEST_CODE_START = 1;
	private final int CAMERA_REQUEST_CODE_START = 100;
	private final int PICTURE_TABLE_ROW_COUNT = 17;
	
	private String pdfLink;
	private String errorMessage;
	private List<State> stateList;	
	
    private ProgressDialog progressDialog; 
    private JSONParser jsonParser = new JSONParser();
    
    // url to create new pdf
    private static String url_create_pdf = "http://development.visualscope.org/admin/project_edit_service.php";
    private static String url_report_pdf = "http://development.visualscope.org/admin/project_client_report_service.php";
    
//    private static String url_create_pdf = "http://192.168.1.102:8889/visualscope/admin/project_edit_service.php";
 
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PDF_LINK = "pdf_link";
    private static final String TAG_PROJECT_ID = "project_id";
    private static final String TAG_ERROR = "error";       
        
    private EditText projectNameEdit;
    private EditText firstContactFirstNameEdit;
    private EditText firstContactLastNameEdit;
    private EditText firstContactTitleEdit;
    private EditText firstContactPhoneEdit;
    private EditText secondContactNameEdit;
    private EditText secondContactPhoneEdit;
    private EditText faxEdit;
    private EditText emailEdit;
    private EditText addressEdit;
    private EditText cityEdit;
    private Spinner stateSpinner;
    private EditText zipCodeEdit;
    private CheckBox billingAddressCheckBox;
    private EditText billingAddressEdit;
    private EditText billingCityEdit;
    private Spinner billingStateSpinner;
    private EditText billingZipCodeEdit;
    private EditText websiteEdit;
    private EditText clientSampleEdit;
    private EditText tenantNoticeEdit;
    private EditText grantedAccessEdit;
    private EditText instructionEdit;
    private TextView sitemapImageText;
    private CheckBox calculateTaxesCheckBox;          
    
    private EditText writerEdit;
    private EditText proposalTitleEdit;
    private EditText proposalPhoneEdit;
    private EditText proposalEmailEdit;
    private EditText quoteUntilEdit;                      
    
    private CheckBox apartmentCheckBox;
    private CheckBox commericalCheckBox;
    private CheckBox parkingCheckBox;
    private CheckBox miscellaneousCheckBox;
    
    private ApartmentBlock apartmentBlock;
	private CommericalBlock commericalBlock;
	private ParkingBlock parkingBlock;
	private MiscellaneousBlock miscellaneousBlock;
	private EquipmentBlock equipmentBlock; 
	private PictureBlock pictureBlock;
    	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_new_project);									
		
		projectNameEdit = (EditText) findViewById(R.id.projectNameEdit);
		firstContactFirstNameEdit = (EditText) findViewById(R.id.firstContactFirstNameEdit);
		firstContactLastNameEdit = (EditText) findViewById(R.id.firstContactLastNameEdit);
		firstContactTitleEdit = (EditText) findViewById(R.id.firstContactTitleEdit);
		firstContactPhoneEdit = (EditText) findViewById(R.id.firstContactPhoneEdit);
		secondContactNameEdit = (EditText) findViewById(R.id.secondContactNameEdit);
		secondContactPhoneEdit = (EditText) findViewById(R.id.secondContactPhoneEdit);
		faxEdit = (EditText) findViewById(R.id.faxEdit);
		emailEdit = (EditText) findViewById(R.id.emailEdit);
		addressEdit = (EditText) findViewById(R.id.addressEdit);
		cityEdit = (EditText) findViewById(R.id.cityEdit);
		stateSpinner = (Spinner) findViewById(R.id.stateSpinner);
		zipCodeEdit = (EditText) findViewById(R.id.zipCodeEdit);
		billingAddressCheckBox = (CheckBox) findViewById(R.id.billingAddressCheckBox);
		billingAddressEdit = (EditText) findViewById(R.id.billingAddressEdit);
		billingCityEdit = (EditText) findViewById(R.id.billingCityEdit);
		billingStateSpinner = (Spinner) findViewById(R.id.billingStateSpinner);
		billingZipCodeEdit = (EditText) findViewById(R.id.billingZipCodeEdit);
		websiteEdit = (EditText) findViewById(R.id.websiteEdit);
		clientSampleEdit = (EditText) findViewById(R.id.clientSampleEdit);
		tenantNoticeEdit = (EditText) findViewById(R.id.tenantNoticeEdit);
		grantedAccessEdit = (EditText) findViewById(R.id.grantedAccessEdit);
		instructionEdit = (EditText) findViewById(R.id.instructionEdit);
		sitemapImageText = (TextView) findViewById(R.id.sitemapImageText);
		calculateTaxesCheckBox = (CheckBox) findViewById(R.id.calculateTaxesCheckBox);
		
		writerEdit = (EditText) findViewById(R.id.writerEdit);
		proposalTitleEdit = (EditText) findViewById(R.id.proposalTitleEdit);
		proposalPhoneEdit = (EditText) findViewById(R.id.proposalPhoneEdit);
		proposalEmailEdit = (EditText) findViewById(R.id.proposalEmailEdit);
		quoteUntilEdit = (EditText) findViewById(R.id.quoteUntilEdit);
	    
		apartmentCheckBox = (CheckBox) findViewById(R.id.apartmentCheckBox);
		commericalCheckBox = (CheckBox) findViewById(R.id.commericalCheckBox);
		parkingCheckBox = (CheckBox) findViewById(R.id.parkingCheckBox);
		miscellaneousCheckBox = (CheckBox) findViewById(R.id.miscellaneousCheckBox);
		
		apartmentBlock = (ApartmentBlock) findViewById(R.id.apartmentBlock);
		commericalBlock = (CommericalBlock) findViewById(R.id.commericalBlock);
		parkingBlock = (ParkingBlock) findViewById(R.id.parkingBlock);
		miscellaneousBlock = (MiscellaneousBlock) findViewById(R.id.miscellaneousBlock);
		equipmentBlock = (EquipmentBlock) findViewById(R.id.equipmentBlock);
		pictureBlock = (PictureBlock) findViewById(R.id.pictureBlock);
		
		for (int index = 0; index < 17; index++) {
			final int galleryRequestCode = GALLERY_REQUEST_CODE_START + index;			
			pictureBlock.pictureTableRows[index].pictureSelectButton.setOnClickListener(new View.OnClickListener() {							
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub						
					onImageBrowseClick(v, galleryRequestCode);
				}						
			});
			
			final int cameraRequestCode = CAMERA_REQUEST_CODE_START + index;			
			pictureBlock.pictureTableRows[index].pictureCameraButton.setOnClickListener(new View.OnClickListener() {							
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub						
					onImageCameraClick(v, cameraRequestCode);
				}						
			});
		}
		
		// load items to the state, billing state spinners				
//		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, 
//				R.array.states_array, android.R.layout.simple_spinner_item); 
//		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);		
//		stateSpinner.setAdapter(adapter);
//		billingStateSpinner.setAdapter(adapter);
		loadSpinnerItems();
		
		invisibleAllBlocks();
		
		loadValues();
		
		final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
				
		firstContactPhoneEdit.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
		secondContactPhoneEdit.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
		proposalPhoneEdit.addTextChangedListener(new PhoneNumberFormattingTextWatcher());				
	}		
	
	private void loadSpinnerItems() {
		stateList = new ArrayList<State>();
		
		stateList.add(new State(23, "Alabama"));
		stateList.add(new State(60, "Alaska"));
		stateList.add(new State(48, "Arizona"));
		stateList.add(new State(41, "Arkansas"));
		stateList.add(new State(51, "California"));
		stateList.add(new State(44, "Colorado"));
		stateList.add(new State(8, "Connecticut"));
		stateList.add(new State(13, "Delaware"));
		stateList.add(new State(14, "District of Columbia"));
		stateList.add(new State(21, "Florida"));
		stateList.add(new State(20, "Georgia"));
		stateList.add(new State(53, "Hawaii"));
		stateList.add(new State(46, "Idaho"));
		stateList.add(new State(36, "Illinois"));
		stateList.add(new State(28, "Indiana"));
		stateList.add(new State(30, "Iowa"));
		stateList.add(new State(38, "Kansas"));
		stateList.add(new State(26, "Kentucky"));
		stateList.add(new State(40, "Louisiana"));
		stateList.add(new State(6, "Maine"));
		stateList.add(new State(16, "Maryland"));
		stateList.add(new State(3, "Massachusetts"));
		stateList.add(new State(29, "Michigan"));
		stateList.add(new State(32, "Minnesota"));
		stateList.add(new State(25, "Mississippi"));
		stateList.add(new State(37, "Missouri"));
		stateList.add(new State(35, "Montana"));
		stateList.add(new State(39, "Nebraska"));
		stateList.add(new State(50, "Nevada"));
		stateList.add(new State(5, "New Hampshire"));
		stateList.add(new State(10, "New Jersey"));
		stateList.add(new State(49, "New Mexico"));
		stateList.add(new State(9, "New York"));
		stateList.add(new State(18, "North Carolina"));
		stateList.add(new State(34, "North Dakota"));
		stateList.add(new State(27, "Ohio"));
		stateList.add(new State(42, "Oklahoma"));
		stateList.add(new State(58, "Oregon"));
		stateList.add(new State(12, "Pennsylvania"));
		stateList.add(new State(4, "Rhode Island"));
		stateList.add(new State(19, "South Carolina"));
		stateList.add(new State(33, "South Dakota"));
		stateList.add(new State(24, "Tennessee"));
		stateList.add(new State(43, "Texas"));
		stateList.add(new State(47, "Utah"));
		stateList.add(new State(7, "Vermont"));
		stateList.add(new State(15, "Virginia"));
		stateList.add(new State(59, "Washington"));
		stateList.add(new State(17, "West Virginia"));
		stateList.add(new State(31, "Wisconsin"));
		stateList.add(new State(45, "Wyoming"));  
		
		KeyValueSpinner adapter = new KeyValueSpinner(this, stateList);
		
		stateSpinner.setAdapter(adapter);
		stateSpinner.setSelection(47);
		billingStateSpinner.setAdapter(adapter);
		billingStateSpinner.setSelection(47);
	}
	
	public void createNewProject(View view) {
		// creating new pdf in background thread				 
		loadValues();		
		
		if (checkForm()) {
			new CreateNewPdf().execute();			
		}        
	}	
	
	private void invisibleAllBlocks() {
		apartmentBlock.setVisibility(View.GONE);
		commericalBlock.setVisibility(View.GONE);
		parkingBlock.setVisibility(View.GONE);
		miscellaneousBlock.setVisibility(View.GONE);
	}
	
	public void onApartmentClick(View view) {		
		if (apartmentCheckBox.isChecked()) {
			apartmentBlock.setVisibility(View.VISIBLE);
		} else {
			apartmentBlock.setVisibility(View.GONE);
		}
	}
	
	public void onCommericalClick(View view) {
		if (commericalCheckBox.isChecked()) {
			commericalBlock.setVisibility(View.VISIBLE);
		} else {
			commericalBlock.setVisibility(View.GONE);
		}
	}
	
	public void onParkingClick(View view) {
		if (parkingCheckBox.isChecked()) {
			parkingBlock.setVisibility(View.VISIBLE);
		} else {
			parkingBlock.setVisibility(View.GONE);
		}
	}
	
	public void onMiscellaneousClick(View view) {
		if (miscellaneousCheckBox.isChecked()) {
			miscellaneousBlock.setVisibility(View.VISIBLE);
		} else {
			miscellaneousBlock.setVisibility(View.GONE);
		}
	}
	
	public Bitmap getScaledBitmap(String filePath) { 
		File file = new File(filePath);
		if (!file.exists()) {
			return null;
		}
		
	    // Decode image size 
	    BitmapFactory.Options bitmapFactoryOptions = new BitmapFactory.Options(); 
	    bitmapFactoryOptions.inJustDecodeBounds = true; 
	    BitmapFactory.decodeFile(filePath, bitmapFactoryOptions); 
	    	    
	    // The new size we want to scale to 
	    final int REQUIRED_SIZE = 1024; 
	
	    // Find the correct scale value. It should be the power of 2. 
	    int width_tmp = bitmapFactoryOptions.outWidth, height_tmp = bitmapFactoryOptions.outHeight; 
	    int scale = 1; 
	    while (true) {
		    if (width_tmp < REQUIRED_SIZE && height_tmp < REQUIRED_SIZE) { 
		    	break;
		    }
		    
		    width_tmp /= 2; 
		    height_tmp /= 2; 
		    scale *= 2; 
	    } 
	
	    // Decode with inSampleSize 
	    BitmapFactory.Options bitmapFactoryOptionOutput = new BitmapFactory.Options(); 
	    bitmapFactoryOptionOutput.inSampleSize = scale; 
	    return BitmapFactory.decodeFile(filePath, bitmapFactoryOptionOutput); 	    
    }
	
	/**
     * Background Async Task to Create new pdf
     * */
    class CreateNewPdf extends AsyncTask<String, String, String> {
 
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(CreateNewProjectActivity.this);
            progressDialog.setMessage("Processing. Please wait..");
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(true);
            progressDialog.show();
        }
 
        /**
         * Creating pdf
         * */
        protected String doInBackground(String... args) { 
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("vf_accept", "submit"));
            
            params.add(new BasicNameValuePair("name", projectNameEdit.getText().toString()));
            params.add(new BasicNameValuePair("contact1_first_name", firstContactFirstNameEdit.getText().toString()));
            params.add(new BasicNameValuePair("contact1_last_name", firstContactLastNameEdit.getText().toString()));
            params.add(new BasicNameValuePair("contact1_title", firstContactTitleEdit.getText().toString()));
            params.add(new BasicNameValuePair("contact1_phone", firstContactPhoneEdit.getText().toString()));
            params.add(new BasicNameValuePair("contact2_name", secondContactNameEdit.getText().toString()));
            params.add(new BasicNameValuePair("contact2_phone", secondContactPhoneEdit.getText().toString()));
            params.add(new BasicNameValuePair("fax", faxEdit.getText().toString()));
            params.add(new BasicNameValuePair("email", emailEdit.getText().toString()));
            params.add(new BasicNameValuePair("address", addressEdit.getText().toString()));
            params.add(new BasicNameValuePair("city", cityEdit.getText().toString()));            
            params.add(new BasicNameValuePair("state", Integer.toString(stateList.get(stateSpinner.getSelectedItemPosition()).getId())));
            params.add(new BasicNameValuePair("zip", zipCodeEdit.getText().toString()));
            params.add(new BasicNameValuePair("billing_address", billingAddressEdit.getText().toString()));
            params.add(new BasicNameValuePair("billing_city", billingCityEdit.getText().toString()));
            params.add(new BasicNameValuePair("billing_state", Integer.toString(stateList.get(billingStateSpinner.getSelectedItemPosition()).getId())));
            params.add(new BasicNameValuePair("billing_zip", billingZipCodeEdit.getText().toString()));            
            params.add(new BasicNameValuePair("website", websiteEdit.getText().toString()));
            params.add(new BasicNameValuePair("sample", clientSampleEdit.getText().toString()));
            params.add(new BasicNameValuePair("instruct", tenantNoticeEdit.getText().toString()));
            params.add(new BasicNameValuePair("granted", grantedAccessEdit.getText().toString()));
            params.add(new BasicNameValuePair("instructions", instructionEdit.getText().toString()));            
            if (calculateTaxesCheckBox.isChecked()) {
            	params.add(new BasicNameValuePair("vf_dont_calculate_taxes", "1"));            	            
            }
            
            // site image bitmap            
//            Bitmap siteImageBitmap = BitmapFactory.decodeFile(sitemapImageText.getText().toString());   
            if (siteImageBitmap == null) {
            	siteImageBitmap = getScaledBitmap(sitemapImageText.getText().toString());
            }            
            
            if (siteImageBitmap != null) {
	            ByteArrayOutputStream siteImageStream = new ByteArrayOutputStream();
	            siteImageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, siteImageStream); //compress to which format you want.
	            byte [] siteImageByte = siteImageStream.toByteArray();
	//            String siteImageString = Base64.encodeToString(siteImageByte, Base64.DEFAULT);
	            String siteImageString = Base64.encodeBytes(siteImageByte);
	            params.add(new BasicNameValuePair("vf_file", siteImageString));
            }
            
            params.add(new BasicNameValuePair("proposal_writer", writerEdit.getText().toString()));
            params.add(new BasicNameValuePair("proposal_title", proposalTitleEdit.getText().toString()));
            params.add(new BasicNameValuePair("proposal_phone", proposalPhoneEdit.getText().toString()));
            params.add(new BasicNameValuePair("proposal_email", proposalEmailEdit.getText().toString()));
            params.add(new BasicNameValuePair("proposal_effective_until", quoteUntilEdit.getText().toString()));
            
            for (int index = 0; index < PICTURE_TABLE_ROW_COUNT; index++) {
            	PictureTableRow pictureTableRow = pictureBlock.pictureTableRows[index];            	
            	if (pictureTableRow.imagePath != null) {
            		pictureTableRow.imageBitmap = getScaledBitmap(pictureTableRow.imagePath);
            	}
            	if (pictureTableRow.imageBitmap != null) {
            		ByteArrayOutputStream imageStream = new ByteArrayOutputStream();
            		pictureTableRow.imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, imageStream); //compress to which format you want.
    	            byte [] imageByte = imageStream.toByteArray();
    	            String imageString = Base64.encodeBytes(imageByte);    	            
    	            params.add(new BasicNameValuePair("vf_file" + Integer.toString(index + 1), imageString));
    	            params.add(new BasicNameValuePair("vf_photo_name" + Integer.toString(index + 1), pictureTableRow.pictureNameEdit.getText().toString()));
    	            params.add(new BasicNameValuePair("vf_desc" + Integer.toString(index + 1), pictureTableRow.pictureFooterEdit.getText().toString()));
            	}
            }
            
            if (apartmentCheckBox.isChecked()) {
            	params.add(new BasicNameValuePair("is_apartment", "1"));
            	            	
            	params.add(new BasicNameValuePair("apartment_sqft", apartmentBlock.apartmentSqftEdit.getText().toString()));            	
            	params.add(new BasicNameValuePair("apartment_sqft_multiplier", apartmentBlock.apartmentSqftMultiplierEdit.getText().toString()));
            	params.add(new BasicNameValuePair("apartment_sqft_info", apartmentBlock.apartmentSqftInfoEdit.getText().toString()));
            	params.add(new BasicNameValuePair("apartment_buildings", apartmentBlock.apartmentBuildingsEdit.getText().toString()));
            	params.add(new BasicNameValuePair("apartment_buildings_multiplier", apartmentBlock.apartmentBuildingsMultiplierEdit.getText().toString()));
            	params.add(new BasicNameValuePair("apartment_buildings_info", apartmentBlock.apartmentBuildingsInfoEdit.getText().toString()));
            	params.add(new BasicNameValuePair("apartment_units", apartmentBlock.apartmentUnitsEdit.getText().toString()));
            	params.add(new BasicNameValuePair("apartment_units_multiplier", apartmentBlock.apartmentUnitsMultiplierEdit.getText().toString()));
            	params.add(new BasicNameValuePair("apartment_units_info", apartmentBlock.apartmentUnitsInfoEdit.getText().toString()));
            	params.add(new BasicNameValuePair("apartment_parking_stalls", apartmentBlock.apartmentParkingEdit.getText().toString()));
            	params.add(new BasicNameValuePair("apartment_parking_stalls_multiplier", apartmentBlock.apartmentParkingMultiplierEdit.getText().toString()));
            	params.add(new BasicNameValuePair("apartment_parking_stalls_info", apartmentBlock.apartmentParkingInfoEdit.getText().toString()));
            	params.add(new BasicNameValuePair("apartment_car_ports", apartmentBlock.apartmentCarEdit.getText().toString()));
            	params.add(new BasicNameValuePair("apartment_car_ports_multiplier", apartmentBlock.apartmentCarMultiplierEdit.getText().toString()));
            	params.add(new BasicNameValuePair("apartment_car_ports_info", apartmentBlock.apartmentCarInfoEdit.getText().toString()));
            	params.add(new BasicNameValuePair("apartment_stairways", apartmentBlock.apartmentStairwaysEdit.getText().toString()));
            	params.add(new BasicNameValuePair("apartment_stairways_multiplier", apartmentBlock.apartmentStairwaysMultiplierEdit.getText().toString()));
            	params.add(new BasicNameValuePair("apartment_stairways_info", apartmentBlock.apartmentStairwaysInfoEdit.getText().toString()));
            	params.add(new BasicNameValuePair("apartment_railings", apartmentBlock.apartmentRailingsEdit.getText().toString()));
            	params.add(new BasicNameValuePair("apartment_railings_multiplier", apartmentBlock.apartmentRailingsMultiplierEdit.getText().toString()));
            	params.add(new BasicNameValuePair("apartment_railings_info", apartmentBlock.apartmentRailingsInfoEdit.getText().toString()));
            	params.add(new BasicNameValuePair("apartment_stories", apartmentBlock.apartmentStoriesEdit.getText().toString()));
            	params.add(new BasicNameValuePair("apartment_breezeway", apartmentBlock.apartmentBreezyEdit.getText().toString()));
            	params.add(new BasicNameValuePair("apartment_breezeway_multiplier", apartmentBlock.apartmentBreezyMultiplierEdit.getText().toString()));
            	params.add(new BasicNameValuePair("apartment_breezeway_info", apartmentBlock.apartmentBreezyInfoEdit.getText().toString()));
            	params.add(new BasicNameValuePair("apartment_other", apartmentBlock.apartmentOtherEdit.getText().toString()));
            	
            	// siding type
            	SidingType apartmentSidingType = apartmentBlock.apartmentSidingType;            	
            	if (apartmentSidingType.stuccoCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("apartment_type_of_siding_stucco", "1"));
            	}
            	if (apartmentSidingType.vinylCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("apartment_type_of_siding_vinyl", "1"));
            	}
            	if (apartmentSidingType.hardyCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("apartment_type_of_siding_hardy", "1"));
            	}
            	if (apartmentSidingType.brickCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("apartment_type_of_siding_brick", "1"));
            	}
            	if (apartmentSidingType.woodCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("apartment_type_of_siding_wood", "1"));
            	}
            	if (apartmentSidingType.concreteCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("apartment_type_of_siding_concrete", "1"));
            	}
            	if (apartmentSidingType.metalCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("apartment_type_of_siding_metal", "1"));
            	}
            	
            	// lift needed
            	LiftNeeded apartmentLiftNeeded = apartmentBlock.apartmentLiftNeeded;
            	if (apartmentLiftNeeded.liftNeededCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("apartment_lift_needed", "1"));
            	}
            	params.add(new BasicNameValuePair("apartment_lift_needed_multiplier", apartmentLiftNeeded.amountEdit.getText().toString()));
            	params.add(new BasicNameValuePair("apartment_lift_needed_days", apartmentLiftNeeded.daysNeededEdit.getText().toString()));
            	
            	// environmental
            	Environmental apartmentEnviromental = apartmentBlock.apartmentEnvironmental;
            	params.add(new BasicNameValuePair("apartment_type_of_drains", apartmentEnviromental.drainTypeEdit.getText().toString()));
            	if (apartmentEnviromental.wasteWaterCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("apartment_waste_water", "1"));
            	}
            	if (apartmentEnviromental.spinnerCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("apartment_spinner", "1"));
            	}
            	if (apartmentEnviromental.dumpSiteCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("apartment_dump_site", "1"));
            	}
            	if (apartmentEnviromental.recycleWaterCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("apartment_recycle_water", "1"));
            	}
            	
            	// walkways
            	Walkways apartmentWalkways = apartmentBlock.apartmentWalkways;
            	params.add(new BasicNameValuePair("apartment_walkways_sqft", apartmentWalkways.walkwaysSqftEdit.getText().toString()));
            	params.add(new BasicNameValuePair("apartment_walkways_sqft_multiplier", apartmentWalkways.walkwaysMultiplierEdit.getText().toString()));
            	params.add(new BasicNameValuePair("apartment_walkways_sqft_info", apartmentWalkways.walkwaysInfoEdit.getText().toString()));
            	params.add(new BasicNameValuePair("apartment_walkways_gum", apartmentWalkways.walkwaysGumEdit.getText().toString()));
            	params.add(new BasicNameValuePair("apartment_walkways_notes", apartmentWalkways.walkwaysNoteEdit.getText().toString()));
            	params.add(new BasicNameValuePair("apartment_walkways_access_time", apartmentWalkways.walkwaysAccessTimeEdit.getText().toString()));
            	
            	// job details
            	JobDetails apartmentJobDetails = apartmentBlock.apartmentJobDetails;
            	params.add(new BasicNameValuePair("apartment_water_available", apartmentJobDetails.waterAvaliableSpinner.getSelectedItem().toString()));
            	if (apartmentJobDetails.waterColdCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("apartment_cold_water", "1"));
            	}
            	if (apartmentJobDetails.waterHotCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("apartment_hot_water", "1"));
            	}
            	if (apartmentJobDetails.waterHydrantCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("apartment_water_access_hydrant", "1"));
            	}
            	if (apartmentJobDetails.waterScafoldingCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("apartment_water_access_scafolding", "1"));
            	}
            	if (apartmentJobDetails.waterHarnessCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("apartment_water_access_harness", "1"));
            	}
            	if (apartmentJobDetails.waterParkingCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("apartment_water_access_parking", "1"));
            	}
            	if (apartmentJobDetails.waterParkingStreetCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("apartment_water_access_parking_street", "1"));
            	}
            	if (apartmentJobDetails.waterParkingLotCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("apartment_water_access_parking_lot", "1"));
            	}
            	if (apartmentJobDetails.waterLiftNeededCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("", "1"));
            	}
            	if (apartmentJobDetails.waterMildewCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("apartment_water_access_chemical_mildew", "1"));
            	}
            	if (apartmentJobDetails.waterOilCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("apartment_water_access_chemical_oil", "1"));
            	}
            	if (apartmentJobDetails.waterEnzymeMicrobesCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("apartment_water_access_chemical_enzymes", "1"));
            	}
            	if (apartmentJobDetails.waterMildewcideCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("apartment_water_access_chemical_mildewcide", "1"));
            	}
            	if (apartmentJobDetails.waterEnzymesCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("", "1"));
            	}
            	
            	// roofs
            	Roofs apartmentRoofs = apartmentBlock.apartmentRoofs;
            	params.add(new BasicNameValuePair("apartment_roofs_sqft", apartmentRoofs.roofsSqftEdit.getText().toString()));
            	params.add(new BasicNameValuePair("apartment_roofs_sqft_multiplier", apartmentRoofs.roofsSqftMultiplierEdit.getText().toString()));
            	params.add(new BasicNameValuePair("apartment_roofs_sqft_info", apartmentRoofs.roofsSqftInfoEdit.getText().toString()));
            	params.add(new BasicNameValuePair("apartment_roofs_notes", apartmentRoofs.roofsNoteEdit.getText().toString()));
            	params.add(new BasicNameValuePair("apartment_notes", apartmentRoofs.additionalNoteEdit.getText().toString()));            	            	
            	if (apartmentRoofs.roofsShakeCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("apartment_roofs_type_shake", "1"));
            	}
            	if (apartmentRoofs.roofsTileCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("apartment_roofs_type_tile", "1"));
            	}
            	if (apartmentRoofs.roofsCompositionCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("apartment_roofs_type_composition", "1"));
            	}
            	if (apartmentRoofs.roofsMetalCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("apartment_roofs_type_metal", "1"));
            	}
            }
            
            if (commericalCheckBox.isChecked()) {
            	params.add(new BasicNameValuePair("is_commercial", "1"));
            	
            	params.add(new BasicNameValuePair("commercial_sqft", commericalBlock.commericalSqftEdit.getText().toString()));
            	params.add(new BasicNameValuePair("commercial_sqft_multiplier", commericalBlock.commericalSqftMultiplierEdit.getText().toString()));
            	params.add(new BasicNameValuePair("commercial_sqft_info", commericalBlock.commericalSqftInfoEdit.getText().toString()));
            	params.add(new BasicNameValuePair("commercial_buildings", commericalBlock.commericalBuildingEdit.getText().toString()));
            	params.add(new BasicNameValuePair("commercial_buildings_multiplier", commericalBlock.commericalBuildingMultiplierEdit.getText().toString()));
            	params.add(new BasicNameValuePair("commercial_buildings_info", commericalBlock.commericalBuildingInfoEdit.getText().toString()));
            	params.add(new BasicNameValuePair("commercial_units", commericalBlock.commericalUnitsEdit.getText().toString()));
            	params.add(new BasicNameValuePair("commercial_units_multiplier", commericalBlock.commericalUnitsMultiplierEdit.getText().toString()));
            	params.add(new BasicNameValuePair("commercial_units_info", commericalBlock.commericalUnitsInfoEdit.getText().toString()));
            	params.add(new BasicNameValuePair("commercial_stories", commericalBlock.commericalStoriesEdit.getText().toString()));
            	params.add(new BasicNameValuePair("commercial_railings", commericalBlock.commericalRailingsEdit.getText().toString()));            	            	
            	
            	// siding type
            	SidingType commericalSidingType = commericalBlock.commericalSidingType; 
            	if (commericalSidingType.stuccoCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("commercial_type_of_siding_stucco", "1"));
            	}
            	if (commericalSidingType.vinylCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("commercial_type_of_siding_vinyl", "1"));
            	}
            	if (commericalSidingType.hardyCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("commercial_type_of_siding_hardy", "1"));
            	}
            	if (commericalSidingType.brickCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("commercial_type_of_siding_brick", "1"));
            	}
            	if (commericalSidingType.woodCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("commercial_type_of_siding_wood", "1"));
            	}
            	if (commericalSidingType.concreteCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("commercial_type_of_siding_concrete", "1"));
            	}
            	if (commericalSidingType.metalCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("commercial_type_of_siding_metal", "1"));
            	}
            	
            	// lift needed
            	LiftNeeded commericalLiftNeeded = commericalBlock.commericalLiftNeeded;
            	if (commericalLiftNeeded.liftNeededCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("commercial_lifts_needed", "1"));
            	}
            	params.add(new BasicNameValuePair("commercial_lifts_needed_multiplier", commericalLiftNeeded.amountEdit.getText().toString()));
            	params.add(new BasicNameValuePair("commercial_lift_needed_days", commericalLiftNeeded.daysNeededEdit.getText().toString()));
            	
            	// environmental
            	Environmental commericalEnviromental = commericalBlock.commericalEnvironmental;
            	params.add(new BasicNameValuePair("commercial_type_of_drains", commericalEnviromental.drainTypeEdit.getText().toString()));
            	if (commericalEnviromental.wasteWaterCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("commercial_waste_water", "1"));
            	}
            	if (commericalEnviromental.spinnerCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("commercial_spinner", "1"));
            	}
            	if (commericalEnviromental.dumpSiteCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("commercial_dump_site", "1"));
            	}
            	if (commericalEnviromental.recycleWaterCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("commercial_recycle_water", "1"));
            	}
            	
            	// walkways
            	Walkways commericalWalkways = commericalBlock.commericalWalkways;
            	params.add(new BasicNameValuePair("commercial_walkways_sqft", commericalWalkways.walkwaysSqftEdit.getText().toString()));
            	params.add(new BasicNameValuePair("commercial_walkways_sqft_multiplier", commericalWalkways.walkwaysMultiplierEdit.getText().toString()));
            	params.add(new BasicNameValuePair("commercial_walkways_sqft_info", commericalWalkways.walkwaysInfoEdit.getText().toString()));
            	params.add(new BasicNameValuePair("commercial_gum", commericalWalkways.walkwaysGumEdit.getText().toString()));
            	params.add(new BasicNameValuePair("commercial_walkways_notes", commericalWalkways.walkwaysNoteEdit.getText().toString()));
            	params.add(new BasicNameValuePair("commercial_walkways_access_time", commericalWalkways.walkwaysAccessTimeEdit.getText().toString()));
            	
            	// job details
            	JobDetails commericalJobDetails = commericalBlock.commericalJobDetails;
            	params.add(new BasicNameValuePair("commercial_water_available", commericalJobDetails.waterAvaliableSpinner.getSelectedItem().toString()));
            	if (commericalJobDetails.waterColdCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("commercial_cold_water", "1"));
            	}
            	if (commericalJobDetails.waterHotCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("commercial_hot_water", "1"));
            	}
            	if (commericalJobDetails.waterHydrantCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("commercial_access_hydrant", "1"));
            	}
            	if (commericalJobDetails.waterScafoldingCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("commercial_access_scafolding", "1"));
            	}
            	if (commericalJobDetails.waterHarnessCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("commercial_access_harness", "1"));
            	}
            	if (commericalJobDetails.waterParkingCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("commercial_access_parking", "1"));
            	}
            	if (commericalJobDetails.waterParkingStreetCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("commercial_access_parking_street", "1"));
            	}
            	if (commericalJobDetails.waterParkingLotCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("commercial_access_parking_lot", "1"));
            	}
//            	if (commericalJobDetails.waterLiftNeededCheckBox.isChecked()) {
//                	params.add(new BasicNameValuePair("", "1"));
//            	}
            	if (commericalJobDetails.waterMildewCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("commercial_access_chemical_mildew", "1"));
            	}
            	if (commericalJobDetails.waterOilCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("commercial_access_chemical_oil", "1"));
            	}
            	if (commericalJobDetails.waterEnzymeMicrobesCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("commercial_access_chemical_enzymes", "1"));
            	}
            	if (commericalJobDetails.waterMildewcideCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("commercial_access_chemical_mildewcide", "1"));
            	}
//            	if (commericalJobDetails.waterEnzymesCheckBox.isChecked()) {
//                	params.add(new BasicNameValuePair("", "1"));
//            	}
            	
            	// roofs
            	Roofs commericalRoofs = commericalBlock.commericalRoofs;
            	params.add(new BasicNameValuePair("commercial_roofs_sqft", commericalRoofs.roofsSqftEdit.getText().toString()));
            	params.add(new BasicNameValuePair("commercial_roofs_sqft_multiplier", commericalRoofs.roofsSqftMultiplierEdit.getText().toString()));
            	params.add(new BasicNameValuePair("commercial_roofs_sqft_info", commericalRoofs.roofsSqftInfoEdit.getText().toString()));
            	params.add(new BasicNameValuePair("commercial_roofs_notes", commericalRoofs.roofsNoteEdit.getText().toString()));
            	params.add(new BasicNameValuePair("commercial_notes", commericalRoofs.additionalNoteEdit.getText().toString()));            	            	
            	if (commericalRoofs.roofsShakeCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("commercial_type_of_roofs_shake", "1"));
            	}
            	if (commericalRoofs.roofsTileCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("commercial_type_of_roofs_tile", "1"));
            	}
            	if (commericalRoofs.roofsCompositionCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("commercial_type_of_roofs_composition", "1"));
            	}            	
            }
            
            if (parkingCheckBox.isChecked()) {
            	params.add(new BasicNameValuePair("is_parking", "1"));
            	
            	params.add(new BasicNameValuePair("parking_lot_sqft", parkingBlock.lotSqftEdit.getText().toString()));
            	params.add(new BasicNameValuePair("parking_lot_sqft_multiplier", parkingBlock.lotSqftMultiplierEdit.getText().toString()));
            	params.add(new BasicNameValuePair("parking_lot_sqft_info", parkingBlock.lotSqftInfoEdit.getText().toString()));
            	params.add(new BasicNameValuePair("parking_lot_stalls", parkingBlock.lotStallsEdit.getText().toString()));
            	params.add(new BasicNameValuePair("parking_lot_stalls_multiplier", parkingBlock.lotStallsMultiplierEdit.getText().toString()));
            	params.add(new BasicNameValuePair("parking_lot_stalls_info", parkingBlock.lotStallsInfoEdit.getText().toString()));
            	params.add(new BasicNameValuePair("parking_lot_storms", parkingBlock.lotDrainsEdit.getText().toString()));
            	params.add(new BasicNameValuePair("parking_lot_curb", parkingBlock.lotCurbEdit.getText().toString()));
            	params.add(new BasicNameValuePair("parking_lot_curb_multiplier", parkingBlock.lotCurbMultiplierEdit.getText().toString()));
            	params.add(new BasicNameValuePair("parking_lot_curb_info", parkingBlock.lotCurbInfoEdit.getText().toString()));
            	params.add(new BasicNameValuePair("parking_garage_sqft", parkingBlock.garageSqftEdit.getText().toString()));
            	params.add(new BasicNameValuePair("parking_garage_sqft_multiplier", parkingBlock.garageSqftMultiplierEdit.getText().toString()));
            	params.add(new BasicNameValuePair("parking_garage_sqft_info", parkingBlock.garageSqftInfoEdit.getText().toString()));
            	params.add(new BasicNameValuePair("parking_garage_stalls", parkingBlock.garageStallsEdit.getText().toString()));
            	params.add(new BasicNameValuePair("parking_garage_stalls_multiplier", parkingBlock.garageStallsMultiplierEdit.getText().toString()));
            	params.add(new BasicNameValuePair("parking_garage_stalls_info", parkingBlock.garageStallsInfoEdit.getText().toString()));
            	params.add(new BasicNameValuePair("parking_garage_walls", parkingBlock.garageWallsEdit.getText().toString()));
            	params.add(new BasicNameValuePair("parking_garage_walls_multiplier", parkingBlock.garageWallsMultiplierEdit.getText().toString()));
            	params.add(new BasicNameValuePair("parking_garage_walls_info", parkingBlock.garageWallsInfoEdit.getText().toString()));
            	params.add(new BasicNameValuePair("parking_garage_stalls_railings", parkingBlock.garageRailingsEdit.getText().toString()));
            	params.add(new BasicNameValuePair("parking_garage_floors", parkingBlock.garageFloorsEdit.getText().toString()));
            	params.add(new BasicNameValuePair("parking_garage_stairs", parkingBlock.garageStairsEdit.getText().toString()));
            	params.add(new BasicNameValuePair("parking_garage_stairs_multiplier", parkingBlock.garageStairsMultiplierEdit.getText().toString()));
            	params.add(new BasicNameValuePair("parking_garage_stairs_info", parkingBlock.garageStairsInfoEdit.getText().toString()));
            	params.add(new BasicNameValuePair("parking_garage_ceiling", parkingBlock.garageCeilingEdit.getText().toString()));
            	params.add(new BasicNameValuePair("parking_garage_ceiling_info", parkingBlock.garageCeilingInfoEdit.getText().toString()));            	
            	            	            	                        	
            	// environmental
            	Environmental parkingEnvironmental = parkingBlock.parkingEnvironmental;             	
            	params.add(new BasicNameValuePair("parking_type_of_drains", parkingEnvironmental.drainTypeEdit.getText().toString()));
            	if (parkingEnvironmental.wasteWaterCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("parking_waste_water", "1"));
            	}
            	if (parkingEnvironmental.spinnerCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("parking_spineer", "1"));
            	}
            	if (parkingEnvironmental.dumpSiteCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("parking_dump_site", "1"));
            	}
            	if (parkingEnvironmental.recycleWaterCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("parking_recycle", "1"));
            	}
            	
            	// walkways
            	Walkways parkingWalkways = parkingBlock.parkingWalkways;
            	params.add(new BasicNameValuePair("parking_walkways_sqft", parkingWalkways.walkwaysSqftEdit.getText().toString()));
            	params.add(new BasicNameValuePair("parking_walkways_sqft_multiplier", parkingWalkways.walkwaysMultiplierEdit.getText().toString()));
            	params.add(new BasicNameValuePair("parking_walkways_sqft_info", parkingWalkways.walkwaysInfoEdit.getText().toString()));
            	params.add(new BasicNameValuePair("parking_gum", parkingWalkways.walkwaysGumEdit.getText().toString()));
            	params.add(new BasicNameValuePair("parking_walkways_notes", parkingWalkways.walkwaysNoteEdit.getText().toString()));
            	params.add(new BasicNameValuePair("parking_walkways_access_time", parkingWalkways.walkwaysAccessTimeEdit.getText().toString()));
            	
            	// job details
            	JobDetails parkingJobDetails = parkingBlock.parkingJobDetails;
            	params.add(new BasicNameValuePair("parking_water_available", parkingJobDetails.waterAvaliableSpinner.getSelectedItem().toString()));
            	if (parkingJobDetails.waterColdCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("parking_cold_water", "1"));
            	}
            	if (parkingJobDetails.waterHotCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("parking_hot_water", "1"));
            	}
            	if (parkingJobDetails.waterHydrantCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("parking_access_hydrant", "1"));
            	}
            	if (parkingJobDetails.waterScafoldingCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("parking_access_scafolding", "1"));
            	}
            	if (parkingJobDetails.waterHarnessCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("parking_access_harness", "1"));
            	}
            	if (parkingJobDetails.waterParkingCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("parking_access_parking", "1"));
            	}
            	if (parkingJobDetails.waterParkingStreetCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("parking_access_parking_street", "1"));
            	}
            	if (parkingJobDetails.waterParkingLotCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("parking_access_parking_lot", "1"));
            	}
            	if (parkingJobDetails.waterLiftNeededCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("parking_access_lift_needed", "1"));
            	}
            	if (parkingJobDetails.waterMildewCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("parking_access_chemical_mildew", "1"));
            	}
            	if (parkingJobDetails.waterOilCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("parking_access_chemical_oil", "1"));
            	}
            	if (parkingJobDetails.waterEnzymeMicrobesCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("parking_access_chemical_microbes", "1"));
            	}
            	if (parkingJobDetails.waterMildewcideCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("parking_access_chemical_mildewcide", "1"));
            	}
            	if (parkingJobDetails.waterEnzymesCheckBox.isChecked()) {
                	params.add(new BasicNameValuePair("parking_access_chemical_enzymes", "1"));
            	}       
            	params.add(new BasicNameValuePair("parking_notes", parkingJobDetails.additionalNoteInJobEdit.getText().toString()));
            }
            
            if (miscellaneousCheckBox.isChecked()) {
            	params.add(new BasicNameValuePair("is_miscellaneous", "1"));
            	
            	params.add(new BasicNameValuePair("miscellaneous_value1", miscellaneousBlock.valueTableRow1.getValue()));
            	params.add(new BasicNameValuePair("miscellaneous_multiplier1", miscellaneousBlock.valueTableRow1.getMultiplier()));
            	params.add(new BasicNameValuePair("miscellaneous_info1", miscellaneousBlock.valueTableRow1.getInfo()));
            	params.add(new BasicNameValuePair("miscellaneous_type1", miscellaneousBlock.valueTableRow1.getType()));
            	
            	params.add(new BasicNameValuePair("miscellaneous_value2", miscellaneousBlock.valueTableRow2.getValue()));
            	params.add(new BasicNameValuePair("miscellaneous_multiplier2", miscellaneousBlock.valueTableRow2.getMultiplier()));
            	params.add(new BasicNameValuePair("miscellaneous_info2", miscellaneousBlock.valueTableRow2.getInfo()));
            	params.add(new BasicNameValuePair("miscellaneous_type2", miscellaneousBlock.valueTableRow2.getType()));
            	
            	params.add(new BasicNameValuePair("miscellaneous_value3", miscellaneousBlock.valueTableRow3.getValue()));
            	params.add(new BasicNameValuePair("miscellaneous_multiplier3", miscellaneousBlock.valueTableRow3.getMultiplier()));
            	params.add(new BasicNameValuePair("miscellaneous_info3", miscellaneousBlock.valueTableRow3.getInfo()));
            	params.add(new BasicNameValuePair("miscellaneous_type3", miscellaneousBlock.valueTableRow3.getType()));
            	
            	params.add(new BasicNameValuePair("miscellaneous_value4", miscellaneousBlock.valueTableRow4.getValue()));
            	params.add(new BasicNameValuePair("miscellaneous_multiplier4", miscellaneousBlock.valueTableRow4.getMultiplier()));
            	params.add(new BasicNameValuePair("miscellaneous_info4", miscellaneousBlock.valueTableRow4.getInfo()));
            	params.add(new BasicNameValuePair("miscellaneous_type4", miscellaneousBlock.valueTableRow4.getType()));
            	
            	params.add(new BasicNameValuePair("miscellaneous_value5", miscellaneousBlock.valueTableRow5.getValue()));
            	params.add(new BasicNameValuePair("miscellaneous_multiplier5", miscellaneousBlock.valueTableRow5.getMultiplier()));
            	params.add(new BasicNameValuePair("miscellaneous_info5", miscellaneousBlock.valueTableRow5.getInfo()));
            	params.add(new BasicNameValuePair("miscellaneous_type5", miscellaneousBlock.valueTableRow5.getType()));
            	
            	params.add(new BasicNameValuePair("miscellaneous_value6", miscellaneousBlock.valueTableRow6.getValue()));
            	params.add(new BasicNameValuePair("miscellaneous_multiplier6", miscellaneousBlock.valueTableRow6.getMultiplier()));
            	params.add(new BasicNameValuePair("miscellaneous_info6", miscellaneousBlock.valueTableRow6.getInfo()));
            	params.add(new BasicNameValuePair("miscellaneous_type6", miscellaneousBlock.valueTableRow6.getType()));
            	
            	params.add(new BasicNameValuePair("miscellaneous_value7", miscellaneousBlock.valueTableRow7.getValue()));
            	params.add(new BasicNameValuePair("miscellaneous_multiplier7", miscellaneousBlock.valueTableRow7.getMultiplier()));
            	params.add(new BasicNameValuePair("miscellaneous_info7", miscellaneousBlock.valueTableRow7.getInfo()));
            	params.add(new BasicNameValuePair("miscellaneous_type7", miscellaneousBlock.valueTableRow7.getType()));
            	
            	params.add(new BasicNameValuePair("miscellaneous_value8", miscellaneousBlock.valueTableRow8.getValue()));
            	params.add(new BasicNameValuePair("miscellaneous_multiplier8", miscellaneousBlock.valueTableRow8.getMultiplier()));
            	params.add(new BasicNameValuePair("miscellaneous_info8", miscellaneousBlock.valueTableRow8.getInfo()));
            	params.add(new BasicNameValuePair("miscellaneous_type8", miscellaneousBlock.valueTableRow8.getType()));
            	
            	params.add(new BasicNameValuePair("miscellaneous_value9", miscellaneousBlock.valueTableRow9.getValue()));
            	params.add(new BasicNameValuePair("miscellaneous_multiplier9", miscellaneousBlock.valueTableRow9.getMultiplier()));
            	params.add(new BasicNameValuePair("miscellaneous_info9", miscellaneousBlock.valueTableRow9.getInfo()));
            	params.add(new BasicNameValuePair("miscellaneous_type9", miscellaneousBlock.valueTableRow9.getType()));
            	
            	params.add(new BasicNameValuePair("miscellaneous_value10", miscellaneousBlock.valueTableRow10.getValue()));
            	params.add(new BasicNameValuePair("miscellaneous_multiplier10", miscellaneousBlock.valueTableRow10.getMultiplier()));
            	params.add(new BasicNameValuePair("miscellaneous_info10", miscellaneousBlock.valueTableRow10.getInfo()));
            	params.add(new BasicNameValuePair("miscellaneous_type10", miscellaneousBlock.valueTableRow10.getType()));
            	
            	params.add(new BasicNameValuePair("miscellaneous_value11", miscellaneousBlock.valueTableRow11.getValue()));
            	params.add(new BasicNameValuePair("miscellaneous_multiplier11", miscellaneousBlock.valueTableRow11.getMultiplier()));
            	params.add(new BasicNameValuePair("miscellaneous_info11", miscellaneousBlock.valueTableRow11.getInfo()));
            	params.add(new BasicNameValuePair("miscellaneous_type11", miscellaneousBlock.valueTableRow11.getType()));
            	
            	params.add(new BasicNameValuePair("miscellaneous_value12", miscellaneousBlock.valueTableRow12.getValue()));
            	params.add(new BasicNameValuePair("miscellaneous_multiplier12", miscellaneousBlock.valueTableRow12.getMultiplier()));
            	params.add(new BasicNameValuePair("miscellaneous_info12", miscellaneousBlock.valueTableRow12.getInfo()));
            	params.add(new BasicNameValuePair("miscellaneous_type12", miscellaneousBlock.valueTableRow12.getType()));
            	
            	params.add(new BasicNameValuePair("miscellaneous_value13", miscellaneousBlock.valueTableRow13.getValue()));
            	params.add(new BasicNameValuePair("miscellaneous_multiplier13", miscellaneousBlock.valueTableRow13.getMultiplier()));
            	params.add(new BasicNameValuePair("miscellaneous_info13", miscellaneousBlock.valueTableRow13.getInfo()));
            	params.add(new BasicNameValuePair("miscellaneous_type13", miscellaneousBlock.valueTableRow13.getType()));
            	
            	params.add(new BasicNameValuePair("miscellaneous_value14", miscellaneousBlock.valueTableRow14.getValue()));
            	params.add(new BasicNameValuePair("miscellaneous_multiplier14", miscellaneousBlock.valueTableRow14.getMultiplier()));
            	params.add(new BasicNameValuePair("miscellaneous_info14", miscellaneousBlock.valueTableRow14.getInfo()));
            	params.add(new BasicNameValuePair("miscellaneous_type14", miscellaneousBlock.valueTableRow14.getType()));
            	
            	params.add(new BasicNameValuePair("miscellaneous_value15", miscellaneousBlock.valueTableRow15.getValue()));
            	params.add(new BasicNameValuePair("miscellaneous_multiplier15", miscellaneousBlock.valueTableRow15.getMultiplier()));
            	params.add(new BasicNameValuePair("miscellaneous_info15", miscellaneousBlock.valueTableRow15.getInfo()));
            	params.add(new BasicNameValuePair("miscellaneous_type15", miscellaneousBlock.valueTableRow15.getType()));
            	
            	params.add(new BasicNameValuePair("miscellaneous_value16", miscellaneousBlock.valueTableRow16.getValue()));
            	params.add(new BasicNameValuePair("miscellaneous_multiplier16", miscellaneousBlock.valueTableRow16.getMultiplier()));
            	params.add(new BasicNameValuePair("miscellaneous_info16", miscellaneousBlock.valueTableRow16.getInfo()));
            	params.add(new BasicNameValuePair("miscellaneous_type16", miscellaneousBlock.valueTableRow16.getType()));
            	
            	params.add(new BasicNameValuePair("miscellaneous_value17", miscellaneousBlock.valueTableRow17.getValue()));
            	params.add(new BasicNameValuePair("miscellaneous_multiplier17", miscellaneousBlock.valueTableRow17.getMultiplier()));
            	params.add(new BasicNameValuePair("miscellaneous_info17", miscellaneousBlock.valueTableRow17.getInfo()));
            	params.add(new BasicNameValuePair("miscellaneous_type17", miscellaneousBlock.valueTableRow17.getType()));
            	
            	params.add(new BasicNameValuePair("miscellaneous_value18", miscellaneousBlock.valueTableRow18.getValue()));
            	params.add(new BasicNameValuePair("miscellaneous_multiplier18", miscellaneousBlock.valueTableRow18.getMultiplier()));
            	params.add(new BasicNameValuePair("miscellaneous_info18", miscellaneousBlock.valueTableRow18.getInfo()));
            	params.add(new BasicNameValuePair("miscellaneous_type18", miscellaneousBlock.valueTableRow18.getType()));
            	
            	params.add(new BasicNameValuePair("miscellaneous_value19", miscellaneousBlock.valueTableRow19.getValue()));
            	params.add(new BasicNameValuePair("miscellaneous_multiplier19", miscellaneousBlock.valueTableRow19.getMultiplier()));
            	params.add(new BasicNameValuePair("miscellaneous_info19", miscellaneousBlock.valueTableRow19.getInfo()));
            	params.add(new BasicNameValuePair("miscellaneous_type19", miscellaneousBlock.valueTableRow19.getType()));
            	
            	params.add(new BasicNameValuePair("miscellaneous_value20", miscellaneousBlock.valueTableRow20.getValue()));
            	params.add(new BasicNameValuePair("miscellaneous_multiplier20", miscellaneousBlock.valueTableRow20.getMultiplier()));
            	params.add(new BasicNameValuePair("miscellaneous_info20", miscellaneousBlock.valueTableRow20.getInfo()));
            	params.add(new BasicNameValuePair("miscellaneous_type20", miscellaneousBlock.valueTableRow20.getType()));
            	
            	params.add(new BasicNameValuePair("miscellaneous_notes", miscellaneousBlock.miscellaneousNoteEdit.getText().toString()));            	            
            }         
            
            // equipment                    	
        	if (equipmentBlock.truck1CheckBox.isChecked()) {
            	params.add(new BasicNameValuePair("equipment_truck_1", "1"));
        	}
        	if (equipmentBlock.truck2CheckBox.isChecked()) {
            	params.add(new BasicNameValuePair("equipment_truck_2", "1"));
        	}
        	if (equipmentBlock.truck3CheckBox.isChecked()) {
            	params.add(new BasicNameValuePair("equipment_truck_3", "1"));
        	}
        	if (equipmentBlock.truck4CheckBox.isChecked()) {
            	params.add(new BasicNameValuePair("equipment_truck_4", "1"));
        	}
        	if (equipmentBlock.truck5CheckBox.isChecked()) {
            	params.add(new BasicNameValuePair("equipment_truck_5", "1"));
        	}
        	if (equipmentBlock.truck6CheckBox.isChecked()) {
            	params.add(new BasicNameValuePair("equipment_truck_6", "1"));
        	}
        	if (equipmentBlock.largeTrailerCheckBox.isChecked()) {
            	params.add(new BasicNameValuePair("equipment_larger_trailer", "1"));
        	}
        	if (equipmentBlock.smallTrailerCheckBox.isChecked()) {
            	params.add(new BasicNameValuePair("equipment_small_trailer", "1"));
        	}
        	if (equipmentBlock.nozzleTipsCheckBox.isChecked()) {
            	params.add(new BasicNameValuePair("equipment_nozzle_tips", "1"));
        	}
        	if (equipmentBlock.turboNozzleCheckBox.isChecked()) {
            	params.add(new BasicNameValuePair("equipment_turbo_nozzle", "1"));
        	}
        	if (equipmentBlock.cycloneCheckBox.isChecked()) {
            	params.add(new BasicNameValuePair("equipment_cyclone", "1"));
        	}
        	if (equipmentBlock.safetyVestCheckBox.isChecked()) {
            	params.add(new BasicNameValuePair("equipment_safety_vest", "1"));
        	}
        	if (equipmentBlock.wand4ftCheckBox.isChecked()) {
            	params.add(new BasicNameValuePair("equipment_wand_4ft", "1"));
        	}
        	if (equipmentBlock.wand8ftCheckBox.isChecked()) {
            	params.add(new BasicNameValuePair("equipment_wand_8ft", "1"));
        	}
        	if (equipmentBlock.ropeGrabberCheckBox.isChecked()) {
            	params.add(new BasicNameValuePair("equipment_rope_grabber", "1"));
        	}
        	if (equipmentBlock.ropeCheckBox.isChecked()) {
            	params.add(new BasicNameValuePair("equipment_rope", "1"));
        	}
        	if (equipmentBlock.paperWorkCheckBox.isChecked()) {
            	params.add(new BasicNameValuePair("equipment_paper_work", "1"));
        	}
        	if (equipmentBlock.gateKeyCheckBox.isChecked()) {
            	params.add(new BasicNameValuePair("equipment_gate_key", "1"));
        	}
        	if (equipmentBlock.drainCoverCheckBox.isChecked()) {
            	params.add(new BasicNameValuePair("equipment_drain_cover", "1"));
        	}
        	if (equipmentBlock.snakesCheckBox.isChecked()) {
            	params.add(new BasicNameValuePair("equipment_snakes", "1"));
        	}
        	if (equipmentBlock.squeegyCheckBox.isChecked()) {
            	params.add(new BasicNameValuePair("equipment_squeegy", "1"));
        	}
        	if (equipmentBlock.shovelCheckBox.isChecked()) {
            	params.add(new BasicNameValuePair("equipment_shovel", "1"));
        	}
        	if (equipmentBlock.rakeCheckBox.isChecked()) {
            	params.add(new BasicNameValuePair("equipment_rake", "1"));
        	}
        	if (equipmentBlock.garbageCanCheckBox.isChecked()) {
            	params.add(new BasicNameValuePair("equipment_garbage_can", "1"));
        	}
        	if (equipmentBlock.ladderCheckBox.isChecked()) {
            	params.add(new BasicNameValuePair("equipment_ladder", "1"));
        	}
        	if (equipmentBlock.sandwichBoardCheckBox.isChecked()) {
            	params.add(new BasicNameValuePair("equipment_drain_a_board", "1"));
        	}
        	if (equipmentBlock.steelEagleSpinnerCheckBox.isChecked()) {
            	params.add(new BasicNameValuePair("equipment_regular_spinner", "1"));
        	}
        	if (equipmentBlock.smallVacSpinnerCheckBox.isChecked()) {
            	params.add(new BasicNameValuePair("equipment_small_vac_spinner", "1"));
        	}
        	if (equipmentBlock.largeVacSpinnerCheckBox.isChecked()) {
            	params.add(new BasicNameValuePair("equipment_large_vac_spinner", "1"));
        	}
        	if (equipmentBlock.wetSidewalkSsignCheckBox.isChecked()) {
            	params.add(new BasicNameValuePair("equipment_wet_sidewalk_sign", "1"));
        	}
        	if (equipmentBlock.bigPumpCheckBox.isChecked()) {
            	params.add(new BasicNameValuePair("equipment_big_pump", "1"));
        	}
        	if (equipmentBlock.floatingSpinnerCheckBox.isChecked()) {
            	params.add(new BasicNameValuePair("equipment_floating_spinner", "1"));
        	}
        	if (equipmentBlock.fiberglassExtension18CheckBox.isChecked()) {
            	params.add(new BasicNameValuePair("equipment_fiberglass_18", "1"));
        	}
        	if (equipmentBlock.fiberglassExtension24CheckBox.isChecked()) {
            	params.add(new BasicNameValuePair("equipment_fiberglass_24", "1"));
        	}
        	if (equipmentBlock.safetyHarnessLanyardCheckBox.isChecked()) {
            	params.add(new BasicNameValuePair("equipment_safety_harness", "1"));
        	}
        	if (equipmentBlock.hatGlassesGearCheckBox.isChecked()) {
            	params.add(new BasicNameValuePair("equipment_hat_safety_glasses_rain_gear", "1"));
        	}
        	params.add(new BasicNameValuePair("equipment_extra_psi_hose", equipmentBlock.extraPsiHoseEdit.getText().toString()));
        	params.add(new BasicNameValuePair("equipment_extra_water_hose", equipmentBlock.extraWaterHoseEdit.getText().toString()));
        	        	
            try {
	            // getting JSON Object
	            // Note that create pdf url accepts POST method
	            JSONObject json = jsonParser.makeHttpRequest(url_create_pdf, "POST", params);
	 
	            if (json != null) {
		            // check log cat for response
		            Log.d("Create Response", json.toString());
	 
		            // check for success tag            	            
	                int success = json.getInt(TAG_SUCCESS);	                
	 
	                if (success == 1) {                    
	                    // closing this screen
	                    // finish();
	                    
	                	int projectId = json.getInt(TAG_PROJECT_ID);	                	
	                	pdfLink = json.getString(TAG_PDF_LINK);
	                	
	                	List<NameValuePair> reportParam = new ArrayList<NameValuePair>();
	                	reportParam.add(new BasicNameValuePair("vf_id_project", Integer.toString(projectId)));
	                    
	                	JSONObject jsonReport = new JSONParser().makeHttpRequest(url_report_pdf, "POST", reportParam);
	                	if (jsonReport != null) {	                			                                                                             
		                    Intent shareActivity = new Intent(CreateNewProjectActivity.this, CreateProjectSuccessActivity.class);
		            		shareActivity.putExtra("PDF_LINK", pdfLink);
		            		startActivity(shareActivity);
	                	}
	                } else {
	                	errorMessage = json.getString(TAG_ERROR);
	                	if (errorMessage != null) {
	                		errorMessage = errorMessage.replace("<br>", "\r\n");
	                	}
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
            // dismiss the dialog once done
            progressDialog.dismiss();
            if (pdfLink == null || pdfLink.isEmpty()) {            	
            	new AlertDialog.Builder(CreateNewProjectActivity.this)
				    .setTitle("Error")
				    .setMessage(errorMessage)
				    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
				        public void onClick(DialogInterface dialog, int which) { 
				            // continue with delete
				        }
				     })		    
				    .setIcon(android.R.drawable.ic_dialog_alert)
				    .show();            	                        	
            }
        }
    }
    
    private boolean checkForm() {
		boolean isValid = true;
		
		if (projectNameEdit.getText().toString().isEmpty()) {
			projectNameEdit.setError(getResources().getString(R.string.required_field_empty_error));			
			isValid = false;
		}
		
		if (firstContactFirstNameEdit.getText().toString().isEmpty()) {
			firstContactFirstNameEdit.setError(getResources().getString(R.string.required_field_empty_error));			
			isValid = false;
		}
		
		if (firstContactLastNameEdit.getText().toString().isEmpty()) {
			firstContactLastNameEdit.setError(getResources().getString(R.string.required_field_empty_error));			
			isValid = false;
		}
		
		if (firstContactTitleEdit.getText().toString().isEmpty()) {
			firstContactTitleEdit.setError(getResources().getString(R.string.required_field_empty_error));			
			isValid = false;
		}
		
		if (firstContactPhoneEdit.getText().toString().isEmpty()) {
			firstContactPhoneEdit.setError(getResources().getString(R.string.required_field_empty_error));			
			isValid = false;
		}
		
		if (emailEdit.getText().toString().isEmpty()) {
			emailEdit.setError(getResources().getString(R.string.required_field_empty_error));			
			isValid = false;
		}
		
		if (addressEdit.getText().toString().isEmpty()) {
			addressEdit.setError(getResources().getString(R.string.required_field_empty_error));			
			isValid = false;
		}
		
		if (cityEdit.getText().toString().isEmpty()) {
			cityEdit.setError(getResources().getString(R.string.required_field_empty_error));			
			isValid = false;
		}
		
		if (zipCodeEdit.getText().toString().isEmpty()) {
			zipCodeEdit.setError(getResources().getString(R.string.required_field_empty_error));			
			isValid = false;
		}
		
		if (websiteEdit.getText().toString().isEmpty()) {
			websiteEdit.setError(getResources().getString(R.string.required_field_empty_error));			
			isValid = false;
		}
						
		
		if (writerEdit.getText().toString().isEmpty()) {
			writerEdit.setError(getResources().getString(R.string.required_field_empty_error));			
			isValid = false;
		}
		
		if (proposalTitleEdit.getText().toString().isEmpty()) {
			proposalTitleEdit.setError(getResources().getString(R.string.required_field_empty_error));			
			isValid = false;
		}				
		
		if (proposalPhoneEdit.getText().toString().isEmpty()) {
			proposalPhoneEdit.setError(getResources().getString(R.string.required_field_empty_error));			
			isValid = false;
		}				
		
		if (proposalEmailEdit.getText().toString().isEmpty()) {
			proposalEmailEdit.setError(getResources().getString(R.string.required_field_empty_error));			
			isValid = false;
		}				
		
		if (quoteUntilEdit.getText().toString().isEmpty()) {
			quoteUntilEdit.setError(getResources().getString(R.string.required_field_empty_error));			
			isValid = false;
		}		
		
		return isValid;
	}  
    
    private void loadValues() {    	
//    	projectNameEdit.setText("project11");
//    	firstContactFirstNameEdit.setText("first");
//    	firstContactLastNameEdit.setText("last");
//    	firstContactTitleEdit.setText("title");
//    	firstContactPhoneEdit.setText("123456789");
//    	secondContactNameEdit.setText("secondName");
//    	secondContactPhoneEdit.setText("123456789");
//    	faxEdit.setText("123456789");
//    	emailEdit.setText("email@gmail.com");
//    	addressEdit.setText("address");
//    	cityEdit.setText("cityEdit");    
//    	zipCodeEdit.setText("12345");    
//    	billingAddressEdit.setText("billingAddress");
//    	billingCityEdit.setText("billingCity");   
//    	billingZipCodeEdit.setText("5678");
//    	websiteEdit.setText("www.google.com");
//    	clientSampleEdit.setText("clientSampleEdit");
//    	tenantNoticeEdit.setText("tenantNoticeEdit");
//    	grantedAccessEdit.setText("grantedAccessEdit");
//    	instructionEdit.setText("instructionEdit");
//    	
//    	writerEdit.setText("writer");
//    	proposalTitleEdit.setText("proposalTitle");
//    	proposalPhoneEdit.setText("56787855");
//    	proposalEmailEdit.setText("proposal@gmail.com");
//    	quoteUntilEdit.setText("2014.04.03");    	    	    	    	  
    }
    
    public void onBillingAddressClick(View view) {		
		if (billingAddressCheckBox.isChecked()) {
			billingAddressEdit.setText(addressEdit.getText().toString());
	    	billingCityEdit.setText(cityEdit.getText().toString());   
	    	billingZipCodeEdit.setText(zipCodeEdit.getText().toString());
	    	billingStateSpinner.setSelection(stateSpinner.getSelectedItemPosition());
		} 
	}
    
    public void onSitemapImageBrowseClick(View view) {
    	Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Complete action using"), 200);
    }
    
    public void onSitemapImageCameraClick(View view) {
    	Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE); 
        startActivityForResult(cameraIntent, 201); 
    }
    
    public void onImageBrowseClick(View view, int requestCode) {
    	Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Complete action using"), requestCode);
    }
    
    public void onImageCameraClick(View view, int requstCode) {
    	Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE); 
        startActivityForResult(cameraIntent, requstCode); 
    }
    
    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (resultCode == RESULT_OK) {
    		if (requestCode == 200) {
    			Uri selectedImageUri = data.getData();
                String imagePath = getPath(selectedImageUri);
                if (imagePath == null || imagePath.isEmpty()) {
                	sitemapImageText.setText(getResources().getString(R.string.no_image_selected));
                } else {
                	sitemapImageText.setText(imagePath);
                }       
    		} else if (requestCode == 201) {
    			siteImageBitmap = (Bitmap) data.getExtras().get("data");
                sitemapImageText.setText(getResources().getString(R.string.taken_a_photo));
    		} else {
    			if (requestCode >= GALLERY_REQUEST_CODE_START && requestCode < GALLERY_REQUEST_CODE_START + PICTURE_TABLE_ROW_COUNT) {
    				int index = requestCode - GALLERY_REQUEST_CODE_START;
    				// gallery
    				Uri selectedImageUri = data.getData();
                    String imagePath = getPath(selectedImageUri);
                    String[] pathSpilits = imagePath.split("/");
                    String imageName = pathSpilits[pathSpilits.length - 1];
                    if (imagePath == null || imagePath.isEmpty()) {
                    	pictureBlock.pictureTableRows[index].picturePathText.setText(getResources().getString(R.string.no_image_selected));
                    } else {
                    	pictureBlock.pictureTableRows[index].picturePathText.setText(imageName);
                    	pictureBlock.pictureTableRows[index].imagePath = imagePath;
                    }       
    			} else if (requestCode >= CAMERA_REQUEST_CODE_START && requestCode < CAMERA_REQUEST_CODE_START + PICTURE_TABLE_ROW_COUNT) {
    				// camera
    				int index = requestCode - CAMERA_REQUEST_CODE_START;
    				pictureBlock.pictureTableRows[index].picturePathText.setText(getResources().getString(R.string.taken_a_photo));
    				pictureBlock.pictureTableRows[index].imageBitmap = (Bitmap) data.getExtras().get("data");
    			}
    		}
    	}    	
    }
    
    private String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
    
    public void setQuoteUntil(View view) {
    	showDialog(DATE_DIALOG_ID);
    }
    
    private int year;
	private int month;
	private int day;
 
	static final int DATE_DIALOG_ID = 999;
	
    @Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
		   // set date picker as current date
		   return new DatePickerDialog(this, datePickerListener, 
                         year, month,day);
		}
		return null;
	}
    
    private DatePickerDialog.OnDateSetListener datePickerListener 
		    = new DatePickerDialog.OnDateSetListener() {
		
		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,
			int selectedMonth, int selectedDay) {
		year = selectedYear;
		month = selectedMonth;
		day = selectedDay;
		
		// set selected date into textview
		quoteUntilEdit.setText(new StringBuilder().append(month + 1)
		   .append("-").append(day).append("-").append(year)
		   .append(" "));				
		}
	};
}
