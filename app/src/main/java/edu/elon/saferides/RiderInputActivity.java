package edu.elon.saferides;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.net.HttpURLConnection;
import java.net.URLEncoder;


public class RiderInputActivity extends Activity implements OnMapReadyCallback {

    private EditText nameInput;
    private EditText phoneNumberInput;

    private LatLng here;

    private String phoneText, nameText, longitude, latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_input);

        nameInput = (EditText) findViewById(R.id.name_input);
        phoneNumberInput = (EditText) findViewById(R.id.phone_number_input);
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap map){
        here = new LatLng(-33.867,151.206);

        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(here, 13));

        map.addMarker(new MarkerOptions().title("here").position(here));


    }

    public void onClickNext(View view){

        phoneText = phoneNumberInput.getText().toString();
        nameText = nameInput.getText().toString();

        longitude = String.valueOf(here.longitude);
        latitude = String.valueOf(here.latitude);

        Intent intent = new Intent(this, RiderInfoCheckActivity.class);
        intent.putExtra("nameInput", nameText);
        intent.putExtra("phoneNumberInput", phoneText);
        intent.putExtra("longitude", longitude);
        intent.putExtra("latitude", latitude);

        startActivity(intent);

    }


}