package com.wokun.tysl.goodslinshou.bean;

import com.wokun.tysl.goods.bean.GoodsBean;
import com.wokun.tysl.model.bean.HeadAndFootEntity;

/**
 * Created by Administrator on 2018\9\26 0026.
 */

public class GoodsZHBean {
    private   String  order_id;
    private   String  goods_id;
    private   String  goods_price;
    private   String  goods_name;
    private   String  goods_image;
    private   String  goods_num;

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(String goods_price) {
        this.goods_price = goods_price;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_image() {
        return goods_image;
    }

    public void setGoods_image(String goods_image) {
        this.goods_image = goods_image;
    }

    public String getGoods_num() {
        return goods_num;
    }

    public void setGoods_num(String goods_num) {
        this.goods_num = goods_num;
    }
}
