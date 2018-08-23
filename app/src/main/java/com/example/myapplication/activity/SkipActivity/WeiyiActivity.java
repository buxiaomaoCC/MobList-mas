package com.example.myapplication.activity.SkipActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.example.myapplication.R;

public class
WeiyiActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView imgShow,aem;
    private Button before,back,zuo,you,bt_attack;
    private int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weiyi);
        initView();
        initClick();
    }

    private void initView() {
        imgShow= (ImageView) findViewById(R.id.imgShow);
        back= (Button) findViewById(R.id.back);
        before= (Button) findViewById(R.id.before);
        bt_attack= (Button) findViewById(R.id.bt_attack);
        zuo= (Button) findViewById(R.id.zuo);
        you= (Button) findViewById(R.id.you);
        aem= (ImageView) findViewById(R.id.iv_aem);
        imgShow.bringToFront();
        aem.bringToFront();
    }
    private void initClick(){
        before.setOnClickListener(this);
        back.setOnClickListener(this);
        zuo.setOnClickListener(this);
        you.setOnClickListener(this);
        bt_attack.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.before:
                Animation translateAnimation=new TranslateAnimation(count, count, count*100, -100+count*100);
                translateAnimation.setDuration(300);//设置动画持续时间为3秒
                translateAnimation.setInterpolator(new LinearInterpolator());
                translateAnimation.setFillAfter(true);//设置动画结束后保持当前的位置（即不返回到动画开始前的位置）
                imgShow.startAnimation(translateAnimation);
                count--;
                break;
            case R.id.zuo:
                Animation translateAnimation1=new TranslateAnimation(count*100, -100+count*100, count, count);
                translateAnimation1.setDuration(300);//设置动画持续时间为3秒
                translateAnimation1.setInterpolator(new LinearInterpolator());
                translateAnimation1.setFillAfter(true);//设置动画结束后保持当前的位置（即不返回到动画开始前的位置）
                imgShow.startAnimation(translateAnimation1);
                count--;
                break;
            case R.id.you:
                Animation translateAnimation2=new TranslateAnimation(count*100, 100+count*100, count, count);
                translateAnimation2.setDuration(300);//设置动画持续时间为3秒
                translateAnimation2.setInterpolator(new LinearInterpolator());
                translateAnimation2.setFillAfter(true);//设置动画结束后保持当前的位置（即不返回到动画开始前的位置）
                imgShow.startAnimation(translateAnimation2);
                count++;
                break;
            case R.id.back:
                Animation translateAnimation3=new TranslateAnimation(count, count, count*100, 100+count*100);
                translateAnimation3.setDuration(300);//设置动画持续时间为3秒
                translateAnimation3.setInterpolator(new LinearInterpolator());
                translateAnimation3.setFillAfter(true);//设置动画结束后保持当前的位置（即不返回到动画开始前的位置）
                imgShow.startAnimation(translateAnimation3);
                count++;
                break;
            case R.id.bt_attack:
                imgShow.setImageDrawable(null);
                imgShow.setBackgroundResource(R.drawable.lottery_animlist);
                //第二步获取ImagView的背景并将其转换成AnimationDrawable
                AnimationDrawable animationDrawable=(AnimationDrawable)imgShow.getBackground();
                //第三步开始播放动画
                animationDrawable.start();

                aem.setImageDrawable(null);
                aem.setBackgroundResource(R.drawable.framelist);
                //第二步获取ImagView的背景并将其转换成AnimationDrawable
                AnimationDrawable animationDrawable1=(AnimationDrawable)aem.getBackground();
                //第三步开始播放动画
                animationDrawable1.start();
                break;
        }
    }
}
