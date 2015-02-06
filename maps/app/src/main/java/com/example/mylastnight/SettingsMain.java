package com.example.mylastnight;

import android.location.Location;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Created by benja_000 on 11/13/2014.
 */
public class SettingsMain extends Welcomepage {
    Location currentLoc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settingsmain);

        Bundle extras = getIntent().getExtras();
        if(extras!=null)
            currentLoc=extras.getParcelable("CURRENT_LOCATION");

        Button setmyhome = (Button) findViewById(R.id.setmyhome);
        Button accountsettings = (Button) findViewById(R.id.accountsettings);
        Button shackin = (Button) findViewById(R.id.shackin);

        setmyhome.setOnClickListener(this);
        accountsettings.setOnClickListener(this);
        shackin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        //if (v.getId() == R.id.mylog) {
            //Intent log = new Intent(Welcomepage.this, myLog.class);
            //startActivity(log);
        //}
        if(v.getId() == R.id.setmyhome){
            Intent setmyhome = new Intent(SettingsMain.this, SettingsLocation.class);
            setmyhome.putExtra("CURRENT_LOCATION",Main.getCurrentLocation());
            startActivity(setmyhome);
        }
        if(v.getId() == R.id.accountsettings){
            Intent accountsettings = new Intent(SettingsMain.this, settingactivity.class);
            startActivity(accountsettings);
        }
        if(v.getId()==R.id.shackin){
            Intent shackin = new Intent(SettingsMain.this, shackin.class);
            startActivity(shackin);
        }
    }
}
