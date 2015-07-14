package com.development.visualscope;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

public class LiftNeeded extends LinearLayout {
	public CheckBox liftNeededCheckBox;
	public EditText amountEdit;
	public EditText daysNeededEdit;
	
    public LiftNeeded(Context context, AttributeSet attrs) {
        super(context, attrs);
 
        LayoutInflater layoutInflater = (LayoutInflater)context
        		.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.lift_needed, this);
        
        liftNeededCheckBox = (CheckBox) findViewById(R.id.liftNeededCheckBox);
    	amountEdit = (EditText) findViewById(R.id.amountEdit);
    	daysNeededEdit = (EditText) findViewById(R.id.daysNeededEdit);
    }    
}
