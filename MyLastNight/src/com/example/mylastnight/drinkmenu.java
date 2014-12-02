package com.example.mylastnight;

import java.util.Arrays;
import java.util.List;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class drinkmenu extends dataclass implements OnItemSelectedListener {

	Spinner commonnames;
	Spinner specialnames;
	TextView display;
	int pos, i = 0;
	String name;
	String[] names = { " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " " };
	boolean first = true;
	int size = 1;

	datasource dtsrc;

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.drinkmenu);

		commonnames = (Spinner) findViewById(R.id.spinner1);
		specialnames = (Spinner) findViewById(R.id.spinner2);
		display = (TextView) findViewById(R.id.selected);

		getActionBar().setDisplayHomeAsUpEnabled(true);

		ArrayAdapter<CharSequence> cname = ArrayAdapter.createFromResource(
				this, R.array.commonmenudisplay,
				android.R.layout.simple_spinner_item);
		ArrayAdapter<CharSequence> spname = ArrayAdapter.createFromResource(
				this, R.array.specialmenudisplay,
				android.R.layout.simple_spinner_item);

		// ArrayAdapter<String> cname = new
		// ArrayAdapter<String>(drinkmenu.this,R.array.commonmenudisplay,android.R.layout.simple_spinner_item);
		// ArrayAdapter<String> spname = new
		// ArrayAdapter<String>(drinkmenu.this,
		// R.array.specialmenudisplay,android.R.layout.simple_spinner_item);

		// cname.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// spname.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		commonnames.setAdapter(cname);
		specialnames.setAdapter(spname);

		commonnames.setOnItemSelectedListener(this);
		specialnames.setOnItemSelectedListener(this);
		dtsrc = new datasource(this);
		dtsrc.open();
		Log.i("Data", "Datasource created new table");

		// List<drinkval> drinks = dtsrc.findall();//put where ever data is
		// needed

	}

	@Override
	public void onItemSelected(AdapterView<?> spin, View view, int position,
			long a) {

		switch (spin.getId()) {
		case R.id.spinner1:
			drinkmenu.this.name = spin.getItemAtPosition(position).toString();
			String name1 = drinkmenu.this.name;
			createdata(name);

		case R.id.spinner2:
			drinkmenu.this.pos = position;
			drinkmenu.this.name = spin.getItemAtPosition(pos).toString();
			String name = drinkmenu.this.name;
			if (first) {
				names[i] = (drinkmenu.this.name);
				createdata(names[i]);
				display.setText(Arrays.toString(names).replaceAll("\\[", "")
						.replaceAll("\\]", "").replaceAll(",", " "));
				i++;
				first = false;
			} else {
				names[i] = (drinkmenu.this.name + " + ");
				createdata(names[i]);
				display.setText(Arrays.toString(names).replaceAll("\\[", "")
						.replaceAll("\\]", "").replaceAll(",", " "));
				i++;
			}

			// createdata(name); remove if wanted
		}// end of switch
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.topactionbuttons, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		size++;
		switch (item.getItemId()) {
		case R.id.action_settings:
			for (int i = 0; i < names.length; i++)
				names[i] = " ";

			display.setText(Arrays.toString(names).replaceAll("\\[", "")
					.replaceAll("\\]", "").replaceAll(",", " "));
			Intent back = new Intent(drinkmenu.this, counting.class);

			startActivity(back);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	protected void onResume() {
		super.onResume();
		dtsrc.open();
	}

	protected void onPause() {
		super.onPause();
		dtsrc.close();
	}

	private void createdata(String all) {
		if (all.equals(""))
			return;
		else {
			int a = all.indexOf(',');
			if (a == -1)
				return;
			String name = all.substring(0, a);
			double cont = Double.parseDouble(all.substring(a + 1,a+3));
			drinkval d1 = new drinkval();
			d1.setname(name);
			d1.setalval(cont);
			Log.i(sqldb1.LOGTAG, "Drink created with id " + d1.getid());
			dtsrc.create(d1);

		}
	}

}
