package com.development.visualscope;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;

public class ApartmentBlock extends LinearLayout {
	public EditText apartmentSqftEdit;
	public EditText apartmentSqftMultiplierEdit;
	public EditText apartmentSqftInfoEdit;
	public EditText apartmentBuildingsEdit;
	public EditText apartmentBuildingsMultiplierEdit;
	public EditText apartmentBuildingsInfoEdit;
	public EditText apartmentUnitsEdit;
	public EditText apartmentUnitsMultiplierEdit;
	public EditText apartmentUnitsInfoEdit;
	public EditText apartmentParkingEdit;
	public EditText apartmentParkingMultiplierEdit;
	public EditText apartmentParkingInfoEdit;
	public EditText apartmentCarEdit;
	public EditText apartmentCarMultiplierEdit;
	public EditText apartmentCarInfoEdit;
	public EditText apartmentStairwaysEdit;
	public EditText apartmentStairwaysMultiplierEdit;
	public EditText apartmentStairwaysInfoEdit;
	public EditText apartmentRailingsEdit;
	public EditText apartmentRailingsMultiplierEdit;
	public EditText apartmentRailingsInfoEdit;
	public EditText apartmentStoriesEdit;
	public EditText apartmentBreezyEdit;
	public EditText apartmentBreezyMultiplierEdit;
	public EditText apartmentBreezyInfoEdit;
	public EditText apartmentOtherEdit;
	
	public SidingType apartmentSidingType;
	public LiftNeeded apartmentLiftNeeded;
	public Environmental apartmentEnvironmental;
	public Walkways apartmentWalkways;
	public JobDetails apartmentJobDetails;
	public Roofs apartmentRoofs;
	
    public ApartmentBlock(Context context, AttributeSet attrs) {
        super(context, attrs);
 
        LayoutInflater layoutInflater = (LayoutInflater)context
        		.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.apartment_block, this);
        
        apartmentSqftEdit = (EditText) findViewById(R.id.apartmentSqftEdit);
		apartmentSqftMultiplierEdit = (EditText) findViewById(R.id.apartmentSqftMultiplierEdit);
		apartmentSqftInfoEdit = (EditText) findViewById(R.id.apartmentSqftInfoEdit);
		apartmentBuildingsEdit = (EditText) findViewById(R.id.apartmentBuildingsEdit);
		apartmentBuildingsMultiplierEdit = (EditText) findViewById(R.id.apartmentBuildingsMultiplierEdit);
		apartmentBuildingsInfoEdit = (EditText) findViewById(R.id.apartmentBuildingsInfoEdit);
		apartmentUnitsEdit = (EditText) findViewById(R.id.apartmentUnitsEdit);
		apartmentUnitsMultiplierEdit = (EditText) findViewById(R.id.apartmentUnitsMultiplierEdit);
		apartmentUnitsInfoEdit = (EditText) findViewById(R.id.apartmentUnitsInfoEdit);
		apartmentParkingEdit = (EditText) findViewById(R.id.apartmentParkingEdit);
		apartmentParkingMultiplierEdit = (EditText) findViewById(R.id.apartmentParkingMultiplierEdit);
		apartmentParkingInfoEdit = (EditText) findViewById(R.id.apartmentParkingInfoEdit);
		apartmentCarEdit = (EditText) findViewById(R.id.apartmentCarEdit);
		apartmentCarMultiplierEdit = (EditText) findViewById(R.id.apartmentCarMultiplierEdit);
		apartmentCarInfoEdit = (EditText) findViewById(R.id.apartmentCarInfoEdit);
		apartmentStairwaysEdit = (EditText) findViewById(R.id.apartmentStairwaysEdit);
		apartmentStairwaysMultiplierEdit = (EditText) findViewById(R.id.apartmentStairwaysMultiplierEdit);
		apartmentStairwaysInfoEdit = (EditText) findViewById(R.id.apartmentStairwaysInfoEdit);
		apartmentRailingsEdit = (EditText) findViewById(R.id.apartmentRailingsEdit);
		apartmentRailingsMultiplierEdit = (EditText) findViewById(R.id.apartmentRailingsMultiplierEdit);
		apartmentRailingsInfoEdit = (EditText) findViewById(R.id.apartmentRailingsInfoEdit);
		apartmentStoriesEdit = (EditText) findViewById(R.id.apartmentStoriesEdit);
		apartmentBreezyEdit = (EditText) findViewById(R.id.apartmentBreezyEdit);
		apartmentBreezyMultiplierEdit = (EditText) findViewById(R.id.apartmentBreezyMultiplierEdit);
		apartmentBreezyInfoEdit = (EditText) findViewById(R.id.apartmentBreezyInfoEdit);
		apartmentOtherEdit = (EditText) findViewById(R.id.apartmentOtherEdit);
		
        apartmentSidingType = (SidingType) findViewById(R.id.apartmentSidingType);        
        apartmentLiftNeeded = (LiftNeeded) findViewById(R.id.apartmentLiftNeeded);
        apartmentEnvironmental = (Environmental) findViewById(R.id.apartmentEnvironmental);
        apartmentWalkways = (Walkways) findViewById(R.id.apartmentWalkways);
        apartmentJobDetails = (JobDetails) findViewById(R.id.apartmentJobDetails);
        apartmentRoofs = (Roofs) findViewById(R.id.apartmentRoofs);
    }    
}
