package com.wokun.tysl.goods.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GoodsListBean implements Serializable {

    @SerializedName("goods_id")
    private int goodsId;
    @SerializedName("goods_name")
    private String goodsName;
    @SerializedName("month_salesnum")
    private String monthSalesNum;
    @SerializedName("goods_storage")
    private String goodsStorage;
    @SerializedName("goods_price")
    private String goodsPrice;
    @SerializedName("goods_image")
    private String goodsImage;

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public void setMonthSalesNum(String monthSalesNum) {
        this.monthSalesNum = monthSalesNum;
    }

    public void setGoodsStorage(String goodsStorage) {
        this.goodsStorage = goodsStorage;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public String getMonthSalesNum() {
        return monthSalesNum;
    }

    public String getGoodsStorage() {
        return goodsStorage;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public String getGoodsImage() {
        return goodsImage;
    }
}
