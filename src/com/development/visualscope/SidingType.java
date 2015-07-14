package com.development.visualscope;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class SidingType extends LinearLayout {
	public CheckBox stuccoCheckBox;
	public CheckBox vinylCheckBox;
	public CheckBox hardyCheckBox;
	public CheckBox brickCheckBox;
	public CheckBox woodCheckBox;
	public CheckBox concreteCheckBox;
	public CheckBox metalCheckBox;		
	
    public SidingType(Context context, AttributeSet attrs) {
        super(context, attrs);
 
        LayoutInflater layoutInflater = (LayoutInflater)context
        		.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.siding_type, this);
        
        stuccoCheckBox = (CheckBox) findViewById(R.id.stuccoCheckBox);
        vinylCheckBox = (CheckBox) findViewById(R.id.vinylCheckBox);
        hardyCheckBox = (CheckBox) findViewById(R.id.hardyCheckBox);
        brickCheckBox = (CheckBox) findViewById(R.id.brickCheckBox);
        woodCheckBox = (CheckBox) findViewById(R.id.woodCheckBox);
        concreteCheckBox = (CheckBox) findViewById(R.id.concreteCheckBox);
        metalCheckBox = (CheckBox) findViewById(R.id.metalCheckBox);

    }    
}
