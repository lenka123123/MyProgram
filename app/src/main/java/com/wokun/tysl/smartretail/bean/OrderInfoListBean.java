package com.wokun.tysl.smartretail.bean;

import java.io.Serializable;

public class OrderInfoListBean implements Serializable {

    private String image;      //商品图片
    private String name;       //商品名称
    private String price;      //商品单价
    private String purchaser;  //购买人
    private String mobile;     //电话

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getPurchaser() {
        return purchaser;
    }

    public String getMobile() {
        return mobile;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setPurchaser(String purchaser) {
        this.purchaser = purchaser;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
