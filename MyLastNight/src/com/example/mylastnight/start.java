package com.example.mylastnight;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class start extends Activity implements OnClickListener{
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.starting);
		Button b = (Button) findViewById(R.id.bottle);
		b.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.bottle){
			Intent first = new Intent(start.this,Welcomepage.class);
			startActivity(first);
			
		}
			
	}
}
