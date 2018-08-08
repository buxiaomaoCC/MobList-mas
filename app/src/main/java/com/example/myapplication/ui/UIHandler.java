package com.example.myapplication.ui;

import android.os.Handler;
import android.os.Message;

/**
 * Created by zhangbin on 2018/6/13.
 */

public  class UIHandler extends Handler {
    private static final int MSG_AUTH_CANCEL=1;
    private static final int MSG_AUTH_ERROR=2;
    private static final int MSG_AUTH_COMPLETE=3;
    @Override
    public void handleMessage(Message msg) {
        switch (msg.what){

        }
    }
}
