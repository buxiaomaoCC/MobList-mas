package com.example.myapplication.activity.SkipActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.myapplication.R;


public class FrameActivity extends AppCompatActivity {
    private ImageView iv_aem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);
        iv_aem= (ImageView) findViewById(R.id.iv_aem);
        iv_aem.bringToFront();
        iv_aem.setImageDrawable(null);
        iv_aem.setBackgroundResource(R.drawable.framelist);
        AnimationDrawable animationDrawable= (AnimationDrawable) iv_aem.getBackground();
        animationDrawable.start();
    }
}
