package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.bean.ListviewObject;

import java.util.List;

public class ListviewAdapter extends BaseAdapter {
    private List<ListviewObject>listviewObjects;
    private Context context;
    public ListviewAdapter(Context context,List<ListviewObject>listviewObjects){
        this.context=context;
        this.listviewObjects=listviewObjects;
    }
    @Override
    public int getCount() {
        return listviewObjects.size();
    }

    @Override
    public Object getItem(int i) {
        return listviewObjects.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if(view==null){
            viewHolder=new ViewHolder();
            view= LayoutInflater.from(context).inflate(R.layout.listview,null);
            viewHolder.iv=view.findViewById(R.id.listview_iv);
            viewHolder.tv_name=view.findViewById(R.id.tv_name);
            viewHolder.tv_price=view.findViewById(R.id.tv_price);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        Glide.with(context).load(listviewObjects.get(i).getListview_iv()).into(viewHolder.iv);
        viewHolder.tv_name.setText(listviewObjects.get(i).getTv_name());
        viewHolder.tv_price.setText(listviewObjects.get(i).getTv_price());
        return view;
    }

    class ViewHolder{
        ImageView iv;
        TextView tv_name;
        TextView tv_price;
    }
}
