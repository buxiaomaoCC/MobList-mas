package com.example.myapplication.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.example.myapplication.view.dropdown.ConstellationAdapter;
import com.example.myapplication.view.dropdown.DropDownMenu;
import com.example.myapplication.view.dropdown.GirdDropDownAdapter;
import com.example.myapplication.view.dropdown.ListDropDownAdapter;

import java.util.ArrayList;
import java.util.Arrays;
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
    @BindView(R.id.dropdownmenu)
    DropDownMenu dropdownmenu;
    private List<Integer> imglist;//轮播的数据源
    private List<GridviewObject> gridviewObjects;//Gridview数据源
    private GridviewAdapter gridviewAdapter;
    private ListviewAdapter listviewAdapter;
    private List<ListviewObject> listviewObjects;
    private String headers[] = {"城市", "年龄", "性别", "星座"};
    private List<View> popViews = new ArrayList<>();
    private String citys[] = {"不限", "武汉", "北京", "上海", "成都", "广州", "深圳", "重庆", "天津", "西安", "南京", "杭州"};
    private String ages[] = {"不限", "18岁以下", "18-22岁", "23-26岁", "27-35岁", "35岁以上"};
    private String sexs[] = {"不限", "男", "女"};
    private String constellations[] = {"不限", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座", "水瓶座", "双鱼座"};
    private GirdDropDownAdapter cityAdapter;
    private ListDropDownAdapter ageAdapter, sexAdapter;
    private ConstellationAdapter constellationAdapter;
    private ListView lvCity;
    private ListView lvAge;
    private ListView lvSex;
    private GridView gv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall);
        ButterKnife.bind(this);
        initView();
        initData();
        initViews();
        initGridview();
        initListview();
        initItmClick();
    }

    private void initViews() {
        lvCity = new ListView(this);
        cityAdapter = new GirdDropDownAdapter(this, Arrays.asList(citys));
        lvCity.setDividerHeight(0);
        lvCity.setAdapter(cityAdapter);

        lvAge = new ListView(this);
        ageAdapter = new ListDropDownAdapter(this, Arrays.asList(ages));
        lvAge.setDividerHeight(0);
        lvAge.setAdapter(ageAdapter);

        lvSex = new ListView(this);
        sexAdapter = new ListDropDownAdapter(this, Arrays.asList(sexs));
        lvSex.setDividerHeight(0);
        lvSex.setAdapter(sexAdapter);


        gv = new GridView(this);
        gv.setNumColumns(4);
        gv.setHorizontalSpacing(4);
        gv.setVerticalSpacing(4);
        gv.setPadding(10, 10, 10, 10);
        gv.setBackgroundColor(Color.WHITE);
        constellationAdapter = new ConstellationAdapter(this, Arrays.asList(constellations));
        gv.setAdapter(constellationAdapter);

        lvCity.setOnItemClickListener(this);
        lvAge.setOnItemClickListener(this);
        lvSex.setOnItemClickListener(this);
        gv.setOnItemClickListener(this);

        popViews.add(lvCity);
        popViews.add(lvAge);
        popViews.add(lvSex);
        popViews.add(gv);

        ImageView contentView = new ImageView(this);
        contentView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
        contentView.setScaleType(ImageView.ScaleType.CENTER);

        try {
            dropdownmenu.setDropDownMenu(Arrays.asList(headers), popViews, contentView);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void onBackPressed() {
        if (dropdownmenu.isShowing()) {
            dropdownmenu.closeMenu();
        } else {
            super.onBackPressed();
        }
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.lv_show:
                intent = new Intent(MallActivity.this, PayActivity.class);
                Integer img = listviewObjects.get(position).getListview_iv();
                String name = listviewObjects.get(position).getTv_name();
                String price = listviewObjects.get(position).getTv_price();
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
        if (parent == lvCity) {
            cityAdapter.setCheckItemPosition(position);
            dropdownmenu.setTabText(0, position == 0 ? headers[0] : citys[position]);
//            dropdownmenu.setImageResource(imagids[0]);
        } else if (parent == lvAge) {
            ageAdapter.setCheckItemPosition(position);
            dropdownmenu.setTabText(1, position == 0 ? headers[1] : ages[position]);
//            dropdownmenu.setImageResource(imagids[1]);
        } else if (parent == lvSex) {
            sexAdapter.setCheckItemPosition(position);
            dropdownmenu.setTabText(2, position == 0 ? headers[2] : sexs[position]);
//            dropDownMenu.setImageResource(imagids[2]);
        } else if (parent == gv) {
            constellationAdapter.setCheckItemPosition(position);
            dropdownmenu.setTabText(3, position == 0 ? headers[3] : constellations[position]);
//            dropdownmenu.setImageResource(imagids[3]);
        } else {
            Toast.makeText(this, "出错了！", Toast.LENGTH_SHORT).show();
        }
        dropdownmenu.closeMenu();
    }

}
