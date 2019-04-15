package com.wokun.tysl.dietician.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

//营养师
public class DieticianInfoBean implements Serializable {

    private int sex;
    private double balance;
    private String uid;
    private String wechat;
    private String mobile;
    private String profile;
    private String birthday;
    private String avatar;
    private List<String> field;

    @SerializedName("dietitian_id")
    private String dietitianId;
    @SerializedName("service_fee")
    private String serviceFee;
    @SerializedName("service_nums")
    private String serviceNums;
    @SerializedName("area_info")
    private String areaInfo;
    @SerializedName("truename")
    private String trueName;
    @SerializedName("head_logo")
    private String headLogo;
    @SerializedName("field_ids")
    private String fieldIds;
    @SerializedName("idc_no")
    private String idcNo;
    @SerializedName("type_name")
    private String typeName;
    @SerializedName("favorite_good_total")
    private String favoriteGoodTotal;
    @SerializedName("favorite_store_total")
    private String favoriteStoreTotal;
    @SerializedName("favorite_dietitian_total")
    private String favoriteDietitianTotal;
    @SerializedName("favorite_article_total")
    private String favoriteArticleTotal;

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setField(List<String> field) {
        this.field = field;
    }

    public void setDietitianId(String dietitianId) {
        this.dietitianId = dietitianId;
    }

    public void setServiceFee(String serviceFee) {
        this.serviceFee = serviceFee;
    }

    public void setServiceNums(String serviceNums) {
        this.serviceNums = serviceNums;
    }

    public void setAreaInfo(String areaInfo) {
        this.areaInfo = areaInfo;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public void setHeadLogo(String headLogo) {
        this.headLogo = headLogo;
    }

    public void setFieldIds(String fieldIds) {
        this.fieldIds = fieldIds;
    }

    public void setIdcNo(String idcNo) {
        this.idcNo = idcNo;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setFavoriteGoodTotal(String favoriteGoodTotal) {
        this.favoriteGoodTotal = favoriteGoodTotal;
    }

    public void setFavoriteStoreTotal(String favoriteStoreTotal) {
        this.favoriteStoreTotal = favoriteStoreTotal;
    }

    public void setFavoriteDietitianTotal(String favoriteDietitianTotal) {
        this.favoriteDietitianTotal = favoriteDietitianTotal;
    }

    public void setFavoriteArticleTotal(String favoriteArticleTotal) {
        this.favoriteArticleTotal = favoriteArticleTotal;
    }

    public int getSex() {
        return sex;
    }

    public double getBalance() {
        return balance;
    }

    public String getUid() {
        return uid;
    }

    public String getWechat() {
        return wechat;
    }

    public String getMobile() {
        return mobile;
    }

    public String getProfile() {
        return profile;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getAvatar() {
        return avatar;
    }

    public List<String> getField() {
        return field;
    }

    public String getDietitianId() {
        return dietitianId;
    }

    public String getServiceFee() {
        return serviceFee;
    }

    public String getServiceNums() {
        return serviceNums;
    }

    public String getAreaInfo() {
        return areaInfo;
    }

    public String getTrueName() {
        return trueName;
    }

    public String getHeadLogo() {
        return headLogo;
    }

    public String getFieldIds() {
        return fieldIds;
    }

    public String getIdcNo() {
        return idcNo;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getFavoriteGoodTotal() {
        return favoriteGoodTotal;
    }

    public String getFavoriteStoreTotal() {
        return favoriteStoreTotal;
    }

    public String getFavoriteDietitianTotal() {
        return favoriteDietitianTotal;
    }

    public String getFavoriteArticleTotal() {
        return favoriteArticleTotal;
    }
}
