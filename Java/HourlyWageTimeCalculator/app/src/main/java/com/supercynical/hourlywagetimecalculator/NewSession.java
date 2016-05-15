package com.supercynical.hourlywagetimecalculator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInstaller;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;

public class NewSession extends AppCompatActivity implements View.OnClickListener {

    private SessionData sessionData = new SessionData();
    private Chronometer timer;
    private double wageAmt;
    private static final String TAG = "SessionMsg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_session);

        // Initialize components
        timer = (Chronometer) findViewById(R.id.chronometer);
        Button startButton = (Button) findViewById(R.id.startButton);
        Button stopButton = (Button) findViewById(R.id.stopButton);
        Button clearButton = (Button) findViewById(R.id.clearButton);
        TextView statusTextView = (TextView) findViewById(R.id.timerStatusText);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set default values
        statusTextView.setText(R.string.timerStoppedText);
        timer.setBase(SystemClock.elapsedRealtime());

        // Set Button OnClickListeners
        startButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);
        clearButton.setOnClickListener(this);

        // Get wage rate amount from SharedPreferences
        wageAmt = LoadSettings();
    }

    public double LoadSettings() {
        SharedPreferences preferences = getSharedPreferences("defaultWageAmt", MODE_PRIVATE);
        float wageAmtFloat = preferences.getFloat("defaultWageAmt", 10.00f);
        double wageAmtDouble;
        DecimalFormat df = new DecimalFormat("#.00");

        // Preference variable comes in as a String, so convert to double
        // and return in two decimal format
        wageAmtDouble = (double)wageAmtFloat;
        return Double.valueOf(df.format(wageAmtDouble));
    }

    // Toolbar options item - inflate menu (menu settings not implemented yet)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    // Start Settings activity
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity(new Intent(NewSession.this, SettingsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        // Initialize TextViews
        TextView statusTextView = (TextView) findViewById(R.id.timerStatusText);
        TextView atRateText = (TextView) findViewById(R.id.atRateText);
        TextView totalChargeAmtText = (TextView) findViewById(R.id.totalChargeAmtText);

        // Get hourly rate again
        wageAmt = LoadSettings();

        // Switch statement for each button click
        switch(v.getId()) {
            // Start Button
            case R.id.startButton:
                int stoppedMilliseconds = 0;

                // Get Chronometer (timer) reading and insert into an array
                String timerText = timer.getText().toString();
                String array[] = timerText.split(":");

                // If no hour count, only MM:SS
                if (array.length == 2) {
                    stoppedMilliseconds = Integer.parseInt(array[0]) * 60 * 1000
                            + Integer.parseInt(array[1]) * 1000;
                }
                // With hour count, HH:MM:SS
                else if (array.length == 3) {
                    stoppedMilliseconds = Integer.parseInt(array[0]) * 60 * 60 * 1000
                            + Integer.parseInt(array[1]) * 60 * 1000
                            + Integer.parseInt(array[2]) * 1000;
                }

                // Clear total charge amount and start timer.
                // There is a problem with Chronometer, it will keep counting even if you stop
                // the timer. The only solution is to get the amount of time between stopping time
                // and net starting time, and subtract that from Base time before starting again.
                // This is a workaround instead of subclassing Chronometer.
                totalChargeAmtText.setText("");
                timer.setBase(SystemClock.elapsedRealtime() - stoppedMilliseconds);
                timer.start();
                statusTextView.setText(R.string.timerStartedText);
                break;

            // Stop Button
            case R.id.stopButton:
                // Stop timer, set Stopped Timer text, set hourly rate text, initialize variables
                timer.stop();
                DecimalFormat df = new DecimalFormat("#.00");
                statusTextView.setText(R.string.timerStoppedText);
                atRateText.setText("at $" + df.format(wageAmt) + " /hr");
                double wageAmtMinute = wageAmt / 60;
                double totalChargeAmt = 0.0;
                double totalMinutes;

                // Get Chronometer reading and split into an array
                timerText = timer.getText().toString();
                // Use code below to set a custom time instead of having to wait
                //timerText = "01:25:50";
                array = timerText.split(":");

                // Debug log for array length (MM:SS or HH:MM:SS)
                //Log.d(TAG, String.valueOf(array.length));

                // If no hour count, only MM:SS
                if (array.length == 2) {
                    totalMinutes = Double.valueOf(array[0]) +
                            Double.valueOf(array[1]) / 60;
                    totalChargeAmt = totalMinutes * wageAmtMinute;
                    // If the timer is below one minute, do not calculate time and just set to 0
                    if (Integer.parseInt(array[0]) == 0 && Integer.parseInt(array[1]) <= 59) {
                        totalChargeAmt = 0.00;
                    }
                }
                // With hour count, HH:MM:SS
                else if (array.length == 3) {
                    totalMinutes = Double.valueOf(array[2]) / 60 +
                            Double.valueOf(array[1]) + Double.valueOf(array[0]) * 60;
                    totalChargeAmt = totalMinutes * wageAmtMinute;
                }

                totalChargeAmtText.setText("$" + String.valueOf(df.format(totalChargeAmt)));

                // Now log the session
                Date d = new Date();
                sessionData.writeToFile(d + " - $" + String.valueOf(df.format(totalChargeAmt) + "\n"),
                        NewSession.this);
                break;

            // Clear Button
            case R.id.clearButton:
                // Stop timer, reset text and timer values
                timer.stop();
                statusTextView.setText(R.string.timerStoppedText);
                totalChargeAmtText.setText("");
                atRateText.setText("");
                timer.setBase(SystemClock.elapsedRealtime());
        }
    }

}
