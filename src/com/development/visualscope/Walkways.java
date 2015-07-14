package com.development.visualscope;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Walkways extends LinearLayout {
	private TextView walkwaysText;
	public EditText walkwaysSqftEdit;
	public EditText walkwaysMultiplierEdit;
	public EditText walkwaysInfoEdit;
	public EditText walkwaysGumEdit;
	public EditText walkwaysNoteEdit;
	public EditText walkwaysAccessTimeEdit;	
	
	
    public Walkways(Context context, AttributeSet attrs) {
        super(context, attrs);
 
        LayoutInflater layoutInflater = (LayoutInflater)context
        		.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.walkways, this);
        
        walkwaysText = (TextView) findViewById(R.id.walkwaysText);
        
        walkwaysSqftEdit = (EditText) findViewById(R.id.walkwaysSqftEdit);
        walkwaysMultiplierEdit = (EditText) findViewById(R.id.walkwaysMultiplierEdit);
        walkwaysInfoEdit = (EditText) findViewById(R.id.walkwaysInfoEdit);
        walkwaysGumEdit = (EditText) findViewById(R.id.walkwaysGumEdit);
        walkwaysNoteEdit = (EditText) findViewById(R.id.walkwaysNoteEdit);
        walkwaysAccessTimeEdit = (EditText) findViewById(R.id.walkwaysAccessTimeEdit);
        
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Walkways);               
        for (int index = 0; index < typedArray.getIndexCount(); index++)
        {
           int attr = typedArray.getIndex(index);
           switch (attr)
           {
              case R.styleable.Walkways_walkwaysPanelName:
                 String walkwaysPanelName = typedArray.getString(attr);
                 walkwaysText.setText(walkwaysPanelName);                 
                 break;              
           }
        }        
        typedArray.recycle();
    }    
}
