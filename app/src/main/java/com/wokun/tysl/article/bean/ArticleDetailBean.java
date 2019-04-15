package com.wokun.tysl.article.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ArticleDetailBean implements Serializable {

    private int favorites;
    private String title;
    @SerializedName("article_image")
    private String articleImage;
    private String description;
    @SerializedName("share_url")
    private String shareUrl;
    @SerializedName("article_url")
    private String articleUrl;

    public int getFavorites() {
        return favorites;
    }

    public void setFavorites(int favorites) {
        this.favorites = favorites;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticle_image() {
        return articleImage;
    }

    public void setArticle_image(String article_image) {
        this.articleImage = article_image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShare_url() {
        return shareUrl;
    }

    public void setShare_url(String share_url) {
        this.shareUrl = share_url;
    }

    public String getArticle_url() {
        return articleUrl;
    }

    public void setArticle_url(String article_url) {
        this.articleUrl = article_url;
    }
}
