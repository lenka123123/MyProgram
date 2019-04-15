package com.wokun.tysl.model.bean;

public class Alipay2 {
    private String orderString;
    private String name;
    private String mobile;
    private String address;
    private String remark;
    private String order_total;
    private String freight_total_price;
    private String pay_price;
    private String pay_type;
    private String cart_id_str;
    private String province_id;


    private String buy_gid;
    private  String order_id;
    private  WXPayResult config;

    public String getBuy_gid() {
        return buy_gid;
    }

    public void setBuy_gid(String buy_gid) {
        this.buy_gid = buy_gid;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public WXPayResult getConfig() {
        return config;
    }

    public void setConfig(WXPayResult config) {
        this.config = config;
    }

    @Override
    public String toString() {
        return "AliPay{" +
                "name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address='" + address + '\'' +
                ", remark='" + remark + '\'' +
                ", order_total='" + order_total + '\'' +
                ", freight_total_price='" + freight_total_price + '\'' +
                ", pay_price='" + pay_price + '\'' +
                ", pay_type='" + pay_type + '\'' +
                ", cart_id_str='" + cart_id_str + '\'' +
                ", province_id='" + province_id + '\'' +
                ", orderString='" + orderString + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setOrder_total(String order_total) {
        this.order_total = order_total;
    }

    public void setFreight_total_price(String freight_total_price) {
        this.freight_total_price = freight_total_price;
    }

    public void setPay_price(String pay_price) {
        this.pay_price = pay_price;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }

    public void setCart_id_str(String cart_id_str) {
        this.cart_id_str = cart_id_str;
    }

    public void setProvince_id(String province_id) {
        this.province_id = province_id;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getAddress() {
        return address;
    }

    public String getRemark() {
        return remark;
    }

    public String getOrder_total() {
        return order_total;
    }

    public String getFreight_total_price() {
        return freight_total_price;
    }

    public String getPay_price() {
        return pay_price;
    }

    public String getPay_type() {
        return pay_type;
    }

    public String getCart_id_str() {
        return cart_id_str;
    }

    public String getProvince_id() {
        return province_id;
    }

    public String getOrderString() {
        return orderString;
    }

    public void setOrderString(String orderString) {
        this.orderString = orderString;
    }
}
