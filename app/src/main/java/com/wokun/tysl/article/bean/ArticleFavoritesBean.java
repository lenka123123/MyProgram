package com.wokun.tysl.article.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

//文章收藏
public class ArticleFavoritesBean implements Serializable {

    private String title;
    @SerializedName("truename")
    private String trueName;
    @SerializedName("article_id")
    private String articleId;
    @SerializedName("article_image")
    private String articleImage;
    @SerializedName("click_num")
    private String clickNum;

    public String getTitle() {
        return title;
    }

    public String getTrueName() {
        return trueName;
    }

    public String getArticleId() {
        return articleId;
    }

    public String getArticleImage() {
        return articleImage;
    }

    public String getClickNum() {
        return clickNum;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public void setArticleImage(String articleImage) {
        this.articleImage = articleImage;
    }

    public void setClickNum(String clickNum) {
        this.clickNum = clickNum;
    }
}
