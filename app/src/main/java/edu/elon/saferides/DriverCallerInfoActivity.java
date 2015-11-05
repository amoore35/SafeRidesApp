package edu.elon.saferides;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DriverCallerInfoActivity extends Activity {

    private String name, phoneNumber, latitude, longitude;

    private TextView nameView, numberView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_caller_info);


        nameView = (TextView) findViewById(R.id.caller_name);
        numberView = (TextView) findViewById(R.id.caller_number);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        phoneNumber = intent.getStringExtra("phoneNumber");
        latitude = intent.getStringExtra("latitude");
        longitude = intent.getStringExtra("longitude");

        nameView.setText(name);
        numberView.setText(phoneNumber);

        nameView.invalidate();
    }



    public void onClickAccept (View view){
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?daddr=" + latitude + "," + longitude));
        startActivity(intent);
    }

}
