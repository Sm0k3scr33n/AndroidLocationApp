package com.example.bengabbard.location;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    Button show;
    TextView la, lon, msg;
    LocationManager locationManager;
    String PROVIDER = LocationManager.GPS_PROVIDER;

    private void showLocation() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        View linearLayout = findViewById(R.id.layout1);
        show = (Button) findViewById(R.id.btn);
        msg= (TextView)findViewById(R.id.msg);
        la = (TextView) findViewById(R.id.textview1);
        lon = (TextView) findViewById(R.id.textview2);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        final Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLocation(location);
            }
        });
        TextView valueTV = new TextView(this);
        msg.setText("Welcome To The Location Application");
        valueTV.setId(5);
        valueTV.setLayoutParams(new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.WRAP_CONTENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT));

        ((LinearLayout) linearLayout).addView(valueTV);

    }

    private void showLocation(Location location) {
        if(location==null){

            Toast.makeText(getApplicationContext(),"Currently you are located at:",Toast.LENGTH_LONG).show();
        }else{
            la.setText("Lat  : "+location.getLatitude());
            lon.setText("Long : "+location.getLongitude());
        }
    }


    private LocationListener listener=new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
        showLocation(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {
;
        }
    };
    @Override
    protected void onPause(){

        super.onPause();
        locationManager.removeUpdates(listener);
    }
    @Override
    protected void onResume (){
        super.onResume();
        locationManager.requestLocationUpdates(PROVIDER, 0, 0, listener);


    }
}
