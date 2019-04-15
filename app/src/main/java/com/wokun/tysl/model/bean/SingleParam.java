package com.wokun.tysl.model.bean;

import com.google.gson.annotations.SerializedName;

public class SingleParam {

    private String mobile;
    private String balance;
    private String url;
    private String kd_url;
    private String cart_id_str;
    private String headImgUrl;
    private String token;
    @SerializedName("order_number")
    private String orderNumber;

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getBalance() {
        return balance;
    }

    public String getKd_url() {
        return kd_url;
    }

    public void setKd_url(String kd_url) {
        this.kd_url = kd_url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCart_id_str(String cart_id_str) {
        this.cart_id_str = cart_id_str;
    }

    public String getCart_id_str() {
        return cart_id_str;
    }
}
