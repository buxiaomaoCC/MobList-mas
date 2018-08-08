package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.LoginViewPagerAdapter;
import com.example.myapplication.loginfragment.PasswordFragment;
import com.example.myapplication.loginfragment.PhoneFragment;
import com.mob.MobSDK;
import com.mob.pushsdk.MobPush;
import com.mob.tools.utils.UIHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

public class LoginActivity extends BaseActivity implements Handler.Callback, PlatformActionListener {

    @BindView(R.id.iv_return)
    ImageView ivReturn;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.ll_login)
    LinearLayout llLogin;
    private List<Fragment> fragmentList;
    private String[] login_item = new String[]{"快速登录", "密码登陆"};
    private LoginViewPagerAdapter loginViewPagerAdapter;
    private Handler handler;
    private static final int MSG_USERID_FOUND = 1;
    private static final int MSG_LOGIN = 2;
    private static final int MSG_AUTH_CANCEL = 3;
    private static final int MSG_AUTH_ERROR = 4;
    private static final int MSG_AUTH_COMPLETE = 5;
    @BindView(R.id.tl_login)
    TabLayout tlLogin;
    @BindView(R.id.vp_login)
    ViewPager vpLogin;
    @BindView(R.id.iv_wxLogin)
    ImageView ivWxLogin;
    @BindView(R.id.iv_qqLogin)
    ImageView ivQqLogin;
    @BindView(R.id.iv_xlLogin)
    ImageView ivXlLogin;
    String[] args = new String[]{"mob", "coco"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //初始化第三方登录
        MobSDK.init(getApplicationContext());
        MobPush.setAlias("mobList");
        MobPush.addTags(args);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
        tvTitle.setText("MobList");
    }

    /**
     * 登录界面viewpager数据配置
     */
    private void initData() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new PhoneFragment());
        fragmentList.add(new PasswordFragment());
        loginViewPagerAdapter = new LoginViewPagerAdapter(getSupportFragmentManager(), fragmentList);
        vpLogin.setAdapter(loginViewPagerAdapter);
        tlLogin.setupWithViewPager(vpLogin);
        for (int i = 0; i < fragmentList.size(); i++) {
            tlLogin.getTabAt(i).setText(login_item[i]);
        }
    }

    @OnClick({R.id.iv_return, R.id.iv_share, R.id.tl_login, R.id.vp_login, R.id.iv_wxLogin, R.id.iv_qqLogin, R.id.iv_xlLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tl_login:
                break;
            case R.id.vp_login:
                break;
            case R.id.iv_wxLogin:
                WXLogin(Wechat.NAME);
                break;
            case R.id.iv_qqLogin:
                WXLogin(QQ.NAME);
                break;
            case R.id.iv_xlLogin:
                WXLogin(SinaWeibo.NAME);
                break;
        }
    }

    /**
     * 第三方登录
     */
    private void WXLogin(String name) {
        Platform platform = ShareSDK.getPlatform(name);
        if (platform == null) {
            return;
        }
        //判断指定平台是否已经完成授权
        if (platform.isAuthValid()) {
            String userId = platform.getDb().getUserId();
            if (userId != null) {
                UIHandler.sendEmptyMessage(MSG_USERID_FOUND, LoginActivity.this);
                login(platform.getName(), userId, null);
            }
        }
        platform.SSOSetting(false);//单点登录，设置为false,优先采用客户端授权
        //设置监听
        platform.setPlatformActionListener(this);
        //获取登录用户的信息，如果没有授权，会先授权，然后获取用户信息
        platform.authorize();
    }

    @Override
    public boolean handleMessage(Message message) {
        switch (message.what) {
            case MSG_USERID_FOUND:
//                Toast.makeText(this, "信息已找到", Toast.LENGTH_SHORT).show();
                break;
            case MSG_LOGIN:
//                Toast.makeText(this, "已登陆", Toast.LENGTH_SHORT).show();

                break;
            case MSG_AUTH_CANCEL:
                Toast.makeText(this, "已取消", Toast.LENGTH_SHORT).show();
                break;
            case MSG_AUTH_ERROR:
                Toast.makeText(this, "请求错误", Toast.LENGTH_SHORT).show();
                break;
            case MSG_AUTH_COMPLETE:
//                Toast.makeText(this, "信息已收到", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return false;
    }

    /**
     * 发送登录请求
     */
    private void login(String plat, String userId, HashMap<String, Object> userInfo) {
        Message message = new Message();
        message.what = MSG_LOGIN;
        message.obj = plat;
        UIHandler.sendMessage(message, this);
    }

    /**
     * 登陆成功
     *
     * @param platform
     * @param i
     * @param hashMap
     */
    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        if (i == Platform.ACTION_AUTHORIZING) {
            //登陆成功，获取需要的信息
            UIHandler.sendEmptyMessage(MSG_AUTH_COMPLETE, this);
            login(platform.getName(), platform.getDb().getUserId(), hashMap);
            String openid = platform.getDb().getUserId() + "";
            String gender = platform.getDb().getUserGender();
            String gead_url = platform.getDb().getUserIcon();
            String nickname = platform.getDb().getUserName();
//            Log.e("openid,",openid);
////            Log.e("gender,",gender+"");
//            Log.e("gead_url,",gead_url);
//            Log.e("nickname,",nickname);
//            Log.e("登陆，",openid+"////"+gender+"////"+gead_url+"////"+nickname);
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//            Bundle bundle=new Bundle();
            intent.putExtra("image_tx", gead_url);
            intent.putExtra("name", nickname);
            startActivity(intent);
        }
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        if (i == Platform.ACTION_AUTHORIZING) {
            UIHandler.sendEmptyMessage(MSG_AUTH_ERROR, this);
        }
        throwable.printStackTrace();
        Log.e("登陆1，", "失败");
    }

    @Override
    public void onCancel(Platform platform, int i) {
        if (i == Platform.ACTION_AUTHORIZING) {
            UIHandler.sendEmptyMessage(MSG_AUTH_CANCEL, this);
        }
        Log.e("登陆2，", "取消");
    }
}
