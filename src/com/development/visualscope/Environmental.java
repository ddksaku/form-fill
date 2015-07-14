package com.development.visualscope;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

public class Environmental extends LinearLayout {
	public CheckBox wasteWaterCheckBox;
	public EditText drainTypeEdit;
	public CheckBox spinnerCheckBox;
	public CheckBox dumpSiteCheckBox;
	public CheckBox recycleWaterCheckBox;		
	
    public Environmental(Context context, AttributeSet attrs) {
        super(context, attrs);
 
        LayoutInflater layoutInflater = (LayoutInflater)context
        		.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.environmental, this);
        
        wasteWaterCheckBox = (CheckBox) findViewById(R.id.wasteWaterCheckBox);
    	drainTypeEdit = (EditText) findViewById(R.id.drainTypeEdit);
    	spinnerCheckBox = (CheckBox) findViewById(R.id.spinnerCheckBox);
    	dumpSiteCheckBox = (CheckBox) findViewById(R.id.dumpSiteCheckBox);
    	recycleWaterCheckBox = (CheckBox) findViewById(R.id.recycleWaterCheckBox);
    }    
}
