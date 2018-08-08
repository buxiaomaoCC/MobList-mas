package com.example.myapplication.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.model.Friend;
import com.mob.MobSDK;
import com.mob.imsdk.MobIM;
import com.mob.imsdk.MobIMCallback;
import com.mob.imsdk.MobIMMessageReceiver;
import com.mob.imsdk.model.IMMessage;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;

public class MobIMActivity extends BaseActivity implements View.OnClickListener, RongIM.UserInfoProvider {
    private static final String token1 = "xJGPknWg9uZnhiVTnf3SjDD/7qqHET2TT4DpQOs/9D9gt+kSwyjW35oMT47M3Noe21NglY6fLpTmnI4PwdJQlA==";
    private static final String token2 = "nY+4bQc4PWd8RkxGMJXtVqCCseBf0ore2iGeptsZ959f2EhqCYKxCUcvocuYcL8BEdAZKBMNPtk=";

    private List<Friend> userIdList;
    private static final String TAG = "MainActivity";

    private Button mUser1, mUser2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mob_im);
        mUser1 = (Button) findViewById(R.id.connect_10010);
        mUser2 = (Button) findViewById(R.id.connect_10086);
        mUser1.setOnClickListener(this);
        mUser2.setOnClickListener(this);

        initUserInfo();
    }
    private void connectRongServer(String token) {
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onSuccess(String userId) {
                if (userId.equals("175")) {
                    mUser1.setText("用户1连接服务器成功");
                    startActivity(new Intent(MobIMActivity.this, HomeActivity.class));
                    Toast.makeText(MobIMActivity.this, "切换用户登录成功", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(MobIMActivity.this, HomeActivity.class));
                    Toast.makeText(MobIMActivity.this, "切换用户登录成功", Toast.LENGTH_SHORT).show();
                }
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
    public void onClick(View v) {
        if (v != null) {
            switch (v.getId()) {
                case R.id.connect_10010:
                    connectRongServer(token1);
                    break;
                case R.id.connect_10086:
                    connectRongServer(token2);
                    break;
            }
        }
    }

    private void initUserInfo() {
        userIdList = new ArrayList<>();
        userIdList.add(new Friend("175", "coco", "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=137798378,4132757201&fm=27&gp=0.jpg"));     // 联通图标
        userIdList.add(new Friend("176", "在线客服", "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=37363516,836102565&fm=27&gp=0.jpg"));  // 移动图标
//        userIdList.add(new Friend("KEFU144542424649464", "在线客服", "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=567306798,4267271838&fm=27&gp=0.jpg"));
        RongIM.setUserInfoProvider(this, true);
    }

    @Override
    public UserInfo getUserInfo(String s) {
        for (Friend i : userIdList) {
            if (i.getUserId().equals(s)) {
                Log.e(TAG, i.getPortraitUri());
                return new UserInfo(i.getUserId(), i.getName(), Uri.parse(i.getPortraitUri()));
            }
        }
        Log.e(TAG, "UserId is : " + s);
        return null;
    }
}
