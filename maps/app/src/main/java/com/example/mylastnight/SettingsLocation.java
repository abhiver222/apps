package com.example.mylastnight;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.view.View.OnClickListener;

public class SettingsLocation extends FragmentActivity implements OnClickListener{

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private googleMapsInitializer initializer;
    public LatLng home;
    Location loca;
    public double lat;
    public double lon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_location);

        Bundle extras = getIntent().getExtras();
        if(extras!=null)
            loca=extras.getParcelable("CURRENT_LOCATION");

        initializer = new googleMapsInitializer(this,this);
        initializer.onCreate(savedInstanceState);
        initializer.onStart();

        Button setashomebutton = (Button) findViewById(R.id.setashomebutton);
        setashomebutton.setOnClickListener(this);

        SharedPreferences sphome = getSharedPreferences("MYHOME",0);
        lat = sphome.getFloat("lat",0f);
        lon = sphome.getFloat("long",0f);


        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initializer.onStart();
        setUpMapIfNeeded();
    }

    @Override
    protected void onStop(){
        super.onStop();
        initializer.onStop();
        SharedPreferences sphome = getSharedPreferences("MYHOME",0);
        SharedPreferences.Editor editor = sphome.edit();
        editor.putFloat("lat",(float)lat);
        editor.putFloat("long",(float)lon);
        editor.commit();
    }
    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map1)).getMap();

            //WHY DOES THIS HAVE TO BE MapFragment vs SupportMapFragment?

            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.setMyLocationEnabled(true);

        LatLng home = new LatLng(lat,lon);
        mMap.addMarker(new MarkerOptions().position(home).title("Home"));

        Location mlocation = initializer.getCurrentLocation();

        if (mlocation == null && loca !=null)
        MoveMap(loca);
        else if (mlocation != null)
        {MoveMap(mlocation);}

    }
    public void MoveMap(Location loc){


        if (loc==null)
            System.out.println("It's Null.");

        else {LatLng current;
        current = new LatLng(loc.getLatitude(),loc.getLongitude());

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(current, 15f));}
    }

    public LatLng setmyhome(){

        Location loca = initializer.getCurrentLocation();
        return new LatLng(loca.getLatitude(),loca.getLongitude());
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.setashomebutton)
           addHomeMarker();
            Intent back = new Intent(SettingsLocation.this,SettingsMain.class);
            startActivity(back);
    }

    public void addHomeMarker(){
        mMap.clear();

        Location loca = initializer.getCurrentLocation();
        lat=loca.getLatitude();
        lon=loca.getLongitude();

        LatLng home = new LatLng(lat,lon);
        mMap.addMarker(new MarkerOptions().position(home).title("Home"));
    }
}
