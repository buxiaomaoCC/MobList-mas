package com.example.myapplication.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;

public class ConversationActivity extends BaseActivity{
    private TextView mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);

        mName = (TextView) findViewById(R.id.name);

        String sId = getIntent().getData().getQueryParameter("targetId");   // targetId:单聊即对方ID，群聊即群组ID
        String sName = getIntent().getData().getQueryParameter("title");    // 获取昵称

        if (!TextUtils.isEmpty(sName)) {
            mName.setText(sName);
        } else {
            // sId
            // TODO 拿到id 去请求自己服务端
        }
    }
}
