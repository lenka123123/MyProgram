package com.wokun.tysl.smartretail.bean;

import java.io.Serializable;

public class OrderListBean implements Serializable {

    private String name;
    private String price;
    private String state;
    private String time;

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getState() {
        return state;
    }

    public String getTime() {
        return time;
    }
}
