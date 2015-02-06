package com.example.mylastnight;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class myLog extends Welcomepage implements OnClickListener{

    Location currentLoc;
	/**
	 * @param args
	 */
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mylog);  //layout.mylog
		//Toast.makeText(this, "This page would help you log whatever activity you would " +
				//"be doing in today's party.", Toast.LENGTH_LONG).show();

        Bundle extras = getIntent().getExtras();
        if(extras!=null)
            currentLoc=extras.getParcelable("CURRENT_LOCATION");

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

}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.counter){
			Intent count = new Intent(myLog.this,counting.class);
			startActivity(count);
		}
		else if(v.getId()==R.id.base){
			Intent base = new Intent(myLog.this,base.class);
			startActivity(base);
		}
		else if(v.getId()==R.id.maps){
			Intent maps = new Intent(myLog.this,maps.class);
            maps.putExtra("CURRENT_LOCATION",Main.getCurrentLocation());
            startActivity(maps);
		}
	}
}