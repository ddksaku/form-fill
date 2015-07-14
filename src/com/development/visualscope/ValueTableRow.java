package com.development.visualscope;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;

public class ValueTableRow extends LinearLayout {		
	private EditText valueEdit;
	private EditText multiplierEdit;
	private EditText infoEdit;
	private EditText typeEdit;
	
    public ValueTableRow(Context context, AttributeSet attrs) {
        super(context, attrs);
 
        LayoutInflater layoutInflater = (LayoutInflater)context
        		.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.value_table_row, this);
        
        valueEdit = (EditText) findViewById(R.id.valueEdit);
        multiplierEdit = (EditText) findViewById(R.id.multiplierValueEdit);
        infoEdit = (EditText) findViewById(R.id.infoValueEdit);
        typeEdit = (EditText) findViewById(R.id.typeValueEdit);
    }
    
    public String getValue() {    	
    	return valueEdit.getText().toString();
    }
    
    public String getMultiplier() {    	
    	return multiplierEdit.getText().toString();
    }
    
    public String getInfo() {    	
    	return infoEdit.getText().toString();
    }
     
    public String getType() {    	
    	return typeEdit.getText().toString();
    }
}
