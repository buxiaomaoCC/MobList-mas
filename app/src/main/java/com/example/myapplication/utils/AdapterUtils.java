package com.example.myapplication.utils;

import com.example.myapplication.R;
import com.example.myapplication.bean.GridviewObject;
import com.example.myapplication.bean.ListviewObject;

import java.util.ArrayList;
import java.util.List;

public class AdapterUtils {
    private List<GridviewObject>gridviewObjects;
    private List<ListviewObject>listviewObjects;
    private List<Integer>integerList;
    public List<GridviewObject> getGridviewObjects(){
        gridviewObjects=new ArrayList<>();
        GridviewObject gridviewObject=new GridviewObject();
        gridviewObject.setGrid_view(R.drawable.g1);
        gridviewObject.setGrid_tv("阿克苏果");
        gridviewObjects.add(gridviewObject);
        GridviewObject gridviewObject1=new GridviewObject();
        gridviewObject1.setGrid_view(R.drawable.g2);
        gridviewObject1.setGrid_tv("澳洲弥桃");
        gridviewObjects.add(gridviewObject1);
        GridviewObject gridviewObject2=new GridviewObject();
        gridviewObject2.setGrid_view(R.drawable.g3);
        gridviewObject2.setGrid_tv("小皮皮虾");
        gridviewObjects.add(gridviewObject2);
        GridviewObject gridviewObject3=new GridviewObject();
        gridviewObject3.setGrid_view(R.drawable.g4);
        gridviewObject3.setGrid_tv("澳洲牛肉");
        gridviewObjects.add(gridviewObject3);
        GridviewObject gridviewObject4=new GridviewObject();
        gridviewObject4.setGrid_view(R.drawable.g5);
        gridviewObject4.setGrid_tv("iwatch3");
        gridviewObjects.add(gridviewObject4);
        GridviewObject gridviewObject5=new GridviewObject();
        gridviewObject5.setGrid_view(R.drawable.g6);
        gridviewObject5.setGrid_tv("体感摄像头");
        gridviewObjects.add(gridviewObject5);
        GridviewObject gridviewObject6=new GridviewObject();
        gridviewObject6.setGrid_view(R.drawable.g7);
        gridviewObject6.setGrid_tv("国美风扇");
        gridviewObjects.add(gridviewObject6);
        GridviewObject gridviewObject7=new GridviewObject();
        gridviewObject7.setGrid_view(R.drawable.g8);
        gridviewObject7.setGrid_tv("家居");
        gridviewObjects.add(gridviewObject7);
        GridviewObject gridviewObject8=new GridviewObject();
        gridviewObject8.setGrid_view(R.drawable.g9);
        gridviewObject8.setGrid_tv("衣物");
        gridviewObjects.add(gridviewObject8);
        GridviewObject gridviewObject9=new GridviewObject();
        gridviewObject9.setGrid_view(R.drawable.g10);
        gridviewObject9.setGrid_tv("牙刷");
        gridviewObjects.add(gridviewObject8);
        return gridviewObjects;
    }

    //listview的数据
    public List<ListviewObject> getListview(){
        listviewObjects=new ArrayList<>();
        ListviewObject listviewObject=new ListviewObject();
        listviewObject.setListview_iv(R.drawable.g1);
        listviewObject.setTv_name("阿克苏果");
        listviewObject.setTv_price("1");
        listviewObjects.add(listviewObject);
        ListviewObject listviewObject1=new ListviewObject();
        listviewObject1.setListview_iv(R.drawable.g2);
        listviewObject1.setTv_name("澳洲弥桃");
        listviewObject1.setTv_price("1");
        listviewObjects.add(listviewObject1);
        ListviewObject listviewObject2=new ListviewObject();
        listviewObject2.setListview_iv(R.drawable.g3);
        listviewObject2.setTv_name("小皮皮虾");
        listviewObject2.setTv_price("1");
        listviewObjects.add(listviewObject2);
        ListviewObject listviewObject3=new ListviewObject();
        listviewObject3.setListview_iv(R.drawable.g4);
        listviewObject3.setTv_name("澳洲牛肉");
        listviewObject3.setTv_price("1");
        listviewObjects.add(listviewObject3);
        ListviewObject listviewObject4=new ListviewObject();
        listviewObject4.setListview_iv(R.drawable.g5);
        listviewObject4.setTv_name("iwatch3");
        listviewObject4.setTv_price("1");
        listviewObjects.add(listviewObject4);
        ListviewObject listviewObject5=new ListviewObject();
        listviewObject5.setListview_iv(R.drawable.g6);
        listviewObject5.setTv_name("体感摄像头");
        listviewObject5.setTv_price("1");
        listviewObjects.add(listviewObject5);
        ListviewObject listviewObject6=new ListviewObject();
        listviewObject6.setListview_iv(R.drawable.g7);
        listviewObject6.setTv_name("埃特美风扇");
        listviewObject6.setTv_price("1");
        listviewObjects.add(listviewObject);
        ListviewObject listviewObject7=new ListviewObject();
        listviewObject7.setListview_iv(R.drawable.g8);
        listviewObject7.setTv_name("简约家居");
        listviewObject7.setTv_price("1");
        listviewObjects.add(listviewObject7);
        ListviewObject listviewObject8=new ListviewObject();
        listviewObject8.setListview_iv(R.drawable.g9);
        listviewObject8.setTv_name("夏天长腿衣物");
        listviewObject8.setTv_price("1");
        listviewObjects.add(listviewObject8);
        ListviewObject listviewObject9=new ListviewObject();
        listviewObject9.setListview_iv(R.drawable.g10);
        listviewObject9.setTv_name("牙刷十只装");
        listviewObject9.setTv_price("1");
        listviewObjects.add(listviewObject9);
        return listviewObjects;
    }

    /**
     * 轮播数据源
     */
    public List<Integer>getCB(){
        integerList = new ArrayList<>();
        integerList.add(R.drawable.t1);
        integerList.add(R.drawable.t2);
        integerList.add(R.drawable.t3);
        integerList.add(R.drawable.t4);
        integerList.add(R.drawable.t5);
        return integerList;
    }
}
