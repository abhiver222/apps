package com.example.mylastnight;

    import android.content.Intent;
    import android.content.SharedPreferences;
    import android.location.Location;
    import android.net.Uri;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;
    import android.support.v4.app.FragmentActivity;

    import com.google.android.gms.maps.CameraUpdateFactory;
    import com.google.android.gms.maps.GoogleMap;
    import com.google.android.gms.maps.MapFragment;
    import com.google.android.gms.maps.SupportMapFragment;
    import com.google.android.gms.maps.model.LatLng;
    import com.google.android.gms.maps.model.Marker;

    public class maps extends FragmentActivity implements View.OnClickListener {

        private googleMapsInitializer mapss;
        private GoogleMap mMap;
        public Location loca;
        public Location previous;
        float lat;
        float lon;

    protected void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maps);

        mapss = new googleMapsInitializer(this,this);
        mapss.onCreate(savedInstanceState);
        mapss.onStart();

        SharedPreferences sphome = getSharedPreferences("MYHOME",0);
        lat = sphome.getFloat("lat",0f);
        lon = sphome.getFloat("long",0f);


        Bundle extras = getIntent().getExtras();
        if(extras!=null)
            previous=extras.getParcelable("CURRENT_LOCATION");

        loca = mapss.getCurrentLocation();

        Button takemehome = (Button) findViewById(R.id.takemehome);
        takemehome.setOnClickListener(this);

        setUpMapIfNeeded();
	}
    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();

    }

    @Override
    public void onStop(){
        super.onStop();

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.takemehome){
            Location current = mapss.getCurrentLocation();
            double currentlat = current.getLatitude();
            double currentlon = current.getLongitude();

            String url = "http://maps.google.com/maps?saddr="+currentlat+","+currentlon+"&daddr="+lat+","+lon+"&mode=walking";
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(url));
            intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
            startActivity(intent);

        }

    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map2)).getMap();

            //WHY DOES THIS HAVE TO BE MapFragment vs SupportMapFragment?

            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        mMap.setMyLocationEnabled(true);

       Location mlocation = mapss.getCurrentLocation();

      // if (mlocation == null && loca ==null)
      //    MoveMapH(previous);
       if (mlocation == null && loca!=null)
           MoveMapH(loca);
       else if (mlocation!=null)MoveMapH(mlocation);

    }

    public void MoveMapH(Location currentloc){

       LatLng current;
            current = new LatLng(currentloc.getLatitude(),currentloc.getLongitude());

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(current, 15f));
    }

}

