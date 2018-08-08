package com.example.myapplication.ui;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;

public class Uploadservice extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //启动下载任务
        new DownLoadTask().execute("");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    /**
     * 下载任务
     * 我这里就是模拟下载，就没有解析传递过来的URL了。
     */
    private class DownLoadTask extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {
            for(int i = 1; i <= 10; i++){
                //模拟下载发送进度
                Intent intent = new Intent();
                intent.setAction("fly.upload.recervice");
                intent.putExtra("progress",10*i);
                //休息一秒
                SystemClock.sleep(1000);
                //发送广播
                sendBroadcast(intent);
            }
            return null;
        }
    }
}
