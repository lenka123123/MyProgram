package com.wokun.tysl.goods.bean;

import com.google.gson.annotations.SerializedName;
import com.wokun.tysl.address.bean.AddressBean;

import java.io.Serializable;
import java.util.List;

public class OrderShowBean implements Serializable {

    @SerializedName("pay_price")
    private double payPrice;

    @SerializedName("cart_id_str")
    private String cartIdStr;


    @SerializedName("order_total")
    private double orderTotal;
    @SerializedName("freight_total_price")
    private double freightTotalPrice;
    @SerializedName("default_address")
    private AddressBean defaultAddress;
    @SerializedName("cart_info")
    private List<CartInfoBean> cartInfo;

    public String getCartIdStr() {
        return cartIdStr;
    }

    public void setCartIdStr(String cartIdStr) {
        this.cartIdStr = cartIdStr;
    }

    public double getPayPrice() {
        return payPrice;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public double getFreightTotalPrice() {
        return freightTotalPrice;
    }

    public AddressBean getDefaultAddress() {
        return defaultAddress;
    }

    public List<CartInfoBean> getCartInfo() {
        return cartInfo;
    }

    public void setPayPrice(double payPrice) {
        this.payPrice = payPrice;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public void setFreightTotalPrice(double freightTotalPrice) {
        this.freightTotalPrice = freightTotalPrice;
    }

    public void setDefaultAddress(AddressBean defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    public void setCartInfo(List<CartInfoBean> cartInfo) {
        this.cartInfo = cartInfo;
    }
}
