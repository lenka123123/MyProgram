package com.wokun.tysl.smartretail.bean;

import java.io.Serializable;

public class SmartRetailListBean implements Serializable {

    private String image;
    private String title;
    private String address;
    private String content;

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getAddress() {
        return address;
    }

    public String getContent() {
        return content;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
