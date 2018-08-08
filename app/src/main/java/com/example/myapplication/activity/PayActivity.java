package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.example.myapplication.R;
import com.example.myapplication.utils.AdapterUtils;
import com.example.myapplication.utils.AliPayUtils;
import com.example.myapplication.utils.LocalImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayActivity extends BaseActivity implements OnItemClickListener {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.bt_pay)
    Button btPay;
    @BindView(R.id.ll_price)
    LinearLayout llPrice;
    @BindView(R.id.iv_cut)
    ImageView ivCut;
    @BindView(R.id.iv_add)
    ImageView ivAdd;
    @BindView(R.id.cb_pay)
    ConvenientBanner cbPay;
    @BindView(R.id.lv_return)
    ImageView lvReturn;
    @BindView(R.id.llNum)
    LinearLayout llNum;
    @BindView(R.id.cb_ali)
    CheckBox cbAli;
    @BindView(R.id.cb_wx)
    CheckBox cbWx;
    private List<Integer> integerList;
    private int num = 0;
    private int sumPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ButterKnife.bind(this);

        initView();
        initPayWay();
    }

    AliPayUtils aliPayUtils = null;

    private void initView() {
        integerList = new AdapterUtils().getCB();
        cbPay.setPages(new CBViewHolderCreator<LocalImageView>() {
            @Override
            public LocalImageView createHolder() {
                return new LocalImageView();
            }
        }, integerList)//设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.image_indicator, R.drawable.image_indicator_focus})
                .setOnItemClickListener(this);

        aliPayUtils = new AliPayUtils(PayActivity.this);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        cbPay.startTurning(3000);
        Intent intent = getIntent();
        int img = intent.getIntExtra("img", -1);
        String name = intent.getStringExtra("name");
        String price = intent.getStringExtra("price");
        tvName.setText(name);
        tvPrice.setText(price);
        tvNum.setText(num + "");
        if(!TextUtils.isEmpty(tvPrice.getText().toString().trim())){
            sumPrice=Integer.parseInt(tvPrice.getText().toString().trim());
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        cbPay.stopTurning();
    }

    boolean isInstalled = false;
    @OnClick({R.id.tv_name, R.id.tv_price, R.id.tv_num, R.id.bt_pay,R.id.iv_cut, R.id.iv_add,R.id.lv_return,R.id.cb_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_name:
                break;
            case R.id.tv_price:
                break;
            case R.id.tv_num:
                break;
            case R.id.bt_pay:
                if(cbAli.isChecked()){
                    isInstalled = aliPayUtils.checkAliPayInstalled();
                    if (isInstalled) {
                        aliPayUtils.pay(PayActivity.this);
                    } else {
                        aliPayUtils.h5Pay(view);
                    }
                }else if(cbWx.isChecked()){

                }else {
                    Toast.makeText(this, "请选择付款方式", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.iv_cut:
                if (num >= 0.01) {
                    num--;
                }
                tvNum.setText(num + "");
                tvPrice.setText(sumPrice*num+"");
                break;
            case R.id.iv_add:
                num++;
                tvNum.setText(num + "");
                tvPrice.setText(sumPrice*num+"");
                break;
            case R.id.lv_return:
                finish();
                break;
            case R.id.cb_pay:
                break;
        }
    }

    @Override
    public void onItemClick(int position) {

    }
    //选择付款方法
    private void initPayWay(){
        cbAli.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(cbAli.isChecked()){
                    cbWx.setChecked(false);
                }
            }
        });
        cbWx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(cbWx.isChecked()){
                    cbAli.setChecked(false);
                }
            }
        });
    }
}
