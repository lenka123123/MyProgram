package com.wokun.tysl.smartretail.bean;

import java.io.Serializable;

public class GoodsBean implements Serializable{

    private String image;
    private String name;
    private String price;

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
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
}
