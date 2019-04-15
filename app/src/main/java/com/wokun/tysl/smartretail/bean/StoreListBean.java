package com.wokun.tysl.smartretail.bean;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StoreListBean implements Serializable{

    private String address;
    private double distance;

    @SerializedName("store_name")
    private String storeName;

    @SerializedName("store_picture")
    private String storePicture;

    @SerializedName("store_code")
    private String storeCode;

    public String getAddress() {
        return address;
    }

    public double getDistance() {
        return distance;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getStorePicture() {
        return storePicture;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setStorePicture(String storePicture) {
        this.storePicture = storePicture;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }
}
