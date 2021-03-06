package com.example.mylastnight;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

// This class is for Creating the myLog menu from where you can switch to different parts of the application

public class myLog extends Activity implements OnClickListener{

	/**
	 * @param args
	**/
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mylog);
		//Toast.makeText(this, "This page would help you log whatever activity you would " +
				//"be doing in today's party.", Toast.LENGTH_LONG).show();
		
		 final Toast toast = Toast.makeText(this, "This page would help you log whatever activity you would " +
				"be doing in today's party", Toast.LENGTH_LONG);
	        toast.show();

	        Handler handler = new Handler();
	            handler.postDelayed(new Runnable() {
	               @Override
	               public void run() {
	                   toast.cancel(); 
	               }
	        }, 3000);
		
		
		Button drinkcounter = (Button) findViewById(R.id.counter);
		Button base = (Button) findViewById(R.id.base);
		Button maps = (Button) findViewById(R.id.maps);
		
		drinkcounter.setOnClickListener(this);
		base.setOnClickListener(this);
		maps.setOnClickListener(this);
		SharedPreferences prefs = getApplicationContext().getSharedPreferences("prefs", 0);
		Editor edt = prefs.edit();
		edt.putString("water", "water");

}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.counter){
			Intent count = new Intent(myLog.this,counting.class);
			Intent serv= new Intent(myLog.this, serviceone.class);
			//String[] arr = new String[0];
			//count.putExtra("vals", arr);
			startService(serv);
			startActivity(count);
		}
		else if(v.getId()==R.id.base){
			Intent base = new Intent(myLog.this,base.class);
			startActivity(base);
		}
		else if(v.getId()==R.id.maps){
			Intent maps = new Intent(myLog.this,maps.class);
			startActivity(maps);
		}
	}
}