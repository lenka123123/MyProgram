package com.wokun.tysl.dietician.bean;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class EvalDataBean implements Serializable {

    private String uid;
    private int eid;
     private   String    avatar;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @SerializedName("evalution_text")
    private String evalutionText;
    @SerializedName("evalution_time")
    private String evalutionTime;
    @SerializedName("reply_text")
    private String replyText;
    @SerializedName("reply_time")
    private String replyTime;
    @SerializedName("evalution_score1")
    private String evalutionScore1;
    @SerializedName("username")
    private String userName;
    @SerializedName("headimgurl")
    private String headImgUrl;
    @SerializedName("evalution_image")
    private List<?> evalutionImage;

    public String getUid() {
        return uid;
    }

    public int getEid() {
        return eid;
    }

    public String getEvalutionText() {
        return evalutionText;
    }

    public String getEvalutionTime() {
        return evalutionTime;
    }

    public String getReplyText() {
        return replyText;
    }

    public String getReplyTime() {
        return replyTime;
    }

    public String getEvalutionScore1() {
        return evalutionScore1;
    }

    public String getUserName() {
        return userName;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public List<?> getEvalutionImage() {
        return evalutionImage;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public void setEvalutionText(String evalutionText) {
        this.evalutionText = evalutionText;
    }

    public void setEvalutionTime(String evalutionTime) {
        this.evalutionTime = evalutionTime;
    }

    public void setReplyText(String replyText) {
        this.replyText = replyText;
    }

    public void setReplyTime(String replyTime) {
        this.replyTime = replyTime;
    }

    public void setEvalutionScore1(String evalutionScore1) {
        this.evalutionScore1 = evalutionScore1;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public void setEvalutionImage(List<?> evalutionImage) {
        this.evalutionImage = evalutionImage;
    }
}
