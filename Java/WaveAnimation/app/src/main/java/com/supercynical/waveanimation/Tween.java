package com.supercynical.waveanimation;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Tween extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tween);
        ImageView imgRotate = (ImageView)findViewById(R.id.imgTween);
        imgRotate.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotation));
    }
}
