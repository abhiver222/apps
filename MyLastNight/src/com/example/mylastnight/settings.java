package com.example.mylastnight;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.widget.Toast;

public class settings extends PreferenceFragment{

	// Creates the settings template and storing them in a shared preference. Pulling preferences from settingspref.xml
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settingprefs);
		
	}

}