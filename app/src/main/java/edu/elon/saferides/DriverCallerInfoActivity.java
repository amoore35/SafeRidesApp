package edu.elon.saferides;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class DriverCallerInfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_caller_info);
    }
    public void onClickAccept (View view){
        Intent intent = new Intent(this, AcceptedCallerActivity.class);
        startActivity(intent);
    }

}
