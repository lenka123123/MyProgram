package com.wokun.tysl.ucenter.bean;

import android.content.Context;

import com.google.gson.annotations.SerializedName;
import com.wokun.tysl.config.Constants;

import java.io.Serializable;

public class UserServiceOrder implements Serializable {

    private String jobType;
    private String field;
    private String untie;
    @SerializedName("truename")
    private String trueName;
    @SerializedName("head_logo")
    private String headLogo;
    @SerializedName("dietitian_id")

    private String dietitianId;
    @SerializedName("order_amount")
    private String orderAmount;
    @SerializedName("service_days")
    private String serviceDays;
    @SerializedName("finish_time")
    private String finishTime;
    @SerializedName("dietitian_user_id")
    private String dietitianUserId;
    @SerializedName("service_order_id")
    private String serviceOrderId;
    @SerializedName("order_state")
    private String orderState;

    public String getUntie() {
        return untie;
    }

    public void setUntie(String untie) {
        this.untie = untie;
    }

//添加一些

 /*   private String avatar;
    @SerializedName("start_time")
    private String startTime;
    @SerializedName("service_last_time")
    private String serviceLastTime;
    @SerializedName("order_id")
    private String orderId;
    @SerializedName("user_id")
    private String userId;
    @SerializedName("username")
    private String userName;*/

   /* public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getServiceLastTime() {
        return serviceLastTime;
    }

    public void setServiceLastTime(String serviceLastTime) {
        this.serviceLastTime = serviceLastTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
*/
    public void setServiceOrderId(String serviceOrderId) {
        this.serviceOrderId = serviceOrderId;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public String getServiceOrderId() {
        return serviceOrderId;
    }

    public String getOrderState() {
        return orderState;
    }

    public String getJobType() {
        return jobType;
    }

    public String getField() {
        return field;
    }

    public String getDietitianUserId() {
        return dietitianUserId;
    }

    public String getTrueName() {
        return trueName;
    }

    public String getHeadLogo() {
        return headLogo;
    }

    public String getDietitianId() {
        return dietitianId;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public String getServiceDays() {
        return serviceDays;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setDietitianUserId(String dietitianUserId) {
        this.dietitianUserId = dietitianUserId;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public void setHeadLogo(String headLogo) {
        this.headLogo = headLogo;
    }

    public void setDietitianId(String dietitianId) {
        this.dietitianId = dietitianId;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public void setServiceDays(String serviceDays) {
        this.serviceDays = serviceDays;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }
}
