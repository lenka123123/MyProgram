package com.wokun.tysl.asset.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AssetsReleaseInOrOut implements Serializable {

    private double money;
    private String canUseNum;
    private String allAssets;
    private String useNum;
    @SerializedName("today_price")
    private String todayPrice;


    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getAllAssets() {
        return allAssets;
    }

    public void setAllAssets(String allAssets) {
        this.allAssets = allAssets;
    }

    public String getUseNum() {
        return useNum;
    }

    public void setUseNum(String useNum) {
        this.useNum = useNum;
    }

    public String getCanUseNum() {
        return canUseNum;
    }

    public void setCanUseNum(String canUseNum) {
        this.canUseNum = canUseNum;
    }

    public String getTodayPrice() {
        return todayPrice + "元";
    }

    public void setTodayPrice(String todayPrice) {
        this.todayPrice = todayPrice;
    }
}