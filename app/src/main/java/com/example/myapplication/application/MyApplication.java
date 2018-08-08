package com.example.myapplication.application;

import android.app.Application;

import com.mob.MobSDK;
import com.umeng.commonsdk.UMConfigure;

import io.rong.imkit.RongIM;

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        RongIM.init(this);
        MobSDK.init(this);
        //初始化友盟统计
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE,"");
    }
}
