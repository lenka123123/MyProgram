package com.wokun.tysl.asset.bean;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AssetReleaseBean implements Serializable {

    @SerializedName("r_id")
    private String id;
    @SerializedName("username")
    private String userName;
    @SerializedName("unit_price")
    private String unitPrice;
    @SerializedName("surplus_num")
    private String surplusNum;
    @SerializedName("create_time")
    private String createTime;

    @SerializedName("release_num")
    private String releaseNum;

    public void setReleaseNum(String releaseNum) {
        this.releaseNum = releaseNum;
    }

    public String getReleaseNum() {
        return releaseNum;
    }

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
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

    public void setId(String id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
}
