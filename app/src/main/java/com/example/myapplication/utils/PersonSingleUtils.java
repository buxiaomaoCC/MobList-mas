package com.example.myapplication.utils;

import android.content.Context;

public class PersonSingleUtils {
    private String imgUrl;
    private String name;
    private Context mcontext;
    private PersonSingleUtils(Context context){
        this.mcontext=context.getApplicationContext();
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private static PersonSingleUtils instance;
    public static PersonSingleUtils getInstance(Context context){
        if(instance==null){
            synchronized (PersonSingleUtils.class){
                if(instance==null){
                    instance=new PersonSingleUtils(context);
                }
            }
        }
        return instance;
    }
}
