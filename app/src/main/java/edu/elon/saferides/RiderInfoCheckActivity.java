package edu.elon.saferides;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class RiderInfoCheckActivity extends Activity {

    private TextView phoneView;
    private TextView nameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_info_check);

        phoneView = (TextView) findViewById(R.id.phone_view);
        nameView = (TextView) findViewById(R.id.name_view);
        Intent intent = getIntent();
        String name = intent.getStringExtra("nameInput");
        String number = intent.getStringExtra("phoneNumberInput");
        nameView.setText(name);
        phoneView.setText(number);
    }

    public void onClickNext(View view){
        Intent intent = new Intent(this, RiderRequestSentActivity.class);

        startActivity(intent);

    }


}
