package com.example.myapplication.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.LoginViewPagerAdapter;
import com.example.myapplication.mainfragment.FunctionFragment;
import com.example.myapplication.mainfragment.PersonFragment;
import com.example.myapplication.push.MobPushDemo;
import com.example.myapplication.utils.PersonSingleUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;


public class MainActivity extends BaseActivity implements RongIM.UserInfoProvider{

    @BindView(R.id.main_view)
    ViewPager mainView;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.main_tl)
    TabLayout mainTl;
    private String url = null;
    private String name = null;
    private String[] strings = {"功能列表", "个人中心"};
    private List<Fragment> layouts;
    private LoginViewPagerAdapter loginViewPagerAdapter;
    private static final String TAG = "MainActivity";
    private static final String token1 = "xJGPknWg9uZnhiVTnf3SjDD/7qqHET2TT4DpQOs/9D9gt+kSwyjW35oMT47M3Noe21NglY6fLpTmnI4PwdJQlA==";
    private String userId="175",username="龙猫",portraitUri="https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=137798378,4132757201&fm=27&gp=0.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initDate();
        new MobPushDemo().initMobpush();
        RongIM.setUserInfoProvider(this, true);
    }

    private void initDate() {
        layouts = new ArrayList<>();
        FunctionFragment functionFragment = new FunctionFragment();
        PersonFragment personFragment = new PersonFragment();
        layouts.add(functionFragment);
        layouts.add(personFragment);
        loginViewPagerAdapter = new LoginViewPagerAdapter(getSupportFragmentManager(), layouts);
        mainView.setAdapter(loginViewPagerAdapter);
        mainTl.setupWithViewPager(mainView);
        for (int i = 0; i < layouts.size(); i++) {
            mainTl.getTabAt(i).setText(strings[i]);
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        url = getIntent().getStringExtra("image_tx");
        name = getIntent().getStringExtra("name");
        if (url != null) {
            PersonSingleUtils.getInstance(this).setImgUrl(url);
        }
        if (name != null) {
            PersonSingleUtils.getInstance(this).setName(name);
        }
        connectRongServer(token1);
    }
    private void connectRongServer(String token) {
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onSuccess(String userId) {
                Log.e(TAG,"用户id"+userId);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.e(TAG, "connect failure errorCode is : " + errorCode.getValue());
            }

            @Override
            public void onTokenIncorrect() {
                Log.e(TAG, "token is error ,please check token and app key");
            }
        });
    }
    @Override
    public UserInfo getUserInfo(String s) {
        if(userId.equals(s)){
            return new UserInfo(userId,username, Uri.parse(portraitUri));
        }
        return null;
    }
}
