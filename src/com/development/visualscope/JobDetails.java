package com.development.visualscope;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class JobDetails extends LinearLayout {		
	public CheckBox waterColdCheckBox;
	public CheckBox waterHotCheckBox;
	public CheckBox waterHydrantCheckBox;
	public CheckBox waterScafoldingCheckBox;
	public CheckBox waterHarnessCheckBox;
	public CheckBox waterParkingCheckBox;
	public CheckBox waterParkingStreetCheckBox;
	public CheckBox waterParkingLotCheckBox;
	public CheckBox waterLiftNeededCheckBox;
	public CheckBox waterMildewCheckBox;
	public CheckBox waterOilCheckBox;
	public CheckBox waterEnzymeMicrobesCheckBox;
	public CheckBox waterMildewcideCheckBox;
	public CheckBox waterEnzymesCheckBox;		
	
	public EditText additionalNoteInJobEdit;
	
	public Spinner waterAvaliableSpinner;
			
    public JobDetails(Context context, AttributeSet attrs) {
        super(context, attrs);
 
        LayoutInflater layoutInflater = (LayoutInflater)context
        		.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.job_details, this);               
        
        waterColdCheckBox = (CheckBox) findViewById(R.id.waterColdCheckBox);
        waterHotCheckBox = (CheckBox) findViewById(R.id.waterHotCheckBox);
        waterHydrantCheckBox = (CheckBox) findViewById(R.id.waterHydrantCheckBox);
        waterScafoldingCheckBox = (CheckBox) findViewById(R.id.waterScafoldingCheckBox);
        waterHarnessCheckBox = (CheckBox) findViewById(R.id.waterHarnessCheckBox);
        waterParkingCheckBox = (CheckBox) findViewById(R.id.waterParkingCheckBox);
        waterParkingStreetCheckBox = (CheckBox) findViewById(R.id.waterParkingStreetCheckBox);
        waterParkingLotCheckBox = (CheckBox) findViewById(R.id.waterParkingLotCheckBox);
        waterLiftNeededCheckBox = (CheckBox) findViewById(R.id.waterLiftNeededCheckBox);
        waterMildewCheckBox = (CheckBox) findViewById(R.id.waterMildewCheckBox);
        waterOilCheckBox = (CheckBox) findViewById(R.id.waterOilCheckBox);
        waterEnzymeMicrobesCheckBox = (CheckBox) findViewById(R.id.waterEnzymeMicrobesCheckBox);
        waterMildewcideCheckBox = (CheckBox) findViewById(R.id.waterMildewcideCheckBox);
        waterEnzymesCheckBox = (CheckBox) findViewById(R.id.waterEnzymesCheckBox);      
        additionalNoteInJobEdit = (EditText) findViewById(R.id.additionalNoteInJobEdit);        
        waterAvaliableSpinner= (Spinner) findViewById(R.id.waterAvaliableSpinner);
        
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Parking);               
        for (int index = 0; index < typedArray.getIndexCount(); index++)
        {
           int attr = typedArray.getIndex(index);
           switch (attr)
           {
              case R.styleable.Parking_isParkingPanel:
                 boolean isParkingPanel = typedArray.getBoolean(attr, false);
                 if (isParkingPanel) {
                	 waterLiftNeededCheckBox.setVisibility(View.VISIBLE);
                	 waterEnzymesCheckBox.setVisibility(View.VISIBLE);
                	 additionalNoteInJobEdit.setVisibility(View.VISIBLE);
                 } else {
                	 waterLiftNeededCheckBox.setVisibility(View.GONE);
                	 waterEnzymesCheckBox.setVisibility(View.GONE);
                	 additionalNoteInJobEdit.setVisibility(View.GONE);
                 }                        
                 break;              
           }
        }        
        typedArray.recycle();
        
        List<String> avaliableList = new ArrayList<String>();
        avaliableList.add("Yes");
        avaliableList.add("No");
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,         		
        		android.R.layout.simple_spinner_item, avaliableList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        waterAvaliableSpinner.setAdapter(adapter);
    }    
}
