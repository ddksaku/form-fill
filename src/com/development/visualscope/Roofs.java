package com.development.visualscope;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

public class Roofs extends LinearLayout {
	public EditText roofsSqftEdit;
	public EditText roofsSqftMultiplierEdit;
	public EditText roofsSqftInfoEdit;
	public CheckBox roofsShakeCheckBox;
	public CheckBox roofsTileCheckBox;
	public CheckBox roofsCompositionCheckBox;
	public CheckBox roofsMetalCheckBox;
	public EditText roofsNoteEdit;
	public EditText additionalNoteEdit;	
	
    public Roofs(Context context, AttributeSet attrs) {
        super(context, attrs);
 
        LayoutInflater layoutInflater = (LayoutInflater)context
        		.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.roofs, this);
        
        roofsSqftEdit = (EditText) findViewById(R.id.roofsSqftEdit);
        roofsSqftMultiplierEdit = (EditText) findViewById(R.id.roofsSqftMultiplierEdit);
        roofsSqftInfoEdit = (EditText) findViewById(R.id.roofsSqftInfoEdit);
        roofsShakeCheckBox = (CheckBox) findViewById(R.id.roofsShakeCheckBox);
        roofsTileCheckBox = (CheckBox) findViewById(R.id.roofsTileCheckBox);
        roofsCompositionCheckBox = (CheckBox) findViewById(R.id.roofsCompositionCheckBox);
        roofsMetalCheckBox = (CheckBox) findViewById(R.id.roofsMetalCheckBox);
        roofsNoteEdit = (EditText) findViewById(R.id.roofsNoteEdit);
        additionalNoteEdit = (EditText) findViewById(R.id.additionalNoteEdit);

        
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Roofs);               
        for (int index = 0; index < typedArray.getIndexCount(); index++)
        {
           int attr = typedArray.getIndex(index);
           switch (attr)
           {
              case R.styleable.Roofs_isMetalCheckBoxVisible:
                 boolean isMetalCheckBoxVisible = typedArray.getBoolean(attr, true);
                 if (isMetalCheckBoxVisible) {
                	 roofsMetalCheckBox.setVisibility(View.VISIBLE);
                 } else {
                	 roofsMetalCheckBox.setVisibility(View.GONE);
                 }
                 break;              
           }
        }        
        typedArray.recycle();
    }    
}
