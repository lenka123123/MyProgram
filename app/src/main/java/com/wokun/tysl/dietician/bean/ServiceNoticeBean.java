package com.wokun.tysl.dietician.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ServiceNoticeBean implements Serializable {

    private String avatar;
    @SerializedName("user_id")
    private String userId;
    @SerializedName("username")
    private String userName;

    public ServiceNoticeBean(String user_id, String username, String avatar){
        this.userId = user_id;
        this.userName = username;
        this.avatar = avatar;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String user_id) {
        this.userId = user_id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String username) {
        this.userName = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
