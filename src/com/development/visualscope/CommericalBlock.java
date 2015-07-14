package com.development.visualscope;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;

public class CommericalBlock extends LinearLayout {
	public EditText commericalSqftEdit;
	public EditText commericalSqftMultiplierEdit;
	public EditText commericalSqftInfoEdit;
	public EditText commericalBuildingEdit;
	public EditText commericalBuildingMultiplierEdit;
	public EditText commericalBuildingInfoEdit;
	public EditText commericalUnitsEdit;
	public EditText commericalUnitsMultiplierEdit;
	public EditText commericalUnitsInfoEdit;
	public EditText commericalStoriesEdit;
	public EditText commericalRailingsEdit;	
	
	public SidingType commericalSidingType;
	public LiftNeeded commericalLiftNeeded;
	public Environmental commericalEnvironmental;
	public Walkways commericalWalkways;
	public JobDetails commericalJobDetails;
	public Roofs commericalRoofs;
	
    public CommericalBlock(Context context, AttributeSet attrs) {
        super(context, attrs);
 
        LayoutInflater layoutInflater = (LayoutInflater)context
        		.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.commerical_block, this);
        
        commericalSqftEdit = (EditText) findViewById(R.id.commericalSqftEdit);
        commericalSqftMultiplierEdit = (EditText) findViewById(R.id.commericalSqftMultiplierEdit);
        commericalSqftInfoEdit = (EditText) findViewById(R.id.commericalSqftInfoEdit);
        commericalBuildingEdit = (EditText) findViewById(R.id.commericalBuildingEdit);
        commericalBuildingMultiplierEdit = (EditText) findViewById(R.id.commericalBuildingMultiplierEdit);
        commericalBuildingInfoEdit = (EditText) findViewById(R.id.commericalBuildingInfoEdit);
        commericalUnitsEdit = (EditText) findViewById(R.id.commericalUnitsEdit);
        commericalUnitsMultiplierEdit = (EditText) findViewById(R.id.commericalUnitsMultiplierEdit);
        commericalUnitsInfoEdit = (EditText) findViewById(R.id.commericalUnitsInfoEdit);
        commericalStoriesEdit = (EditText) findViewById(R.id.commericalStoriesEdit);
        commericalRailingsEdit = (EditText) findViewById(R.id.commericalRailingsEdit);
        
        commericalSidingType = (SidingType) findViewById(R.id.commericalSidingType);        
        commericalLiftNeeded = (LiftNeeded) findViewById(R.id.commericalLiftNeeded);
        commericalEnvironmental = (Environmental) findViewById(R.id.commericalEnvironmental);
        commericalWalkways = (Walkways) findViewById(R.id.commericalWalkways);
        commericalJobDetails = (JobDetails) findViewById(R.id.commericalJobDetails);
        commericalRoofs = (Roofs) findViewById(R.id.commericalRoofs);

    }    
}
