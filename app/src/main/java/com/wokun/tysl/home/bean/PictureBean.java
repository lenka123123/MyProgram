package com.wokun.tysl.home.bean;


import java.io.Serializable;

public class PictureBean implements Serializable {

    private int type;
    private String picture;

    public int getType() {
        return type;
    }

    public String getPicture() {
        return picture;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
