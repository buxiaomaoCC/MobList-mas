package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplishActivity extends BaseActivity {
    private Timer timer;
    private TimerTask timerTask;
    private static int time=4;
    @BindView(R.id.iv_gif)
    ImageView ivGif;
    @BindView(R.id.textView)
    TextView textView;
    MyHandle myHandle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splish);
        ButterKnife.bind(this);
        initLogin();
    }

    @Override
    protected void onStart() {
        super.onStart();
        myHandle=new MyHandle(this);
        timer=new Timer();
        timerTask=new TimerTask() {
            @Override
            public void run() {
                myHandle.sendEmptyMessage(time--);
            }
        };
        timer.schedule(timerTask,0,1000);
    }

    /**
     * 闪屏页计时结束，跳转至登陆界面
     */
    private void initLogin(){
        CountDownTimer countDownTimer = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                timer.cancel();
                time=4;
                startActivity(new Intent(SplishActivity.this, LoginActivity.class));
                finish();
            }
        };
        countDownTimer.start();
    }
    @OnClick(R.id.textView)
    public void onViewClicked() {
        timer.cancel();
        time=4;
        Intent intent=new Intent(SplishActivity.this, LoginActivity.class);
        startActivity(intent);
    }
    public static class MyHandle extends Handler{
        //声明一个弱引用对象
        WeakReference<SplishActivity>weakReference;
        MyHandle(SplishActivity splishActivity){
            //在构造器中传入activity,创建弱引用对象
            weakReference=new WeakReference<SplishActivity>(splishActivity);
        }


        public void handleMessage(Message msg) {
            //在使用之前activity先做判空处理
            if(weakReference!=null&&weakReference.get()!=null){
                weakReference.get().textView.setText("跳过"+time+"s");
            }
        }
    }
}
