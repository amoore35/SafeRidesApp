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

    private final String baseURL = "http://trumpy.cs.elon.edu:5000/rides";

    private String name, phoneNumber, latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_user);
    }

    public void onClickDriver(View view) {
        new GetRider().execute();
    }

    public void onClickRider(View view) {
        Intent intent = new Intent(this, RiderInputActivity.class);
        startActivity(intent);
    }



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

                System.out.println("response -" + response);


                String[] info = response.split(",");
                phoneNumber = info[0];
                name = info[1];
                latitude = info[2];
                longitude = info[3];


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
            String[] info = response.split(",");
            phoneNumber = info[0];
            name = info[1];
            latitude = info[2];
            longitude = info[3];

            Intent intent = new Intent(getApplicationContext(), DriverCallerInfoActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("phoneNumber", phoneNumber);
            intent.putExtra("latitude", latitude);
            intent.putExtra("longitude", longitude);

            startActivity(intent);
        }
    }

    }

