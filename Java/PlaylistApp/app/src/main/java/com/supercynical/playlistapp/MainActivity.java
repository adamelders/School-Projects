package com.supercynical.playlistapp;

import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btKoze, btFlight, btLedZep;
    MediaPlayer mpKoze, mpFlight, mpLedZep;
    int playing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btKoze = (Button)findViewById(R.id.songKozeButton);
        btFlight = (Button)findViewById(R.id.songFlightButton);
        btLedZep = (Button)findViewById(R.id.songLedZepButton);

        btKoze.setOnClickListener(bKoze);
        btFlight.setOnClickListener(bFlight);
        btLedZep.setOnClickListener(bLedZep);

        mpKoze = new MediaPlayer();
        mpKoze = MediaPlayer.create(this, R.raw.djkoze_lesmou);
        mpFlight = new MediaPlayer();
        mpFlight = MediaPlayer.create(this, R.raw.flight_hiphop);
        mpLedZep = new MediaPlayer();
        mpLedZep = MediaPlayer.create(this, R.raw.ledzep_babe);
        playing = 0;
    }
    Button.OnClickListener bKoze = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (playing) {
                case 0:
                    mpKoze.start();
                    playing = 1;
                    btKoze.setText(R.string.pauseMusic);
                    btFlight.setVisibility(View.INVISIBLE);
                    btLedZep.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    mpKoze.pause();
                    playing = 0;
                    btKoze.setText(R.string.songKozeName);
                    btFlight.setVisibility(View.VISIBLE);
                    btLedZep.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };

    Button.OnClickListener bFlight = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (playing) {
                case 0:
                    mpFlight.start();
                    playing = 1;
                    btFlight.setText(R.string.pauseMusic);
                    btKoze.setVisibility(View.INVISIBLE);
                    btLedZep.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    mpFlight.pause();
                    playing = 0;
                    btFlight.setText(R.string.songFlightName);
                    btKoze.setVisibility(View.VISIBLE);
                    btLedZep.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };

    Button.OnClickListener bLedZep = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (playing) {
                case 0:
                    mpLedZep.start();
                    playing = 1;
                    btLedZep.setText(R.string.pauseMusic);
                    btKoze.setVisibility(View.INVISIBLE);
                    btFlight.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    mpLedZep.pause();
                    playing = 0;
                    btLedZep.setText(R.string.songLedZepName);
                    btKoze.setVisibility(View.VISIBLE);
                    btFlight.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };
}
