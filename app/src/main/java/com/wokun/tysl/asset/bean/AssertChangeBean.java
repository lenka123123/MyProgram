package com.wokun.tysl.asset.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/7/18/018.
 */

public class AssertChangeBean  implements Serializable {
    private String id;
    private String goods_name;
    private String goods_picture;
    private String goods_price;
    private String exchange_integral;
    private String stock;
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_picture() {
        return goods_picture;
    }

    public void setGoods_picture(String goods_picture) {
        this.goods_picture = goods_picture;
    }

    public String getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(String goods_price) {
        this.goods_price = goods_price;
    }

    public String getExchange_integral() {
        return exchange_integral;
    }

    public void setExchange_integral(String exchange_integral) {
        this.exchange_integral = exchange_integral;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
