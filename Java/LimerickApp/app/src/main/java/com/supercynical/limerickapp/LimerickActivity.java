package com.supercynical.limerickapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.preference.PreferenceManager;
import android.widget.TextView;

public class LimerickActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_limerick);
        TextView resultText = (TextView)findViewById(R.id.limerickText);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String resultCity = prefs.getString("keyCity", "");
        String resultOccupation = prefs.getString("keyOccupation", "");
        int resultNumber = prefs.getInt("keyNumber", 0);
        String resultVerb = prefs.getString("keyVerb", "");

        // Create limerick text
        resultText.setText("A genius programmer from " + resultCity + "\n" +
            "Ditched their profession in " + resultOccupation + "\n" +
            "They learned coding in " + resultNumber + " days,\n" +
            "So they could " + resultVerb + " in a haze\n" +
            "Now they code without delays");
    }

}
