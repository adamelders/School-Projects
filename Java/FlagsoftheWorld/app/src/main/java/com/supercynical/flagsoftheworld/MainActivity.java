package com.supercynical.flagsoftheworld;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    AnimationDrawable flagsAnmiation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imgFrame = (ImageView)findViewById(R.id.imgFlags);
        imgFrame.setBackgroundResource(R.drawable.animation);
        flagsAnmiation = (AnimationDrawable)imgFrame.getBackground();
        Button btFrame = (Button)findViewById(R.id.btnStart);
        Button btTween = (Button)findViewById(R.id.btnStop);

        btFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagsAnmiation.start();
            }
        });

        btTween.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagsAnmiation.stop();
                ImageView imgAlpha = (ImageView)findViewById(R.id.imgFlags);
                imgAlpha.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade));
            }
        });
    }
}
