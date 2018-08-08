package com.example.myapplication.utils;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.example.myapplication.R;

/**
 * Created by Administrator on 2016/12/8.
 */
public class LocalImageView implements Holder<Integer> {
    private ImageView  imageView;
    @Override
    public View createView(Context context) {
        imageView=new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, Integer data) {
        Glide.with(context).load(data).placeholder(R.drawable.loading).into(imageView);
    }

//    @Override
//    public void UpdateUI(Context context, int position, String data) {
//
//    }

}
