package com.wokun.tysl.asset.bean;

import java.io.Serializable;
import java.util.List;

public class MyReleaseRecordResponse implements Serializable {

    private List<MyReleaseRecordBean> release;

    public List<MyReleaseRecordBean> getRelease() {
        return release;
    }

    public void setRelease(List<MyReleaseRecordBean> release) {
        this.release = release;
    }
}
