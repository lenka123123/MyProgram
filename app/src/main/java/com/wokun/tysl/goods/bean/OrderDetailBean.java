package com.wokun.tysl.goods.bean;

import com.google.gson.annotations.SerializedName;
import com.wokun.tysl.address.bean.AddressBean;

import java.io.Serializable;
import java.util.ArrayList;

public class OrderDetailBean implements Serializable{

    @SerializedName("order_id")
    private String orderId;
    @SerializedName("order_state")
    private int orderState;
    @SerializedName("store_name")
    private String storeName;
    @SerializedName("goods_amount")
    private String goodsAmount;
    @SerializedName("shipping_fee")
    private String shippingFee;
    @SerializedName("order_amount")
    private String orderAmount;
    @SerializedName("order_sn")
    private String orderSn;
    @SerializedName("buyer_leave_message")
    private String buyerLeaveMessage;
    @SerializedName("add_time")
    private String addTime;
    @SerializedName("finish_time")
    private String finishTime;
    @SerializedName("receiver_info")
    private AddressBean receiverInfo;
    @SerializedName("shipping_info")
    private ShippingBean shippingInfo;

    @SerializedName("goods_eval_integral")
    private int goodsEvalIntegral;
    @SerializedName("state_zh")
    private String stateZh;

    private ArrayList<GoodsBean> goods;

    public ShippingBean getShippingInfo() {
        return shippingInfo;
    }

    public void setShippingInfo(ShippingBean shippingInfo) {
        this.shippingInfo = shippingInfo;
    }

    public String getOrderId() {
        return orderId;
    }

    public int getOrderState() {
        return orderState;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getGoodsAmount() {
        return goodsAmount;
    }

    public String getShippingFee() {
        return shippingFee;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public String getBuyerLeaveMessage() {
        return buyerLeaveMessage;
    }

    public String getAddTime() {
        return addTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public AddressBean getReceiverInfo() {
        return receiverInfo;
    }

    public int getGoodsEvalIntegral() {
        return goodsEvalIntegral;
    }

    public String getStateZh() {
        return stateZh;
    }

    public ArrayList<GoodsBean> getGoods() {
        return goods;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setOrderState(int orderState) {
        this.orderState = orderState;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setGoodsAmount(String goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    public void setShippingFee(String shippingFee) {
        this.shippingFee = shippingFee;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public void setBuyerLeaveMessage(String buyerLeaveMessage) {
        this.buyerLeaveMessage = buyerLeaveMessage;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public void setReceiverInfo(AddressBean receiverInfo) {
        this.receiverInfo = receiverInfo;
    }

    public void setGoodsEvalIntegral(int goodsEvalIntegral) {
        this.goodsEvalIntegral = goodsEvalIntegral;
    }

    public void setStateZh(String stateZh) {
        this.stateZh = stateZh;
    }

    public void setGoods(ArrayList<GoodsBean> goods) {
        this.goods = goods;
    }
}
