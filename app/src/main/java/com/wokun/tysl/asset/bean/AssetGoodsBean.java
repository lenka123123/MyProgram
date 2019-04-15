package com.wokun.tysl.asset.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AssetGoodsBean implements Serializable{

    private String id;
    @SerializedName("goods_name")
    private String goodsName;
    @SerializedName("exchange_integral")
    private String exchangeIntegral;
    @SerializedName("goods_picture")
    private String goodsPicture;

    public String getId() {
        return id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public String getExchangeIntegral() {
        return exchangeIntegral;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public void setExchangeIntegral(String exchangeIntegral) {
        this.exchangeIntegral = exchangeIntegral;
    }

    public String getGoodsPicture() {
        return goodsPicture;
    }

    public void setGoodsPicture(String goodsPicture) {
        this.goodsPicture = goodsPicture;
    }
}
