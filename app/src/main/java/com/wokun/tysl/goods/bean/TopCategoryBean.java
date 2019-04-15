package com.wokun.tysl.goods.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TopCategoryBean implements Serializable {

    @SerializedName("gc_id")
    private String gcId;

    @SerializedName("gc_name")
    private String gcName;

    public String getGcId() {
        return gcId;
    }

    public String getGcName() {
        return gcName;
    }

    public void setGcId(String gcId) {
        this.gcId = gcId;
    }

    public void setGcName(String gcName) {
        this.gcName = gcName;
    }
}