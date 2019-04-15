package com.wokun.tysl.address.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class AddressListResponse implements Serializable {

    @SerializedName("address_list")
    private List<AddressBean> addressList;

    public List<AddressBean> getAddress_list() {
        return addressList;
    }

    public void setAddress_list(List<AddressBean> address_list) {
        this.addressList = address_list;
    }
}
