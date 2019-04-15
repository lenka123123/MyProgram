package com.wokun.tysl.goods.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CartInfoBean implements Serializable{

    @SerializedName("goods_all_weight")
    private int goodsWeight;
    @SerializedName("store_id")
    private int storeId;
    @SerializedName("store_name")
    private String storeName;
    @SerializedName("goods_list")
    private List<GoodsBean> goodsList;

    @SerializedName("sGoodsTotalPrice")
    private int sGoodsTotalPrice;

    public int getsGoodsTotalPrice() {
        return sGoodsTotalPrice;
    }

    public void setsGoodsTotalPrice(int sGoodsTotalPrice) {
        this.sGoodsTotalPrice = sGoodsTotalPrice;
    }

    public int getGoodsWeight() {
        return goodsWeight;
    }

    public int getStoreId() {
        return storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public List<GoodsBean> getGoodsList() {
        return goodsList;
    }

    public void setGoodsWeight(int goodsWeight) {
        this.goodsWeight = goodsWeight;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setGoodsList(List<GoodsBean> goodsList) {
        this.goodsList = goodsList;
    }
}
