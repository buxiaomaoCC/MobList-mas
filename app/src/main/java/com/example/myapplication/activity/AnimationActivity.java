package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.activity.SkipActivity.FrameActivity;
import com.example.myapplication.activity.SkipActivity.RotateActivity;
import com.example.myapplication.activity.SkipActivity.TanchuActivity;
import com.example.myapplication.activity.SkipActivity.WeiyiActivity;

public class AnimationActivity extends AppCompatActivity implements View.OnClickListener{
    private Button bt_danchu,bt_xuanzhuan,bt_suofang,bt_zhen,bt_weiyi,bt_shuxing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        initView();
        initClick();
    }
    private void initView() {
        bt_danchu= (Button) findViewById(R.id.bt_danchu);
        bt_shuxing= (Button) findViewById(R.id.bt_shuxing);
        bt_suofang= (Button) findViewById(R.id.bt_suofang);
        bt_weiyi= (Button) findViewById(R.id.bt_weiyi);
        bt_xuanzhuan= (Button) findViewById(R.id.bt_xuanzhuan);
        bt_zhen= (Button) findViewById(R.id.bt_zhen);
    }
    private void initClick(){
        bt_danchu.setOnClickListener(this);
        bt_shuxing.setOnClickListener(this);
        bt_suofang.setOnClickListener(this);
        bt_weiyi.setOnClickListener(this);
        bt_xuanzhuan.setOnClickListener(this);
        bt_zhen.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.bt_danchu:
                intent=new Intent(AnimationActivity.this, TanchuActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_shuxing:
                break;
            case R.id.bt_suofang:
                break;
            case R.id.bt_weiyi:
                intent=new Intent(AnimationActivity.this, WeiyiActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_xuanzhuan:
                intent=new Intent(AnimationActivity.this, RotateActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_zhen:
                intent=new Intent(AnimationActivity.this, FrameActivity.class);
                startActivity(intent);
                break;

        }
    }
}
