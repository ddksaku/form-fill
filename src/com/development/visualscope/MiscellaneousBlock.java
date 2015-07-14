package com.development.visualscope;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MiscellaneousBlock extends LinearLayout {
	public ValueTableRow valueTableRow1;
	public ValueTableRow valueTableRow2;
	public ValueTableRow valueTableRow3;
	public ValueTableRow valueTableRow4;
	public ValueTableRow valueTableRow5;
	public ValueTableRow valueTableRow6;
	public ValueTableRow valueTableRow7;
	public ValueTableRow valueTableRow8;
	public ValueTableRow valueTableRow9;
	public ValueTableRow valueTableRow10;
	public ValueTableRow valueTableRow11;
	public ValueTableRow valueTableRow12;
	public ValueTableRow valueTableRow13;
	public ValueTableRow valueTableRow14;
	public ValueTableRow valueTableRow15;
	public ValueTableRow valueTableRow16;
	public ValueTableRow valueTableRow17;
	public ValueTableRow valueTableRow18;
	public ValueTableRow valueTableRow19;
	public ValueTableRow valueTableRow20;
	public EditText miscellaneousNoteEdit;
	
    public MiscellaneousBlock(Context context, AttributeSet attrs) {
        super(context, attrs);
 
        LayoutInflater layoutInflater = (LayoutInflater)context
        		.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.miscellaneous_block, this);
        
        valueTableRow1 = (ValueTableRow) findViewById(R.id.valueTableRow1);
        valueTableRow2 = (ValueTableRow) findViewById(R.id.valueTableRow2);
        valueTableRow3 = (ValueTableRow) findViewById(R.id.valueTableRow3);
        valueTableRow4 = (ValueTableRow) findViewById(R.id.valueTableRow4);
        valueTableRow5 = (ValueTableRow) findViewById(R.id.valueTableRow5);
        valueTableRow6 = (ValueTableRow) findViewById(R.id.valueTableRow6);
        valueTableRow7 = (ValueTableRow) findViewById(R.id.valueTableRow7);
        valueTableRow8 = (ValueTableRow) findViewById(R.id.valueTableRow8);
        valueTableRow9 = (ValueTableRow) findViewById(R.id.valueTableRow9);
        valueTableRow10 = (ValueTableRow) findViewById(R.id.valueTableRow10);
        valueTableRow11 = (ValueTableRow) findViewById(R.id.valueTableRow11);
        valueTableRow12 = (ValueTableRow) findViewById(R.id.valueTableRow12);
        valueTableRow13 = (ValueTableRow) findViewById(R.id.valueTableRow13);
        valueTableRow14 = (ValueTableRow) findViewById(R.id.valueTableRow14);
        valueTableRow15 = (ValueTableRow) findViewById(R.id.valueTableRow15);
        valueTableRow16 = (ValueTableRow) findViewById(R.id.valueTableRow16);
        valueTableRow17 = (ValueTableRow) findViewById(R.id.valueTableRow17);
        valueTableRow18 = (ValueTableRow) findViewById(R.id.valueTableRow18);
        valueTableRow19 = (ValueTableRow) findViewById(R.id.valueTableRow19);
        valueTableRow20 = (ValueTableRow) findViewById(R.id.valueTableRow20);
        miscellaneousNoteEdit = (EditText) findViewById(R.id.miscellaneousNoteEdit);               
    }    
}
