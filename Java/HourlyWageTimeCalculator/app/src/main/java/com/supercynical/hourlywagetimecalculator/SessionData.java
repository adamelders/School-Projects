package com.supercynical.hourlywagetimecalculator;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SessionData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView prevSessionData = (TextView)findViewById(R.id.prevSessionDataTextView);

        prevSessionData.setText(readFromFile());
    }

    private String readFromFile() {
        File path = getFilesDir();
        File file = new File(path, "prev_session_data.txt");
        int length = (int)file.length();
        byte[] bytes = new byte[length];

        try {
            FileInputStream in = new FileInputStream(file);
            try {
                in.read(bytes);
            } finally {
                in.close();
            }
        } catch (Exception ex){
            Log.e("Exception", "Error reading from file: " + ex.toString());
        }

        return new String(bytes);
    }

    public void writeToFile(String data, Context context) {
        File path = context.getFilesDir();
        File file = new File(path, "prev_session_data.txt");
        try {
            FileOutputStream stream = new FileOutputStream(file, true);
            try {
                stream.write(data.getBytes());
            } finally {
                stream.close();
            }
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

}
