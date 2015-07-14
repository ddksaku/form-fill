package com.development.visualscope;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PictureTableRow extends LinearLayout {
	public TextView picturePathText;
	public EditText pictureNameEdit;
	public EditText pictureFooterEdit;
	public Button pictureSelectButton;
	public Button pictureCameraButton;
	public String imagePath;
	public Bitmap imageBitmap;
		
    public PictureTableRow(Context context, AttributeSet attrs) {
        super(context, attrs);
 
        LayoutInflater layoutInflater = (LayoutInflater)context
        		.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.picture_table_row, this);
        
        picturePathText = (TextView) findViewById(R.id.picturePathText);
        pictureNameEdit = (EditText) findViewById(R.id.pictureNameEdit);
        pictureFooterEdit = (EditText) findViewById(R.id.pictureFooterEdit);
        pictureSelectButton = (Button) findViewById(R.id.pictureSelectButton);
        pictureCameraButton = (Button) findViewById(R.id.pictureCameraButton);
        
        imagePath = null;
        imageBitmap = null;
    }                     
}
