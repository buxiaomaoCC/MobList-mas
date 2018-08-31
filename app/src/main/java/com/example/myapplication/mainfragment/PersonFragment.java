package com.example.myapplication.mainfragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.activity.ConversationActivity;
import com.example.myapplication.activity.DirectionActivity;
import com.example.myapplication.activity.LuckyDrawActivity;
import com.example.myapplication.activity.MainActivity;
import com.example.myapplication.activity.MobIMActivity;
import com.example.myapplication.utils.PersonSingleUtils;
import com.example.myapplication.view.CircleImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.onekeyshare.OnekeyShare;
import io.rong.imkit.RongIM;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonFragment extends Fragment {
    @BindView(R.id.image_circle)
    CircleImageView imageCircle;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.lv_show)
    ListView lvShow;
    private String url = null;//传过来的头像的url地址
    private String name=null;//传过来的昵称
    private Unbinder unbinder;
    private List<String>stringList;
    private ArrayAdapter arrayAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person, container, false);
        unbinder = ButterKnife.bind(this, view);
        initListView();
        initItemClick();
        return view;
    }

    private void initItemClick() {

        lvShow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=null;
                switch (i){
                    case 0:
                        showShare();
                        break;
                    case 1:
                        if (RongIM.getInstance() != null) {
                            RongIM.getInstance().startPrivateChat(getActivity(), "176", "不笑猫");
                        }
//                        startActivity(new Intent(getContext(),MobIMActivity.class));
                        break;
                    case 2:
                        intent=new Intent(getContext(), LuckyDrawActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent=new Intent(getContext(), DirectionActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    private void initListView() {
        stringList=new ArrayList<>();
        stringList.add("好友分享");
        stringList.add("我的客服");
        stringList.add("圆盘抽奖");
        stringList.add("指南针");
        arrayAdapter=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,stringList);
        lvShow.setAdapter(arrayAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        url=PersonSingleUtils.getInstance(getActivity()).getImgUrl();
        name=PersonSingleUtils.getInstance(getActivity()).getName();
        if(url!=null){
            Glide.with(getActivity()).load(url).into(imageCircle);
        }else {
            imageCircle.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.erke_2));
        }
        if(name!=null){
            tvName.setText(name);
        }else {
            tvName.setText("待登录");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.image_circle)
    public void onViewClicked() {

    }
    /**
     * 一键分享
     */
    private void showShare(){
        OnekeyShare onekeyShare=new OnekeyShare();
        //关闭sso授权
        onekeyShare.disableSSOWhenAuthorize();
        //title标题，微信，qq和qq空间
        onekeyShare.setTitle("我是我是一键分享");
        //titleURL  QQ和空间跳转链接
        onekeyShare.setTitleUrl("http://sharesdk.cn");
        //text是分享文本，所有平台都需要这个字段
        onekeyShare.setText("我是一键分享");
        //imagePath是图片的本地路径，所有平台都支持此参数
//        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url在微信、微博，Facebook等平台中使用
        onekeyShare.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网使用
//        onekeyShare.setComment("我是测试评论文本");
        onekeyShare.setCallback(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "onComplete", Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onError(Platform platform, int i, final Throwable throwable) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "onError"+throwable.toString(), Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onCancel(Platform platform, int i) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "onCancel", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        // 启动分享GUI
        onekeyShare.show(getActivity());
    }

}
