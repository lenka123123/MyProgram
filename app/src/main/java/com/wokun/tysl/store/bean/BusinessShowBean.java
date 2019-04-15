package com.wokun.tysl.store.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BusinessShowBean implements Serializable {

    @SerializedName("store_id")
    private String storeId;
    @SerializedName("store_name")
    private String storeName;
    @SerializedName("store_logo")
    private String storeLogo;
    @SerializedName("description_score")
    private String descriptionScore;
    @SerializedName("service_score")
    private String serviceScore;
    @SerializedName("logistics_score")
    private String logisticsScore;

    private String address;

    public String getStoreId() {
        return storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getStoreLogo() {
        return storeLogo;
    }

    public String getDescriptionScore() {
        return descriptionScore;
    }

    public String getServiceScore() {
        return serviceScore;
    }

    public String getLogisticsScore() {
        return logisticsScore;
    }

    public String getAddress() {
        return address;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setStoreLogo(String storeLogo) {
        this.storeLogo = storeLogo;
    }

    public void setDescriptionScore(String descriptionScore) {
        this.descriptionScore = descriptionScore;
    }

    public void setServiceScore(String serviceScore) {
        this.serviceScore = serviceScore;
    }

    public void setLogisticsScore(String logisticsScore) {
        this.logisticsScore = logisticsScore;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
