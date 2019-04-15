package com.wokun.tysl.myyijian.fragment;

import android.content.Intent;

/**
 * Created by Administrator on 2018\10\18 0018.
 */
//int requestCode, int resultCode, Intent data
public class MyBean {
    public int requestCode;
    public int resultCode;
    public Intent data;

    public MyBean(int requestCode, int resultCode, Intent data) {
        this.requestCode = requestCode;
        this.resultCode = resultCode;
        this.data = data;
    }
}
