package com.wokun.tysl.home.bean;


import java.io.Serializable;

public class ArticleTypeBean implements Serializable {

    private int class_id;//文章分类id（请求文章列表接口带上的参数）
    private String picture;

    public int getClass_id() {
        return class_id;
    }

    public String getPicture() {
        return picture;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
