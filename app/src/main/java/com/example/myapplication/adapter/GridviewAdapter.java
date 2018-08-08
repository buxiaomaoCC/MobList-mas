package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.bean.GridviewObject;
import com.example.myapplication.view.CircleImageView;

import java.util.List;
import java.util.zip.Inflater;

public class GridviewAdapter extends BaseAdapter {
    private Context context;
    private List<GridviewObject>gridviewObjectList;
    public GridviewAdapter(Context context,List<GridviewObject>gridviewObjectList){
        this.context=context;
        this.gridviewObjectList=gridviewObjectList;
    }
    @Override
    public int getCount() {
        return gridviewObjectList.size();
    }

    @Override
    public Object getItem(int i) {
        return gridviewObjectList.get(i);
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
            view= LayoutInflater.from(context).inflate(R.layout.gridview,null);
            viewHolder.gridview=view.findViewById(R.id.grid_img);
            viewHolder.gridview_tv=view.findViewById(R.id.grid_tv);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        Glide.with(context).load(gridviewObjectList.get(i).getGrid_view()).into(viewHolder.gridview);
        viewHolder.gridview_tv.setText(gridviewObjectList.get(i).getGrid_tv());
        return view;
    }

    class ViewHolder{
        private CircleImageView gridview;
        private TextView gridview_tv;
    }
}
