package com.wokun.tysl.goodslinshou.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GoodsBean2 implements Serializable{

    @SerializedName("cart_id")
    private int cartId;

    @SerializedName("store_id")
    private int storeId;

    @SerializedName("goods_id")
    private int goodsId;

    @SerializedName("order_id")
    private String orderId;

    @SerializedName("state_zh")
    private String stateZh;

    @SerializedName("order_amount")
    private String orderAmount;

    @SerializedName("shipping_fee")
    private String shippingFee;

    @SerializedName("order_state")
    private int orderState;

    @SerializedName("store_name")
    private String storeName;

    @SerializedName("goods_image")
    private String goodsImage;

    @SerializedName("goods_name")
    private String goodsName;

    @SerializedName("goods_price")
    private double goodsPrice;

    @SerializedName("goods_state")
    private int goodsState;

    @SerializedName("goods_salesnum")
    private String goodsSalesnum;

    @SerializedName("goods_pay_price")
    private String goodsPayPrice;

    @SerializedName("goods_num")
    private int goodsNum;

    @SerializedName("goods_storage")
    private int goodsStorage;

    @SerializedName("invite_code")
    private String inviteCode;

    private boolean isSelect = false;
    private boolean isShopSelect = false;
    private int isFirst = 2;

    @SerializedName("goods_freight")
    private String goodsFreight;

    @SerializedName("goods_weight")
    private String goodsWeight;

    public int getCartId() {
        return cartId;
    }

    public int getStoreId() {
        return storeId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getStateZh() {
        return stateZh;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public String getShippingFee() {
        return shippingFee;
    }

    public int getOrderState() {
        return orderState;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getGoodsImage() {
        return goodsImage;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public double getGoodsPrice() {
        return goodsPrice;
    }

    public int getGoodsState() {
        return goodsState;
    }

    public String getGoodsSalesnum() {
        return goodsSalesnum;
    }

    public String getGoodsPayPrice() {
        return goodsPayPrice;
    }

    public int getGoodsNum() {
        return goodsNum;
    }

    public int getGoodsStorage() {
        return goodsStorage;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public boolean isShopSelect() {
        return isShopSelect;
    }

    public int getIsFirst() {
        return isFirst;
    }

    public String getGoodsFreight() {
        return goodsFreight;
    }

    public String getGoodsWeight() {
        return goodsWeight;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setStateZh(String stateZh) {
        this.stateZh = stateZh;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public void setShippingFee(String shippingFee) {
        this.shippingFee = shippingFee;
    }

    public void setOrderState(int orderState) {
        this.orderState = orderState;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public void setGoodsPrice(double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public void setGoodsState(int goodsState) {
        this.goodsState = goodsState;
    }

    public void setGoodsSalesnum(String goodsSalesnum) {
        this.goodsSalesnum = goodsSalesnum;
    }

    public void setGoodsPayPrice(String goodsPayPrice) {
        this.goodsPayPrice = goodsPayPrice;
    }

    public void setGoodsNum(int goodsNum) {
        this.goodsNum = goodsNum;
    }

    public void setGoodsStorage(int goodsStorage) {
        this.goodsStorage = goodsStorage;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public void setShopSelect(boolean shopSelect) {
        isShopSelect = shopSelect;
    }

    public void setIsFirst(int isFirst) {
        this.isFirst = isFirst;
    }

    public void setGoodsFreight(String goodsFreight) {
        this.goodsFreight = goodsFreight;
    }

    public void setGoodsWeight(String goodsWeight) {
        this.goodsWeight = goodsWeight;
    }
}
