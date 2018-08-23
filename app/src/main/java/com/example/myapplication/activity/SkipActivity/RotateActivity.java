package com.example.myapplication.activity.SkipActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.myapplication.R;


public class RotateActivity extends AppCompatActivity {
    private ImageView iv_show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate);
        iv_show= (ImageView) findViewById(R.id.iv_show);
        Animation animation= AnimationUtils.loadAnimation(this, R.anim.rotate);
        iv_show.setAnimation(animation);
    }
}
