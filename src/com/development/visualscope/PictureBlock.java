package com.development.visualscope;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class PictureBlock extends LinearLayout {
	public PictureTableRow[] pictureTableRows;
	/*
	public PictureTableRow pictureTableRow1;
	public PictureTableRow pictureTableRow2;
	public PictureTableRow pictureTableRow3;
	public PictureTableRow pictureTableRow4;
	public PictureTableRow pictureTableRow5;
	public PictureTableRow pictureTableRow6;
	public PictureTableRow pictureTableRow7;
	public PictureTableRow pictureTableRow8;
	public PictureTableRow pictureTableRow9;
	public PictureTableRow pictureTableRow10;
	public PictureTableRow pictureTableRow11;
	public PictureTableRow pictureTableRow12;
	public PictureTableRow pictureTableRow13;
	public PictureTableRow pictureTableRow14;
	public PictureTableRow pictureTableRow15;
	public PictureTableRow pictureTableRow16;
	public PictureTableRow pictureTableRow17;
	*/	
	
    public PictureBlock(Context context, AttributeSet attrs) {
        super(context, attrs);
 
        LayoutInflater layoutInflater = (LayoutInflater)context
        		.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.picture_block, this);    
        
        pictureTableRows = new PictureTableRow[17];
        pictureTableRows[0] = (PictureTableRow) findViewById(R.id.pictureTableRow1);
        pictureTableRows[1] = (PictureTableRow) findViewById(R.id.pictureTableRow2);
        pictureTableRows[2] = (PictureTableRow) findViewById(R.id.pictureTableRow3);
        pictureTableRows[3] = (PictureTableRow) findViewById(R.id.pictureTableRow4);
        pictureTableRows[4] = (PictureTableRow) findViewById(R.id.pictureTableRow5);
        pictureTableRows[5] = (PictureTableRow) findViewById(R.id.pictureTableRow6);
        pictureTableRows[6] = (PictureTableRow) findViewById(R.id.pictureTableRow7);
        pictureTableRows[7] = (PictureTableRow) findViewById(R.id.pictureTableRow8);
        pictureTableRows[8] = (PictureTableRow) findViewById(R.id.pictureTableRow9);
        pictureTableRows[9] = (PictureTableRow) findViewById(R.id.pictureTableRow10);
        pictureTableRows[10] = (PictureTableRow) findViewById(R.id.pictureTableRow11);
        pictureTableRows[11] = (PictureTableRow) findViewById(R.id.pictureTableRow12);
        pictureTableRows[12] = (PictureTableRow) findViewById(R.id.pictureTableRow13);
        pictureTableRows[13] = (PictureTableRow) findViewById(R.id.pictureTableRow14);
        pictureTableRows[14] = (PictureTableRow) findViewById(R.id.pictureTableRow15);
        pictureTableRows[15] = (PictureTableRow) findViewById(R.id.pictureTableRow16);
        pictureTableRows[16] = (PictureTableRow) findViewById(R.id.pictureTableRow17);        
    }    
}
