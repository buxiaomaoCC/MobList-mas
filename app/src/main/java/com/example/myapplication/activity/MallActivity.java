package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.example.myapplication.R;
import com.example.myapplication.adapter.GridviewAdapter;
import com.example.myapplication.adapter.ListviewAdapter;
import com.example.myapplication.bean.GridviewObject;
import com.example.myapplication.bean.ListviewObject;
import com.example.myapplication.utils.AdapterUtils;
import com.example.myapplication.utils.ListViewItemUtils;
import com.example.myapplication.utils.LocalImageView;
import com.example.myapplication.view.MyGridView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MallActivity extends BaseActivity implements OnItemClickListener, AdapterView.OnItemClickListener {

    @BindView(R.id.iv_return)
    ImageView ivReturn;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.cb)
    ConvenientBanner cb;
    @BindView(R.id.lv_show)
    ListView lvShow;
    @BindView(R.id.gv_show)
    MyGridView gvShow;
    @BindView(R.id.ll_mall)
    LinearLayout llMall;
    private List<Integer> imglist;//轮播的数据源
    private List<GridviewObject> gridviewObjects;//Gridview数据源
    private GridviewAdapter gridviewAdapter;
    private ListviewAdapter listviewAdapter;
    private List<ListviewObject> listviewObjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall);
        ButterKnife.bind(this);
        initView();
        initData();
        initGridview();
        initListview();
        initItmClick();
    }

    private void initItmClick() {
        lvShow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MallActivity.this, PayActivity.class);
                Integer img = listviewObjects.get(i).getListview_iv();
                String name = listviewObjects.get(i).getTv_name();
                String price = listviewObjects.get(i).getTv_price();
                intent.putExtra("img", img);
                intent.putExtra("name", name);
                intent.putExtra("price", price);
                startActivity(intent);
            }
        });
        gvShow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MallActivity.this, PayActivity.class);
                intent.putExtra("name", "老街口手剥奶油加州巴旦木200g 特产零食坚果炒货干果薄壳扁桃仁");
                intent.putExtra("price", "1");
                startActivity(intent);
            }
        });
    }

    private void initListview() {
        listviewObjects = new AdapterUtils().getListview();
        listviewAdapter = new ListviewAdapter(this, listviewObjects);
        etSearch.setEnabled(true);
        etSearch.setClickable(true);
        lvShow.setAdapter(listviewAdapter);
        ListViewItemUtils.setListViewHeightBasedOnChildren(lvShow);
    }

    //gridview的数据源
    private void initGridview() {
        gridviewObjects = new AdapterUtils().getGridviewObjects();
        gridviewAdapter = new GridviewAdapter(this, gridviewObjects);
        gvShow.setAdapter(gridviewAdapter);
    }

    @Override
    public void onResume() {//轮播自动播放的开始
        super.onResume();
        cb.startTurning(3000);
    }

    @Override
    public void onPause() {//轮播的结束
        super.onPause();
        cb.stopTurning();
    }

    //轮播的数据
    private void initData() {
        imglist = new AdapterUtils().getCB();
        cb.setPages(new CBViewHolderCreator<LocalImageView>() {
            @Override
            public LocalImageView createHolder() {
                return new LocalImageView();
            }
        }, imglist)//设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.image_indicator, R.drawable.image_indicator_focus})
                .setOnItemClickListener(this);
        //设置指示器的方向
    }

    private void initView() {
        tvTitle.setText("商城");
        ivReturn.setImageDrawable(getResources().getDrawable(R.drawable.chuzuxiangqing));
        etSearch.clearFocus();
        llMall.requestFocus();
    }

    @OnClick({R.id.iv_return, R.id.iv_share, R.id.cb, R.id.et_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_return:
                finish();
                break;
            case R.id.iv_share:
                break;
            case R.id.et_search:
                Intent intent = new Intent(MallActivity.this, SearchRecordActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(MallActivity.this, PayActivity.class);
        intent.putExtra("name", "老街口手剥奶油加州巴旦木200g 特产零食坚果炒货干果薄壳扁桃仁");
        intent.putExtra("price", "1");
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.lv_show:
                intent = new Intent(MallActivity.this, PayActivity.class);
                Integer img = listviewObjects.get(i).getListview_iv();
                String name = listviewObjects.get(i).getTv_name();
                String price = listviewObjects.get(i).getTv_price();
                intent.putExtra("img", img);
                intent.putExtra("name", name);
                intent.putExtra("price", price);
                startActivity(intent);
                break;
            case R.id.gv_show:
                intent = new Intent(MallActivity.this, PayActivity.class);
                intent.putExtra("name", "老街口手剥奶油加州巴旦木200g 特产零食坚果炒货干果薄壳扁桃仁");
                intent.putExtra("price", "1");
                startActivity(intent);
                break;
            default:
                break;
        }
    }

}
