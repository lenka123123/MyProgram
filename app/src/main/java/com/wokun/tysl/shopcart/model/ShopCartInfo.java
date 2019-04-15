package com.wokun.tysl.shopcart.model;

public class ShopCartInfo {

    private int cart_id;
    private int goods_id;
    private int goods_num;

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public void setGoods_num(int goods_num) {
        this.goods_num = goods_num;
    }

    public int getCart_id() {
        return cart_id;
    }

    public int getGoods_id() {
        return goods_id;
    }

    public int getGoods_num() {
        return goods_num;
    }
}
