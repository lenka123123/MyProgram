package com.wokun.tysl.home.bean;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StoreTypeBean implements Serializable {

    @SerializedName("goods_id")
    private int goodsId;

    @SerializedName("sgc_id")
    private String sgcId;//店铺商品分类id

    private String picture;//商品图片

    public int getGoodsId() {
        return goodsId;
    }

    public String getSgcId() {
        return sgcId;
    }

    public String getPicture() {
        return picture;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public void setSgcId(String sgcId) {
        this.sgcId = sgcId;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
