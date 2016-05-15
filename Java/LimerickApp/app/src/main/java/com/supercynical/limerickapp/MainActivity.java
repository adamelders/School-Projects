package com.supercynical.limerickapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String strCity;
    String strOccupation;
    int intNumber;
    String strVerb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText cityName = (EditText)findViewById(R.id.cityNameText);
        final EditText occupation = (EditText)findViewById(R.id.occupationText);
        final EditText number = (EditText)findViewById(R.id.numberText);
        final EditText verb = (EditText)findViewById(R.id.verbText);
        Button createButton = (Button)findViewById(R.id.createButton);
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    strCity = cityName.getText().toString();
                    strOccupation = occupation.getText().toString();
                    intNumber = Integer.parseInt(number.getText().toString());
                    strVerb = verb.getText().toString();
                }
                catch (Exception ex)
                {
                    Toast.makeText(MainActivity.this, "Please enter valid information.", Toast.LENGTH_SHORT).show();
                }

                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("keyCity", strCity);
                editor.putString("keyOccupation", strOccupation);
                editor.putInt("keyNumber", intNumber);
                editor.putString("keyVerb", strVerb);
                editor.commit();

                startActivity(new Intent(MainActivity.this, LimerickActivity.class));
            }
        });
    }
}
