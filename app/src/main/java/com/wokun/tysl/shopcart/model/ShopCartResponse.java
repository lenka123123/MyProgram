package com.wokun.tysl.shopcart.model;


import com.wokun.tysl.model.bean.Store;

import java.util.List;

public class ShopCartResponse {
    private   String  ad;

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    private List<Store> cartList;

    public List<Store> getCartList() {
        return cartList;
    }

    public void setCartList(List<Store> cartList) {
        this.cartList = cartList;
    }
}
