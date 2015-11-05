package edu.elon.saferides;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ChooseUserActivity extends Activity {

    // needed for the AGPS
    private LocationManager locationManager;

    private final String baseURL = "http://trumpy.cs.elon.edu:5000/rides";

    private String name, phoneNumber, latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_user);
        // AGPS
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }

    public void onClickDriver(View view) {
        Intent intent = new Intent(this, DriverCallerInfoActivity.class);

        new GetRider().execute();

        intent.putExtra("name", name);

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

    private class GetRider extends AsyncTask<Void, Void, Void> {
        String response = "";

        @Override
        protected Void doInBackground(Void ... voids) {

            HttpURLConnection urlConnection = null;
            try {
                // connect to webserver (GET)
                URL url = new URL(baseURL + "/get");
                urlConnection = (HttpURLConnection) url.openConnection();

                // read the response
                InputStream in = new BufferedInputStream((urlConnection.getInputStream()));
                Scanner sc = new Scanner(in);
                response = sc.nextLine();
                name = response;

                // used to allow UI to update the TextView
                publishProgress();

            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                // always disconnect
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
            return null;
        }

        // This is allowed to call UI objects
        @Override
        protected void onProgressUpdate(Void ... voids) {
            name = response;
        }
    }

    }

