package com.wokun.tysl.goods.bean;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ShippingBean implements Serializable {

    @SerializedName("shipping_id")
    private String shippingId;
    @SerializedName("express_name")
    private String expressName;
    @SerializedName("shipping_code")
    private String shippingCode;
    @SerializedName("shipping_time")
    private String shippingTime;
    @SerializedName("express_number")
    private String expressNumber;

    public String getExpressNumber() {
        return expressNumber;
    }

    public void setExpressNumber(String expressNumber) {
        this.expressNumber = expressNumber;
    }

    public String getShippingId() {
        return shippingId;
    }

    public String getExpressName() {
        return expressName;
    }

    public String getShippingCode() {
        return shippingCode;
    }

    public String getShippingTime() {
        return shippingTime;
    }

    public void setShippingId(String shippingId) {
        this.shippingId = shippingId;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public void setShippingCode(String shippingCode) {
        this.shippingCode = shippingCode;
    }

    public void setShippingTime(String shippingTime) {
        this.shippingTime = shippingTime;
    }
}
