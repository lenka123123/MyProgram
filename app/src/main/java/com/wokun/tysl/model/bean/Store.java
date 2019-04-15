package com.wokun.tysl.model.bean;

import com.wokun.tysl.goods.bean.GoodsBean;
import com.wokun.tysl.shopcart.model.ShopCartEntity;

import java.io.Serializable;
import java.util.List;

public class Store extends ShopCartEntity implements Serializable{

    private String cart_id;
    private String store_id;
    private String store_logo;
    private String store_state;
    private String address;
    private String store_tel;
    private List<GoodsBean> goods_list;

    public Store(int type, GoodsBean goods) {
        super(type, goods);
    }

    public Store(int type, String store_name, String state_zh) {
        super(type, store_name, state_zh);
    }

    public Store(int type, String order_amount, String shipping_fee, String order_state) {
        super(type, order_amount, shipping_fee, order_state);
    }

    public void setCart_id(String cart_id) {
        this.cart_id = cart_id;
    }

    public String getCart_id() {
        return cart_id;
    }

    public void setStore_tel(String store_tel) {
        this.store_tel = store_tel;
    }

    public String getStore_tel() {
        return store_tel;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getStore_logo() {
        return store_logo;
    }

    public void setStore_logo(String store_logo) {
        this.store_logo = store_logo;
    }

    public String getStore_state() {
        return store_state;
    }

    public void setStore_state(String store_state) {
        this.store_state = store_state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<GoodsBean> getGoods_list() {
        return goods_list;
    }

    public void setGoods_list(List<GoodsBean> goods_list) {
        this.goods_list = goods_list;
    }
}
