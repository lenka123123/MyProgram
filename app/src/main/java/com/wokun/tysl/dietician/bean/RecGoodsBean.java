package com.wokun.tysl.dietician.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RecGoodsBean implements Serializable {

    @SerializedName("goods_id")
    private String goodsId;
    @SerializedName("goods_name")
    private String goodsName;
    @SerializedName("goods_commission")
    private String goodsCommission;
    @SerializedName("goods_price")
    private String goodsPrice;
    @SerializedName("goods_image")
    private String goodsImage;
    @SerializedName("goods_state")
    private int goodsState;
    @SerializedName("month_salesnum")
    private String monthSalesnum;

    public String getGoodsId() {
        return goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public String getGoodsCommission() {
        return goodsCommission;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public String getGoodsImage() {
        return goodsImage;
    }

    public int getGoodsState() {
        return goodsState;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public void setGoodsCommission(String goodsCommission) {
        this.goodsCommission = goodsCommission;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
    }

    public void setGoodsState(int goodsState) {
        this.goodsState = goodsState;
    }

    public String getMonthSalesnum() {
        return monthSalesnum;
    }

    public void setMonthSalesnum(String monthSalesnum) {
        this.monthSalesnum = monthSalesnum;
    }
}
