package com.wokun.tysl.dietician.bean;

import android.content.Context;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ServiceOrderBean implements Serializable {

    private String avatar;
    @SerializedName("start_time")
    private String startTime;
    @SerializedName("finish_time")
    private String finishTime;
    @SerializedName("service_last_time")
    private String serviceLastTime;
    @SerializedName("order_amount")
    private double orderAmount;
    @SerializedName("order_state")
    private String orderState;
    @SerializedName("order_id")
    private String orderId;
    @SerializedName("user_id")
    private String userId;
    @SerializedName("username")
    private String userName;

    public String getStartTime() {
        return startTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public String getServiceLastTime() {
        return serviceLastTime;
    }

    public double getOrderAmount() {
        return orderAmount;
    }

    public String getOrderState() {
        return orderState;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public void setServiceLastTime(String serviceLastTime) {
        this.serviceLastTime = serviceLastTime;
    }

    public void setOrderAmount(double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
