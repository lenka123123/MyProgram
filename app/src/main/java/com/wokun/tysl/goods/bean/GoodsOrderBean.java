package com.wokun.tysl.goods.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class GoodsOrderBean extends GoodsOrderEntityBean implements Serializable {

    @SerializedName("goods_amount")
    private String goodsAmount;//商品总价
    private ArrayList<GoodsBean> goods;

    public GoodsOrderBean(int type, GoodsBean goods, String order_id) {
        super(type, goods,order_id);
    }

    public GoodsOrderBean(int type, String store_name, String state_zh, String order_id) {
        super(type, store_name, state_zh,order_id);
    }

    public GoodsOrderBean(int type, String order_amount, String shipping_fee, int order_state, String order_id, ArrayList<GoodsBean> goods) {
        super(type, order_amount, shipping_fee, order_state, order_id);
        this.goods = goods;
    }

    public String getOrder_id() {
        return orderId;
    }

    public int getOrder_state() {
        return orderState;
    }

    public String getStore_name() {
        return storeName;
    }

    public String getGoodsAmount() {
        return goodsAmount;
    }

    public String getShipping_fee() {
        return shippingFee;
    }

    public String getState_zh() {
        return stateZh;
    }

    public ArrayList<GoodsBean> getGoods() {
        return goods;
    }

    public void setOrder_id(String order_id) {
        this.orderId = order_id;
    }

    public void setOrder_state(int order_state) {
        this.orderState = order_state;
    }

    public void setStore_name(String store_name) {
        this.storeName = store_name;
    }

    public void setGoodsAmount(String goods_amount) {
        this.goodsAmount = goods_amount;
    }

    public void setShipping_fee(String shipping_fee) {
        this.shippingFee = shipping_fee;
    }

    public void setState_zh(String state_zh) {
        this.stateZh = state_zh;
    }

    public void setGoods(ArrayList<GoodsBean> goods) {
        this.goods = goods;
    }

    public String getOrder_amount() {
        return orderAmount;
    }

    public void setOrder_amount(String order_amount) {
        this.orderAmount = order_amount;
    }
}
