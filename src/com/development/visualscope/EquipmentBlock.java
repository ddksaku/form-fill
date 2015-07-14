package com.development.visualscope;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

public class EquipmentBlock extends LinearLayout {
	public CheckBox truck1CheckBox;
	public CheckBox truck2CheckBox;
	public CheckBox truck3CheckBox;
	public CheckBox truck4CheckBox;
	public CheckBox truck5CheckBox;
	public CheckBox truck6CheckBox;
	public CheckBox largeTrailerCheckBox;
	public CheckBox smallTrailerCheckBox;
	public CheckBox nozzleTipsCheckBox;
	public CheckBox turboNozzleCheckBox;
	public CheckBox cycloneCheckBox;
	public CheckBox safetyVestCheckBox;
	public CheckBox wand4ftCheckBox;
	public CheckBox wand8ftCheckBox;
	public CheckBox ropeGrabberCheckBox;
	public CheckBox ropeCheckBox;
	public CheckBox paperWorkCheckBox;
	public CheckBox gateKeyCheckBox;
	public CheckBox drainCoverCheckBox;
	public CheckBox snakesCheckBox;
	public CheckBox squeegyCheckBox;
	public CheckBox shovelCheckBox;
	public CheckBox rakeCheckBox;
	public CheckBox garbageCanCheckBox;
	public CheckBox ladderCheckBox;
	public CheckBox sandwichBoardCheckBox;
	public CheckBox steelEagleSpinnerCheckBox;
	public CheckBox smallVacSpinnerCheckBox;
	public CheckBox largeVacSpinnerCheckBox;
	public CheckBox wetSidewalkSsignCheckBox;
	public CheckBox bigPumpCheckBox;
	public CheckBox floatingSpinnerCheckBox;
	public CheckBox fiberglassExtension18CheckBox;
	public CheckBox fiberglassExtension24CheckBox;
	public CheckBox safetyHarnessLanyardCheckBox;
	public CheckBox hatGlassesGearCheckBox;
	public EditText extraPsiHoseEdit;
	public EditText extraWaterHoseEdit;	
		
    public EquipmentBlock(Context context, AttributeSet attrs) {
        super(context, attrs);
 
        LayoutInflater layoutInflater = (LayoutInflater)context
        		.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.equipment_block, this);       
        
        truck1CheckBox = (CheckBox) findViewById(R.id.truck1CheckBox);
        truck2CheckBox = (CheckBox) findViewById(R.id.truck2CheckBox);
        truck3CheckBox = (CheckBox) findViewById(R.id.truck3CheckBox);
        truck4CheckBox = (CheckBox) findViewById(R.id.truck4CheckBox);
        truck5CheckBox = (CheckBox) findViewById(R.id.truck5CheckBox);
        truck6CheckBox = (CheckBox) findViewById(R.id.truck6CheckBox);
        largeTrailerCheckBox = (CheckBox) findViewById(R.id.largeTrailerCheckBox);
        smallTrailerCheckBox = (CheckBox) findViewById(R.id.smallTrailerCheckBox);
        nozzleTipsCheckBox = (CheckBox) findViewById(R.id.nozzleTipsCheckBox);
        turboNozzleCheckBox = (CheckBox) findViewById(R.id.turboNozzleCheckBox);
        cycloneCheckBox = (CheckBox) findViewById(R.id.cycloneCheckBox);
        safetyVestCheckBox = (CheckBox) findViewById(R.id.safetyVestCheckBox);
        wand4ftCheckBox = (CheckBox) findViewById(R.id.wand4ftCheckBox);
        wand8ftCheckBox = (CheckBox) findViewById(R.id.wand8ftCheckBox);
        ropeGrabberCheckBox = (CheckBox) findViewById(R.id.ropeGrabberCheckBox);
        ropeCheckBox = (CheckBox) findViewById(R.id.ropeCheckBox);
        paperWorkCheckBox = (CheckBox) findViewById(R.id.paperWorkCheckBox);
        gateKeyCheckBox = (CheckBox) findViewById(R.id.gateKeyCheckBox);
        drainCoverCheckBox = (CheckBox) findViewById(R.id.drainCoverCheckBox);
        snakesCheckBox = (CheckBox) findViewById(R.id.snakesCheckBox);
        squeegyCheckBox = (CheckBox) findViewById(R.id.squeegyCheckBox);
        shovelCheckBox = (CheckBox) findViewById(R.id.shovelCheckBox);
        rakeCheckBox = (CheckBox) findViewById(R.id.rakeCheckBox);
        garbageCanCheckBox = (CheckBox) findViewById(R.id.garbageCanCheckBox);
        ladderCheckBox = (CheckBox) findViewById(R.id.ladderCheckBox);
        sandwichBoardCheckBox = (CheckBox) findViewById(R.id.sandwichBoardCheckBox);
        steelEagleSpinnerCheckBox = (CheckBox) findViewById(R.id.steelEagleSpinnerCheckBox);
        smallVacSpinnerCheckBox = (CheckBox) findViewById(R.id.smallVacSpinnerCheckBox);
        largeVacSpinnerCheckBox = (CheckBox) findViewById(R.id.largeVacSpinnerCheckBox);
        wetSidewalkSsignCheckBox = (CheckBox) findViewById(R.id.wetSidewalkSsignCheckBox);
        bigPumpCheckBox = (CheckBox) findViewById(R.id.bigPumpCheckBox);
        floatingSpinnerCheckBox = (CheckBox) findViewById(R.id.floatingSpinnerCheckBox);
        fiberglassExtension18CheckBox = (CheckBox) findViewById(R.id.fiberglassExtension18CheckBox);
        fiberglassExtension24CheckBox = (CheckBox) findViewById(R.id.fiberglassExtension24CheckBox);
        safetyHarnessLanyardCheckBox = (CheckBox) findViewById(R.id.safetyHarnessLanyardCheckBox);
        hatGlassesGearCheckBox = (CheckBox) findViewById(R.id.hatGlassesGearCheckBox);
        
        extraPsiHoseEdit = (EditText) findViewById(R.id.extraPsiHoseEdit);
        extraWaterHoseEdit = (EditText) findViewById(R.id.extraWaterHoseEdit);        
    }    
}
