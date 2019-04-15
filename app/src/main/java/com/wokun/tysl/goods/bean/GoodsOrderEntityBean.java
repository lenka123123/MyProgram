package com.wokun.tysl.goods.bean;

import com.google.gson.annotations.SerializedName;
import com.wokun.tysl.model.bean.HeadAndFootEntity;

public class GoodsOrderEntityBean extends HeadAndFootEntity<GoodsBean> {

    @SerializedName("store_name")
    protected String storeName;//店铺名称
    @SerializedName("state_zh")
    protected String stateZh;//订单状态中文
    @SerializedName("order_amount")
    protected String orderAmount;//订单总价
    @SerializedName("shipping_fee")
    protected String shippingFee; //邮费
    @SerializedName("order_state")
    protected int orderState; //订单状态
    @SerializedName("order_id")
    protected String orderId;

    public GoodsOrderEntityBean(int type, GoodsBean goods, String order_id) {
        super(type, goods);
        this.orderId = order_id;
    }

    public GoodsOrderEntityBean(int type, String store_name, String state_zh, String order_id) {
        super(type, null);
        this.storeName = store_name;
        this.stateZh = state_zh;
        this.orderId = order_id;
    }

    public GoodsOrderEntityBean(int type, String order_amount, String shipping_fee, int order_state, String order_id) {
        super(type, null);
        this.orderAmount = order_amount;
        this.shippingFee = shipping_fee;
        this.orderState = order_state;
        this.orderId = order_id;
    }
}
