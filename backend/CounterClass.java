package com.example.mylastnight;

import java.util.concurrent.TimeUnit;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

public class CounterClass extends CountDownTimer { //CountDownTimer is a timer class in the android API
    TextView textViewTime;

    RingAlarm setoff = new RingAlarm();
    public CounterClass(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval); }

    @Override public void onFinish() {
        textViewTime.setText("Completed.");
	/* I will meet with Reid after the break to get his code for the alarm and call those methods here to complete
	this method*/
    setoff.onCreate(new Bundle());


    }


    @SuppressLint("NewApi")
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)

    @Override public void onTick(long millisUntilFinished) {
        long millis = millisUntilFinished;
        String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis), TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
        System.out.println(hms); textViewTime.setText(hms); }



}