package com.wokun.tysl.article.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/9/3 0003.
 */

public class PinjiaServiceBean implements Serializable {

    private String evalution_text;
    private String evalution_time;
    private String reply_text;
    private String reply_time;
    private String uid;
    private String evalution_score1;
    private String username;
    private String headimgurl;
    private String  eid;
    private List<String> evalution_image;

    public String getEvalution_text() {
        return evalution_text;
    }

    public void setEvalution_text(String evalution_text) {
        this.evalution_text = evalution_text;
    }

    public String getEvalution_time() {
        return evalution_time;
    }

    public void setEvalution_time(String evalution_time) {
        this.evalution_time = evalution_time;
    }

    public String getReply_text() {
        return reply_text;
    }

    public void setReply_text(String reply_text) {
        this.reply_text = reply_text;
    }

    public String getReply_time() {
        return reply_time;
    }

    public void setReply_time(String reply_time) {
        this.reply_time = reply_time;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEvalution_score1() {
        return evalution_score1;
    }

    public void setEvalution_score1(String evalution_score1) {
        this.evalution_score1 = evalution_score1;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public List<String> getEvalution_image() {
        return evalution_image;
    }

    public void setEvalution_image(List<String> evalution_image) {
        this.evalution_image = evalution_image;
    }
}
