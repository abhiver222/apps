package com.example.mylastnight;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class counting extends Activity {

	int regcount;
	int specialcount;
	Button addreg, addspcl;
	TextView displayreg;
	TextView displayspcl;
	boolean first = true;
	
	datasource datasrc;
	

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drinkcounter);

		regcount = 0;
		specialcount = 0;
		addreg = (Button) findViewById(R.id.regularbutton);
		addspcl = (Button) findViewById(R.id.specialbutton);
		displayreg = (TextView) findViewById(R.id.regulardrinkview);
		displayspcl = (TextView) findViewById(R.id.specialdrinkview);

		if (!first) {
			int special = getIntent().getExtras().getInt("values");
			displayspcl.setText("You have had " + special + " special drinks");
		}
		
		datasrc = new datasource(this);

		addreg.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				regcount++;
				displayreg.setText("You have had " + regcount
						+ " regular drinks");
			}
		});

		addspcl.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent spcl = new Intent(counting.this, drinkmenu.class);
				specialcount++;
				if (first) {
					displayspcl.setText("You have had " + specialcount
							+ " special drinks");
					first = false;
					startActivity(spcl);
				} 
				else {
					int special = getIntent().getExtras().getInt("values");
					displayspcl.setText("You have had " + special
							+ " special drinks");
					first = false;
					startActivity(spcl);
				}

			}
		});		
	}
	/*protected void onResume(){
		super.onResume();
		datasrc.open();
	}
	
	protected void onPause(){
		super.onPause();
		datasrc.close();
	}*/

}
