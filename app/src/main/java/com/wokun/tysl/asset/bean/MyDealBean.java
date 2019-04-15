package com.wokun.tysl.asset.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MyDealBean implements Serializable {

    @SerializedName("d_id")
    private String dId;
    @SerializedName("seller_name")
    private String sellerName;
    @SerializedName("buyer_name")
    private String buyerName;
    private String numbers;
    @SerializedName("unit_price")
    private String unitPrice;
    @SerializedName("pay_money")
    private String payMoney;
    @SerializedName("create_time")
    private String createTime;

    public String getdId() {
        return dId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public String getNumbers() {
        return numbers;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public String getPayMoney() {
        return payMoney;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setdId(String dId) {
        this.dId = dId;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setPayMoney(String payMoney) {
        this.payMoney = payMoney;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
