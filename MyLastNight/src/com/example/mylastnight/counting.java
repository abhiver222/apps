package com.example.mylastnight;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class counting extends Activity{

	int regcount;
	int specialcount;
	Button addreg,addspcl;
	TextView displayreg;
	TextView displayspcl;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drinkcounter);
		
		regcount=0;
		specialcount=0;
		addreg = (Button) findViewById(R.id.regularbutton);
		addspcl = (Button) findViewById(R.id.specialbutton);
		displayreg = (TextView) findViewById(R.id.regulardrinkview);
		displayspcl = (TextView) findViewById(R.id.specialdrinkview);
		
		addreg.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				regcount++;
				displayreg.setText("You have had "+regcount+" regular drinks");
			}
		});
		
		addspcl.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				specialcount++;
				displayspcl.setText("You have had "+specialcount+" special drinks");
			}
		});
		
	}

}
