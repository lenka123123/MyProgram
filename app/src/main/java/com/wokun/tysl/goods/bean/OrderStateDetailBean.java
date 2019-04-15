package com.wokun.tysl.goods.bean;


import com.google.gson.annotations.SerializedName;
import com.wokun.tysl.address.bean.AddressBean;

import java.io.Serializable;

public class OrderStateDetailBean implements Serializable {

    @SerializedName("do_id")
    private String doId;
    @SerializedName("o_order_number")
    private String orderNumber;
    @SerializedName("o_discount_id")
    private String discountId;
    @SerializedName("o_goods_name")
    private String goodsName;
    @SerializedName("o_goods_picture")
    private String goodsPicture;
    @SerializedName("o_receiver_info")
    private AddressBean receiverInfo;
    @SerializedName("o_shipping_fee")
    private String shippingFee;
    @SerializedName("o_add_time")
    private String addTime;
    @SerializedName("o_deposit_finish_time")
    private String depositFinishTime;
    @SerializedName("o_retainage_finish_time")
    private String retainAgeFinishTime;
    @SerializedName("o_order_state")
    private int orderState;
    @SerializedName("o_shipping_id")
    private String shippingId;
    @SerializedName("o_deposit")
    private String deposit;
    @SerializedName("o_deposit_end_time")
    private String depositEndTime;
    @SerializedName("o_retainage_end_time")
    private String retainAgeEndTime;

    private ShippingBean shipping;
    @SerializedName("o_retainage")
    private String retainAge;
    @SerializedName("o_final_price")
    private String finalPrice;
    @SerializedName("state_name")
    private String stateName;

    public void setDoId(String doId) {
        this.doId = doId;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setDiscountId(String discountId) {
        this.discountId = discountId;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public void setGoodsPicture(String goodsPicture) {
        this.goodsPicture = goodsPicture;
    }

    public void setReceiverInfo(AddressBean receiverInfo) {
        this.receiverInfo = receiverInfo;
    }

    public void setShippingFee(String shippingFee) {
        this.shippingFee = shippingFee;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public void setDepositFinishTime(String depositFinishTime) {
        this.depositFinishTime = depositFinishTime;
    }

    public void setRetainAgeFinishTime(String retainAgeFinishTime) {
        this.retainAgeFinishTime = retainAgeFinishTime;
    }

    public void setOrderState(int orderState) {
        this.orderState = orderState;
    }

    public void setShippingId(String shippingId) {
        this.shippingId = shippingId;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public void setDepositEndTime(String depositEndTime) {
        this.depositEndTime = depositEndTime;
    }

    public void setRetainAgeEndTime(String retainAgeEndTime) {
        this.retainAgeEndTime = retainAgeEndTime;
    }

    public void setShipping(ShippingBean shipping) {
        this.shipping = shipping;
    }

    public void setRetainAge(String retainAge) {
        this.retainAge = retainAge;
    }

    public void setFinalPrice(String finalPrice) {
        this.finalPrice = finalPrice;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getDoId() {
        return doId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getDiscountId() {
        return discountId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public String getGoodsPicture() {
        return goodsPicture;
    }

    public AddressBean getReceiverInfo() {
        return receiverInfo;
    }

    public String getShippingFee() {
        return shippingFee;
    }

    public String getAddTime() {
        return addTime;
    }

    public String getDepositFinishTime() {
        return depositFinishTime;
    }

    public String getRetainAgeFinishTime() {
        return retainAgeFinishTime;
    }

    public int getOrderState() {
        return orderState;
    }

    public String getShippingId() {
        return shippingId;
    }

    public String getDeposit() {
        return deposit;
    }

    public String getDepositEndTime() {
        return depositEndTime;
    }

    public String getRetainAgeEndTime() {
        return retainAgeEndTime;
    }

    public ShippingBean getShipping() {
        return shipping;
    }

    public String getRetainAge() {
        return retainAge;
    }

    public String getFinalPrice() {
        return finalPrice;
    }

    public String getStateName() {
        return stateName;
    }
}
