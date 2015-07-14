package com.development.visualscope;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;

public class ParkingBlock extends LinearLayout {	
	public EditText lotSqftEdit;
	public EditText lotSqftMultiplierEdit;
	public EditText lotSqftInfoEdit;
	public EditText lotStallsEdit;
	public EditText lotStallsMultiplierEdit;
	public EditText lotStallsInfoEdit;
	public EditText lotDrainsEdit;
	public EditText lotCurbEdit;
	public EditText lotCurbMultiplierEdit;
	public EditText lotCurbInfoEdit;
	public EditText garageSqftEdit;
	public EditText garageSqftMultiplierEdit;
	public EditText garageSqftInfoEdit;
	public EditText garageStallsEdit;
	public EditText garageStallsMultiplierEdit;
	public EditText garageStallsInfoEdit;
	public EditText garageWallsEdit;
	public EditText garageWallsMultiplierEdit;
	public EditText garageWallsInfoEdit;
	public EditText garageRailingsEdit;
	public EditText garageFloorsEdit;
	public EditText garageStairsEdit;
	public EditText garageStairsMultiplierEdit;
	public EditText garageStairsInfoEdit;
	public EditText garageCeilingEdit;
	public EditText garageCeilingInfoEdit;
		
	public Environmental parkingEnvironmental;
	public Walkways parkingWalkways;
	public JobDetails parkingJobDetails;		
	
    public ParkingBlock(Context context, AttributeSet attrs) {
        super(context, attrs);
 
        LayoutInflater layoutInflater = (LayoutInflater)context
        		.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.parking_block, this);
        
        lotSqftEdit = (EditText) findViewById(R.id.lotSqftEdit);
        lotSqftMultiplierEdit = (EditText) findViewById(R.id.lotSqftMultiplierEdit);
        lotSqftInfoEdit = (EditText) findViewById(R.id.lotSqftInfoEdit);
        lotStallsEdit = (EditText) findViewById(R.id.lotStallsEdit);
        lotStallsMultiplierEdit = (EditText) findViewById(R.id.lotStallsMultiplierEdit);
        lotStallsInfoEdit = (EditText) findViewById(R.id.lotStallsInfoEdit);
        lotDrainsEdit = (EditText) findViewById(R.id.lotDrainsEdit);
        lotCurbEdit = (EditText) findViewById(R.id.lotCurbEdit);
        lotCurbMultiplierEdit = (EditText) findViewById(R.id.lotCurbMultiplierEdit);
        lotCurbInfoEdit = (EditText) findViewById(R.id.lotCurbInfoEdit);
        garageSqftEdit = (EditText) findViewById(R.id.garageSqftEdit);
        garageSqftMultiplierEdit = (EditText) findViewById(R.id.garageSqftMultiplierEdit);
        garageSqftInfoEdit = (EditText) findViewById(R.id.garageSqftInfoEdit);
        garageStallsEdit = (EditText) findViewById(R.id.garageStallsEdit);
        garageStallsMultiplierEdit = (EditText) findViewById(R.id.garageStallsMultiplierEdit);
        garageStallsInfoEdit = (EditText) findViewById(R.id.garageStallsInfoEdit);
        garageWallsEdit = (EditText) findViewById(R.id.garageWallsEdit);
        garageWallsMultiplierEdit = (EditText) findViewById(R.id.garageWallsMultiplierEdit);
        garageWallsInfoEdit = (EditText) findViewById(R.id.garageWallsInfoEdit);
        garageRailingsEdit = (EditText) findViewById(R.id.garageRailingsEdit);
        garageFloorsEdit = (EditText) findViewById(R.id.garageFloorsEdit);
        garageStairsEdit = (EditText) findViewById(R.id.garageStairsEdit);
        garageStairsMultiplierEdit = (EditText) findViewById(R.id.garageStairsMultiplierEdit);
        garageStairsInfoEdit = (EditText) findViewById(R.id.garageStairsInfoEdit);
        garageCeilingEdit = (EditText) findViewById(R.id.garageCeilingEdit);
        garageCeilingInfoEdit = (EditText) findViewById(R.id.garageCeilingInfoEdit);
        
        parkingEnvironmental = (Environmental) findViewById(R.id.parkingEnvironmental);
        parkingWalkways = (Walkways) findViewById(R.id.parkingWalkways);
        parkingJobDetails = (JobDetails) findViewById(R.id.parkingJobDetails);
    }    
}
