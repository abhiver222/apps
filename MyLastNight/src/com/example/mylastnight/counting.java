package com.example.mylastnight;

// Creating the drinks counter button page. Adds and retrieves data from the database or shared preferences

import java.util.ArrayList;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
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
	ArrayList<String> vals = new ArrayList<String>();
	datasource datasrc;
	int size = 0;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drinkcounter);

		regcount = 0;
		specialcount = 0;
		addreg = (Button) findViewById(R.id.regularbutton);
		addspcl = (Button) findViewById(R.id.specialbutton);
		displayreg = (TextView) findViewById(R.id.regulardrinkview);
		displayspcl = (TextView) findViewById(R.id.specialdrinkview);

		displayspcl.setText("You have had 0 special drinks");
		Log.i("jello", "reached till here");

		SharedPreferences prefs = getApplicationContext().getSharedPreferences(
				"prefs", 0);
		Editor edt = prefs.edit();
		Map<String, ?> map = prefs.getAll();
		if (map != null) {
			for (Map.Entry<String, ?> entry : map.entrySet()) {
				vals.add(entry.getValue().toString());
			}
			size = vals.size();
			displayspcl.setText("You have had " + (size) + " special drinks");
		}
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

					first = false;
					startActivity(spcl);
				} else {
					int special = getIntent().getExtras().getInt("values");

					first = false;
					startActivity(spcl);
				}

			}
		});
	}

}
