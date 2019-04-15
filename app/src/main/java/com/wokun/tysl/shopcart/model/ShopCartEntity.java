package com.wokun.tysl.shopcart.model;

import com.wokun.tysl.goods.bean.GoodsBean;
import com.wokun.tysl.model.bean.HeadAndFootEntity;

import java.io.Serializable;

public class ShopCartEntity extends HeadAndFootEntity<GoodsBean> implements Serializable {

    protected String store_name;
    protected String state_zh;
    protected String order_amount;
    protected String shipping_fee;
    protected String order_state;

    public ShopCartEntity(int type, GoodsBean goods) {
        super(type, goods);
    }

    public ShopCartEntity(int type, String store_name, String state_zh) {
        super(type, null);
        this.store_name = store_name;
        this.state_zh = state_zh;
    }

    public ShopCartEntity(int type, String order_amount, String shipping_fee, String order_state) {
        super(type, null);
        this.order_amount = order_amount;
        this.shipping_fee = shipping_fee;
        this.order_state = order_state;
    }

}
