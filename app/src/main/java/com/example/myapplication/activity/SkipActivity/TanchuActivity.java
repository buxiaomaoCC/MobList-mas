package com.example.myapplication.activity.SkipActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.example.myapplication.R;


public class TanchuActivity extends AppCompatActivity {
    private ImageView imgShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanchu);
        imgShow= (ImageView) findViewById(R.id.imaShow);
        Animation alphaAnimation=new AlphaAnimation(1, (float) 0.1);
        alphaAnimation.setDuration(3000);//设置动画持续时间为3秒
        alphaAnimation.setFillAfter(true);//设置动画结束后保持当前的位置（即不返回到动画开始前的位置）
        imgShow.startAnimation(alphaAnimation);
    }
}
