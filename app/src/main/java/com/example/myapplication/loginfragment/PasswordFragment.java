package com.example.myapplication.loginfragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.activity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class PasswordFragment extends Fragment {


    @BindView(R.id.et_Inputphone)
    EditText etInputphone;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.bt_passwordlogin)
    Button btPasswordlogin;
    @BindView(R.id.tv_lineKefu)
    TextView tvLineKefu;
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_password, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.et_Inputphone, R.id.et_password, R.id.tv_register, R.id.bt_passwordlogin, R.id.tv_lineKefu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_Inputphone:
                break;
            case R.id.et_password:
                break;
            case R.id.tv_register:
                break;
            case R.id.bt_passwordlogin:
                Intent intent=new Intent(getContext(), MainActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.tv_lineKefu:
                break;
        }
    }
}
