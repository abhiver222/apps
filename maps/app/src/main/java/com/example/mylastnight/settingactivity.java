package com.example.mylastnight;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class settingactivity extends Activity{

	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		 
		
		  final Toast toast = Toast.makeText(this, "Manage your preferences", Toast.LENGTH_SHORT);
	        toast.show();

	        Handler handler = new Handler();
	            handler.postDelayed(new Runnable() {
	               @Override
	               public void run() {
	                   toast.cancel(); 
	               }
	        }, 1500);
		
		getFragmentManager().beginTransaction()
         .replace(android.R.id.content, new settings())
         .commit();
	}

}
