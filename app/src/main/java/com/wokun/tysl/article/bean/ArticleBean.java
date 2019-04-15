package com.wokun.tysl.article.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ArticleBean implements Serializable {

    private String title;
    private String des;

    @SerializedName("article_id")
    private String articleId;
    @SerializedName("article_image")
    private String articleImage;
    @SerializedName("add_time")
    private String addTime;
    @SerializedName("click_num")
    private String clickNum;
    @SerializedName("good_num")
    private String goodNum;
    @SerializedName("comments_nums")
    private String commentsNums;

    public String getTitle() {
        return title;
    }

    public String getDes() {
        return des;
    }

    public String getArticleId() {
        return articleId;
    }

    public String getArticleImage() {
        return articleImage;
    }

    public String getAddTime() {
        return addTime;
    }

    public String getClickNum() {
        return clickNum;
    }

    public String getGoodNum() {
        return goodNum;
    }

    public String getCommentsNums() {
        return commentsNums;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public void setArticleImage(String articleImage) {
        this.articleImage = articleImage;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public void setClickNum(String clickNum) {
        this.clickNum = clickNum;
    }

    public void setGoodNum(String goodNum) {
        this.goodNum = goodNum;
    }

    public void setCommentsNums(String commentsNums) {
        this.commentsNums = commentsNums;
    }
}
