package com.wokun.tysl.other.bean;

import com.wokun.tysl.other.bean.SystemNotice;

import java.util.List;

public class SystemMessageResponse {

    private List<SystemNotice> myNotice;

    public List<SystemNotice> getMyNotice() {
        return myNotice;
    }

    public void setMyNotice(List<SystemNotice> myNotice) {
        this.myNotice = myNotice;
    }
}
