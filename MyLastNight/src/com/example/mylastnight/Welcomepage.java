package com.example.mylastnight;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Welcomepage extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcomepage);
		
		//Setting up the three main navigation buttons
		Button mylog = (Button) findViewById(R.id.mylog);
		Button moments = (Button) findViewById(R.id.moments);
		Button settings = (Button) findViewById(R.id.settings);
		
		mylog.setOnClickListener(this);
		moments.setOnClickListener(this);
		settings.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.welcomepage, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.mylog){
			Intent log = new Intent(Welcomepage.this,myLog.class);
			startActivity(log);
			
		}
		else if(v.getId()==R.id.moments){
			Intent moments = new Intent(Welcomepage.this,moments.class);
			startActivity(moments);
		}
		else if(v.getId()==R.id.settings){
			Intent settings = new Intent(Welcomepage.this,settingactivity.class);
			startActivity(settings);
		}

		
	}
	
	// Trying to delete the shared preferences if the application ends
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		SharedPreferences prefs = getApplicationContext().getSharedPreferences("prefs", 0);
		Editor edt = prefs.edit();
		edt.clear();
		edt.commit();
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		SharedPreferences prefs = getApplicationContext().getSharedPreferences("prefs", 0);
		Editor edt = prefs.edit();
		edt.commit();
	}

}
