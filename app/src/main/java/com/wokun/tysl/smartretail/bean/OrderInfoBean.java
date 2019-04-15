package com.wokun.tysl.smartretail.bean;

public class OrderInfoBean {

    private String userName;
    private String totalPrice;
    private String productImageUrl;
    private String productName;
    private String productPrice;
    private String productEarnings;

    public String getUserName() {
        return userName;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public String getProductEarnings() {
        return productEarnings;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public void setProductEarnings(String productEarnings) {
        this.productEarnings = productEarnings;
    }
}