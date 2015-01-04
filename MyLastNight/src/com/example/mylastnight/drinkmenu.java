package com.example.mylastnight;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Parcelable;
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

		SharedPreferences pref = getApplicationContext().getSharedPreferences("prefs", 0);
		Editor edt = pref.edit();
		switch (spin.getId()) {
		case R.id.spinner1:
			drinkmenu.this.name = spin.getItemAtPosition(position).toString();
			String name1 = drinkmenu.this.name;
			if(name1.equals("")||name1==null)break;;
			int index1 = name1.indexOf(' ');
			int index2 = name1.indexOf(',');
			String drinkname = name.substring(0,index1);
			String ret = name1.substring(0,index1) + "," +name1.substring(index2);
			edt.putString(drinkname, ret);
			//createdrink(name1);
			//createdata(name);

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
			if(name.equals("")||name==null)break;;
			int index11 = name.indexOf(' ');
			int index22 = name.indexOf(',');
			String drinkname1 = name.substring(0,index11);
			String ret1 = name.substring(0,index11) + "," +name.substring(index22);
			edt.putString(drinkname1, ret1);
			edt.commit();
			// createdata(name); remove if wanted
		}// end of switch
	}

	private static void createdrink(String name1) {
		// TODO Auto-generated method stub
		if(name1.equals("")||name1==null)return;
		int index1 = name1.indexOf(' ');
		int index2 = name1.indexOf(',');
		String ret = name1.substring(0,index1) + "," +name1.substring(index2);
		
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
			
			
			
			
			/*ArrayList<drinkval> drinks =  (ArrayList<drinkval>) dtsrc.findall();//put where ever data is
			String[] arr = new String[drinks.size()];
			Iterator<drinkval> it = drinks.iterator();
			int i=0;
			while(it.hasNext()){
				drinkval d = it.next();
				arr[i] = d.getname()+ " " + d.getalval();
				i++;
			}*/
			Intent back = new Intent(drinkmenu.this, counting.class);
			/*Log.i("gotem", "Got the drinks now the intent");
			//Bundle bund = new Bundle();
			//bund.putSerializable("bund",drinks);
			Log.i("gotembund", "Got the bundle");
			//back.putExtra("list",bund);
			Log.i("gotem", "put extra");
			back.putExtra("vals",arr);*/
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
		if (all.equals("")||all==null)
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
			d1 = dtsrc.create(d1);
			Log.i("any","Drink created with id"+d1.getid());
		}
	}

}
