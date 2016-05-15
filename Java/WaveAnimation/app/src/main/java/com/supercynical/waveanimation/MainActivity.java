package com.supercynical.waveanimation;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    AnimationDrawable surfAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imgFrame=(ImageView)findViewById(R.id.imgSurf);
        imgFrame.setBackgroundResource(R.drawable.animation);
        surfAnimation=(AnimationDrawable)imgFrame.getBackground();
        Button btFrame=(Button)findViewById(R.id.btnStart);
        Button btTween=(Button)findViewById(R.id.btnStop);
        btFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                surfAnimation.start();
            }
        });
        btTween.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                surfAnimation.stop();
                startActivity (new Intent(MainActivity.this, Tween.class));
            }
        });
    }
}
