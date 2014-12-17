package com.example.mylastnight;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

public class SensorActivity extends Activity implements SensorEventListener {



    private TextView textView;

    private SensorManager mSensorManager;

    private Sensor  mAccelerometer;
    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 500;
    //Stores the minimum amount of change in amount required to trigger the sensor


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base);
        // textView = (TextView) findViewById(R.id.textview);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mAccelerometer , SensorManager.SENSOR_DELAY_NORMAL); //registers sensor

    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // not required, but needs to be implemented
    }
    public void onSensorChanged(SensorEvent sensorEvent) { //executed when movement detected
        Sensor mySensor = sensorEvent.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) { // double-check to ensure accelerometer is being used
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            long curTime = System.currentTimeMillis();

            if ((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                float speed = Math.abs(x + y + z - last_x - last_y - last_z)/ diffTime * 10000;
                CountDownTimer cs=new CounterClass(270000,1000);
                if (speed > SHAKE_THRESHOLD) { //if movement is greater than minimum threshold


                    cs.cancel(); // cancels the previous timer because movement detected
                    cs.start(); // restarts the timer

                }
                else cs.onFinish();

                last_x = x;
                last_y = y;
                last_z = z;
            }
        }}

    protected void onResume() {

        super.onResume();

        mSensorManager.registerListener(this, mAccelerometer,

                SensorManager.SENSOR_DELAY_FASTEST);


    }

    protected void onStop() {
        super.onStop();

        mSensorManager.unregisterListener(this, mAccelerometer);
    }
}