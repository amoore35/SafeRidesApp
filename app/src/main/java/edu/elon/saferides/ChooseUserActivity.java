package edu.elon.saferides;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;

public class ChooseUserActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_user);
    }

    public void onClickDriver(View view){
        Intent intent = new Intent(this, DriverCallerInfoActivity.class);
        startActivity(intent);
    }

    public void onClickRider(View view){
        Intent intent = new Intent(this, RiderInputActivity.class);
        startActivity(intent);

    }
}
