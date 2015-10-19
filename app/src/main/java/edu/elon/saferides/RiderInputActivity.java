package edu.elon.saferides;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class RiderInputActivity extends Activity {

    private EditText nameInput;
    private EditText phoneNumberInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_input);

        nameInput = (EditText) findViewById(R.id.name_input);
        phoneNumberInput = (EditText) findViewById(R.id.phone_number_input);
    }


    public void onClickNext(View view){
        Intent intent = new Intent(this, RiderInfoCheckActivity.class);
        intent.putExtra("nameInput", nameInput.getText().toString());
        intent.putExtra("phoneNumberInput", phoneNumberInput.getText().toString());

        startActivity(intent);

    }
}