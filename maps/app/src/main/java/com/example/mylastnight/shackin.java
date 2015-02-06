package com.example.mylastnight;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by benja_000 on 11/13/2014.
 */
public class shackin extends SettingsMain {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shackin);

        Button shack = (Button) findViewById(R.id.shack);

        shack.setOnClickListener(this);
    }

    public void onClick(View v) {
               // if (v.getId()==shack)
                    //THIS IS WHERE WE CAN TURN OFF THE TIMER

    }
}
