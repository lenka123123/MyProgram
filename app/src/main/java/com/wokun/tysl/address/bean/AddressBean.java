package com.wokun.tysl.address.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AddressBean implements Serializable {

    private String contacts;
    private String tel;

    @SerializedName("address_id")
    private String addressId;
    @SerializedName("province_id")
    private int provinceId;
    @SerializedName("city_id")
    private String cityId;
    @SerializedName("district_id")
    private String districtId;
    @SerializedName("is_default")
    private String isDefault;

    private String address;
    private String province;
    private String city;
    private String district;
    private boolean isDefaultAddress;
    private int have;

    public String getAddressId() {
        return addressId;
    }

    public String getContacts() {
        return contacts;
    }

    public String getTel() {
        return tel;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public String getCityId() {
        return cityId;
    }

    public String getDistrictId() {
        return districtId;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public String getAddress() {
        return address;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getDistrict() {
        return district;
    }

    public boolean isDefaultAddress() {
        return isDefault.equals("1");
    }

    public int getHave() {
        return have;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setDefaultAddress(boolean iDefault) {
        this.isDefaultAddress = iDefault;
    }

    public void setHave(int have) {
        this.have = have;
    }
}
