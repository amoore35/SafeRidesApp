package edu.elon.saferides;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.net.HttpURLConnection;
import java.net.URLEncoder;


public class RiderInputActivity extends Activity {

    private EditText nameInput;
    private EditText phoneNumberInput;

    private Button nextButton;

    private double lat, lon;

    private LocationManager locationManager;
    private String phoneText, nameText, latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_input);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        nameInput = (EditText) findViewById(R.id.name_input);
        phoneNumberInput = (EditText) findViewById(R.id.phone_number_input);
        nextButton = (Button) findViewById(R.id.next_button_wait);

        nextButton.setEnabled(false);
    }

    public void onClickNext(View view){

        phoneText = phoneNumberInput.getText().toString();
        nameText = nameInput.getText().toString();

        Intent intent = new Intent(this, RiderInfoCheckActivity.class);
        intent.putExtra("nameInput", nameText);
        intent.putExtra("phoneNumberInput", phoneText);
        intent.putExtra("longitude", longitude);
        intent.putExtra("latitude", latitude);

        startActivity(intent);

    }

    @Override
    protected void onPause() {
        super.onPause();
        // AGPS
        locationManager.removeUpdates(locationListener);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // AGPS
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
    }

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            lat = location.getLatitude();
            lon = location.getLongitude();
            nextButton.setEnabled(true);

            int color = Color.parseColor("#7fbf7f");
            nextButton.setBackgroundColor(color);

            latitude = String.valueOf(lat);
            longitude = String.valueOf(lon);
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