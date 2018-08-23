package com.example.myapplication.mainfragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.activity.AnimationActivity;
import com.example.myapplication.activity.MallActivity;
import com.example.myapplication.activity.VideoSourceActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class FunctionFragment extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.bt_mall)
    Button btMall;
    @BindView(R.id.bt_play)
    Button btPlay;
    @BindView(R.id.bt_animation)
    Button btAnimation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_function, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.bt_mall, R.id.bt_play,R.id.bt_animation})
    public void onViewClicked(View view) {
        Intent intent=null;
        switch (view.getId()) {
            case R.id.bt_mall:
                intent = new Intent(getContext(), MallActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.bt_play:
                intent = new Intent(getContext(), VideoSourceActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.bt_animation:
                intent=new Intent(getContext(), AnimationActivity.class);
                getActivity().startActivity(intent);
                break;
        }
    }
}
