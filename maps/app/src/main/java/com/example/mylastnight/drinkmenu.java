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
	int pos,i=0;
	String name;
	String[] names = {" "," "," "," "," "," "," "," "," "," "," "};
	boolean first = true;
	int size=1;
	
	datasource dtsrc;
	
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drinkmenu);
		
		commonnames = (Spinner) findViewById(R.id.spinner1);
		specialnames = (Spinner) findViewById(R.id.spinner2);
		display = (TextView) findViewById(R.id.selected);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		ArrayAdapter<CharSequence> cname = ArrayAdapter.createFromResource(this,R.array.commonmenudisplay, android.R.layout.simple_spinner_item);
		ArrayAdapter<CharSequence> spname = ArrayAdapter.createFromResource(this,R.array.specialmenudisplay, android.R.layout.simple_spinner_item);
		
		//ArrayAdapter<CharSequence> cname = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item,R.array.commonmenudisplay);
		//ArrayAdapter<CharSequence> spname = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item,R.array.specialmenudisplay);
		
		//ArrayAdapter<String> cname = new ArrayAdapter<String>(drinkmenu.this,R.array.commonmenudisplay,android.R.layout.simple_spinner_item);
		//ArrayAdapter<String> spname = new ArrayAdapter<String>(drinkmenu.this, R.array.specialmenudisplay,android.R.layout.simple_spinner_item);

		//cname.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		//spname.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       		
		commonnames.setAdapter(cname);
		specialnames.setAdapter(spname);
		
		specialnames.setOnItemSelectedListener(this);
		dtsrc = new datasource(this);
		dtsrc.open();
		
		//List<drinkval> drinks = dtsrc.findall();//put where ever data is needed
		
		
		
	}

	@Override
	public void onItemSelected(AdapterView<?> spin, View view, int position,
			long a) {
		
		drinkmenu.this.pos = position;
		drinkmenu.this.name = spin.getItemAtPosition(pos).toString();
		String name = drinkmenu.this.name; 
		if(first){
			names[i]=(drinkmenu.this.name);
			display.setText(Arrays.toString(names).replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(",", " "));
			i++;
			first = false;
		}
		else{
		names[i]=(drinkmenu.this.name+" + ");
		display.setText(Arrays.toString(names).replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(",", " "));
		i++;
		}
		
		createdata(name);
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.topactionbuttons, menu);
        return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    size++;
		switch (item.getItemId()) {
	        case R.id.action_settings:
	        	for(int i=0;i<names.length;i++)
	        		names[i]=" ";
	        	
	        	
	        	
	            display.setText(Arrays.toString(names).replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(",", " "));
	            Intent back = new Intent(drinkmenu.this,counting.class);
	            back.putExtra("values",size);
	            startActivity(back);
	            return true;
	            
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	protected void onResume(){
		super.onResume();
		dtsrc.open();
	}
	
	protected void onPause(){
		super.onPause();
		dtsrc.close();
	}
	
	private void createdata(String all){
        if(all.equals(""))
            return;
        else {
            int a = all.indexOf(',');
            String name = all.substring(0, a);
            double cont = Double.parseDouble(all.substring(a + 1));
            drinkval d1 = new drinkval();
            d1.setname(name);
            d1.setalval(cont);
            Log.i(sqldb1.LOGTAG, "Drink created with id " + d1.getid());
            dtsrc.create(d1);
        }
	}

}
