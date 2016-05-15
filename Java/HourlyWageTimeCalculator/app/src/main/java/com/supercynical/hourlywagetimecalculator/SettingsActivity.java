package com.supercynical.hourlywagetimecalculator;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        EditText hourlyRateSetting = (EditText) findViewById(R.id.hourlyRateSetting);
        Button saveButton = (Button) findViewById((R.id.saveSettingsButton));
        saveButton.setOnClickListener(this);


        hourlyRateSetting.setText(LoadSettings());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.saveSettingsButton:
                EditText hourlyRateSetting = (EditText) findViewById(R.id.hourlyRateSetting);
                Double hourlyRate = Double.parseDouble(hourlyRateSetting.getText().toString());
                SaveSettings(hourlyRate);
                Toast.makeText(SettingsActivity.this, "Settings Applied", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public String LoadSettings() {
        SharedPreferences preferences = getSharedPreferences("defaultWageAmt", MODE_PRIVATE);
        float wageAmtFloat = preferences.getFloat("defaultWageAmt", 10.00f);
        double wageAmtDouble;
        DecimalFormat df = new DecimalFormat("#.00");

        // Preference variable comes in as a String, so convert to double
        // and return in two decimal format
        wageAmtDouble = (double)wageAmtFloat;
        return df.format(wageAmtDouble);
    }

    public void SaveSettings(Double hourlyRate) {
        SharedPreferences preferences = getSharedPreferences("defaultWageAmt", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putFloat("defaultWageAmt", hourlyRate.floatValue());
        editor.commit();
    }
}
