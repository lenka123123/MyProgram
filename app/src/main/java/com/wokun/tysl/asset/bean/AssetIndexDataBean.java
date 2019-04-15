package com.wokun.tysl.asset.bean;

import java.io.Serializable;
import java.util.List;

public class AssetIndexDataBean implements Serializable {

    private String banner;
    private String allIntegral;
    private List<AssetGoodsBean> goods;
    private List<String> deal;
    private String signIn;

    public String getSignIn() {
        return signIn;
    }

    public void setSignIn(String signIn) {
        this.signIn = signIn;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getAllIntegral() {
        return allIntegral;
    }

    public void setAllIntegral(String allIntegral) {
        this.allIntegral = allIntegral;
    }

    public List<AssetGoodsBean> getGoods() {
        return goods;
    }

    public void setGoods(List<AssetGoodsBean> goods) {
        this.goods = goods;
    }

    public List<String> getDeal() {
        return deal;
    }

    public void setDeal(List<String> deal) {
        this.deal = deal;
    }
}