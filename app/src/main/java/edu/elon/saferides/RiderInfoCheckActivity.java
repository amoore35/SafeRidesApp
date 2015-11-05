package edu.elon.saferides;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;


public class RiderInfoCheckActivity extends Activity {

    private TextView phoneView;
    private TextView nameView;

    private String phoneText, nameText, latitude, longitude;

    private final String baseURL = "http://trumpy.cs.elon.edu:5000/rides";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_info_check);

        phoneView = (TextView) findViewById(R.id.phone_view);
        nameView = (TextView) findViewById(R.id.name_view);
        Intent intent = getIntent();
        nameText = intent.getStringExtra("nameInput");
        phoneText = intent.getStringExtra("phoneNumberInput");
        nameView.setText(nameText);
        phoneView.setText(phoneText);
        latitude = intent.getStringExtra("latitude");
        longitude = intent.getStringExtra("longitude");
    }

    public void onClickNext(View view){
        new RequestRide().execute();
        Intent intent = new Intent(this, RiderRequestSentActivity.class);



        startActivity(intent);

    }

    private class RequestRide extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            HttpURLConnection urlConnection = null;
            try {
                String param = "phone=" + URLEncoder.encode(phoneText, "UTF-8") +
                        "&name=" + URLEncoder.encode(nameText, "UTF-8") +
                        "&latitude=" + URLEncoder.encode(latitude, "UTF-8") +
                        "&longitude=" + URLEncoder.encode(longitude, "UTF-8");

                System.out.println(param);
                URL url = new URL(baseURL + "/request?" + param);


                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setDoOutput(true);
                urlConnection.setRequestMethod("POST");

                String response = "";
                Scanner sc = new Scanner(urlConnection.getInputStream());

                while (sc.hasNextLine()) {
                    response += sc.nextLine();
                }
            } catch (IOException ex) {
                ex.printStackTrace();

            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
            return null;
        }
    }

}
