package com.wokun.tysl.model.bean;

import java.util.List;

public class LookEval {

    /**
     * eid : 5
     * uid : 1638
     * source_id : 62
     * evalution_text : 不错，好吃。
     * evalution_image : ["http://192.168.1.222/uploads/eval/service/1514971143378.png","http://192.168.1.222/uploads/eval/service/1514971143324.png"]
     * evalution_time : 2016-12-06 10:12
     * evalution_score1 : 5
     * evalution_score2 : 5
     * reply_text : asdfqwwe
     * reply_time :
     * dietitian_avatar : http://img.tyitop.com/m_tyitop_com/avatar/user_1464569631755.jpg
     */

    private String eid;
    private String uid;
    private String source_id;
    private String evalution_text;
    private String evalution_time;
    private int evalution_score1;
    private int evalution_score2;
    private String reply_text;
    private String reply_time;
    private String dietitian_avatar;
    private List<String> evalution_image;

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSource_id() {
        return source_id;
    }

    public void setSource_id(String source_id) {
        this.source_id = source_id;
    }

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

    public int getEvalution_score1() {
        return evalution_score1;
    }

    public void setEvalution_score1(int evalution_score1) {
        this.evalution_score1 = evalution_score1;
    }

    public int getEvalution_score2() {
        return evalution_score2;
    }

    public void setEvalution_score2(int evalution_score2) {
        this.evalution_score2 = evalution_score2;
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

    public String getDietitian_avatar() {
        return dietitian_avatar;
    }

    public void setDietitian_avatar(String dietitian_avatar) {
        this.dietitian_avatar = dietitian_avatar;
    }

    public List<String> getEvalution_image() {
        return evalution_image;
    }

    public void setEvalution_image(List<String> evalution_image) {
        this.evalution_image = evalution_image;
    }
}
