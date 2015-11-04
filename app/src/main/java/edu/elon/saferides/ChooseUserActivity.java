package edu.elon.saferides;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;

public class ChooseUserActivity extends Activity {

    // needed for the AGPS
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_user);
        // AGPS
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }

    public void onClickDriver(View view) {
        Intent intent = new Intent(this, DriverCallerInfoActivity.class);
        startActivity(intent);
    }

    public void onClickRider(View view) {
        Intent intent = new Intent(this, RiderInputActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onPause() {
        super.onPause();
        // AGPS
        locationManager.removeUpdates(locationListener);
    }

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

}
