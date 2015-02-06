package com.example.mylastnight;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Welcomepage extends Activity implements OnClickListener {

    googleMapsInitializer Main;

	@Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_welcomepage);

        Main = new googleMapsInitializer(this,this);
        Main.onCreate(savedInstanceState);

		Button turnup = (Button) findViewById(R.id.turnup);
		Button mylog = (Button) findViewById(R.id.mylog);
		Button settings = (Button) findViewById(R.id.settings);
		
		mylog.setOnClickListener(this);
		turnup.setOnClickListener(this);
		settings.setOnClickListener(this);
		
	}

    @Override
    public void onStart(){
        super.onStart();
        Main.onStart();

    }
    @Override
    public void onStop(){
        super.onStop();
        Main.onStop();
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
		if(v.getId()==R.id.turnup){
			Intent log = new Intent(Welcomepage.this,myLog.class);
            log.putExtra("CURRENT_LOCATION",Main.getCurrentLocation());
            startActivity(log);
		}
		else if(v.getId()==R.id.mylog){
			Intent moments = new Intent(Welcomepage.this,moments.class);
			startActivity(moments);
		}
		else if(v.getId()==R.id.settings){
			Intent settings = new Intent(Welcomepage.this,SettingsMain.class);
			settings.putExtra("CURRENT_LOCATION",Main.getCurrentLocation());
            startActivity(settings);

		}

		
	}

}
