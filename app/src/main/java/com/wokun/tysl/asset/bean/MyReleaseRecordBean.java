package com.wokun.tysl.asset.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MyReleaseRecordBean implements Serializable {

    @SerializedName("r_id")
    private String rId;
    @SerializedName("release_num")
    private String releaseNum;
    @SerializedName("unit_price")
    private String unitPrice;
    @SerializedName("surplus_num")
    private String surplusNum;
    @SerializedName("create_time")
    private String createTime;

    public void setrId(String rId) {
        this.rId = rId;
    }

    public void setReleaseNum(String releaseNum) {
        this.releaseNum = releaseNum;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setSurplusNum(String surplusNum) {
        this.surplusNum = surplusNum;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getrId() {
        return rId;
    }

    public String getReleaseNum() {
        return releaseNum;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public String getSurplusNum() {
        return surplusNum;
    }

    public String getCreateTime() {
        return createTime;
    }
}
