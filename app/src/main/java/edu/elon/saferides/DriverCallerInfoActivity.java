package edu.elon.saferides;
import android.app.Activity;
import android.content.Intent;
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

    private String name, location, latitude, longitude;

    private TextView nameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_caller_info);

        nameView = (TextView) findViewById(R.id.caller_name);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        nameView.setText(name);


    }

    public void onClickAccept (View view){
        Intent intent = new Intent(this, AcceptedCallerActivity.class);
        startActivity(intent);
    }

}
