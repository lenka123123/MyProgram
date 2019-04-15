package com.wokun.tysl.smartretail.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GoodsDataBean implements Serializable{

    private String gid;
    @SerializedName("goods_name")
    private String goodsName;
    @SerializedName("goods_picture")
    private String goodsPicture;
    @SerializedName("goods_price")
    private String goodsPrice;
    @SerializedName("goods_detail")
    private String goodsDetail;
    @SerializedName("sale_num")
    private String saleSum;

    public String getSaleSum() {
        return saleSum;
    }

    public void setSaleSum(String saleSum) {
        this.saleSum = saleSum;
    }

    private int productAmount;//总计
    private int productRemain = 100;//剩余

    public String getGid() {
        return gid;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public String getGoodsPicture() {
        return goodsPicture;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public String getGoodsDetail() {
        return goodsDetail;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public void setGoodsPicture(String goodsPicture) {
        this.goodsPicture = goodsPicture;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public void setGoodsDetail(String goodsDetail) {
        this.goodsDetail = goodsDetail;
    }

    public int getProductAmount() {
        return productAmount;
    }

    public int getProductRemain() {
        return productRemain;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }

    public void setProductRemain(int productRemain) {
        this.productRemain = productRemain;
    }
}
