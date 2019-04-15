package com.wokun.tysl.goodslinshou.bean;

import com.google.gson.annotations.SerializedName;
import com.wokun.tysl.goods.bean.GoodsBean;
import com.wokun.tysl.model.bean.HeadAndFootEntity;

import java.util.List;

public class GoodsOrderEntityBean2 extends HeadAndFootEntity<GoodsZHBean> {

    @SerializedName("order_id")
    protected String orderId;
   @SerializedName("order_state")
    protected String orderState; //订单状态

    @SerializedName("pay_price")
    protected  String  payPrice;
    @SerializedName("express_name")
    protected   String  expressName;
    @SerializedName("express_code")
    protected   String  expressCode;
   // private   List<GoodsZHBean> subOrder;
   @SerializedName("store_name")
   protected   String  storeName;




    public GoodsOrderEntityBean2(int type, GoodsZHBean goods, String order_id) {
        super(type, goods);
        this.orderId = order_id;
    }

    public GoodsOrderEntityBean2(int type, String store_name,  String order_state, String order_id) {
        super(type, null);
        this.orderState = order_state;
        this.storeName = store_name;
        this.orderId = order_id;
    }

  public GoodsOrderEntityBean2(int type ,String pay_price, String order_state,String store_name, String order_id) {
        super(type, null);
        this.payPrice = pay_price;
        this.storeName = store_name;
        this.orderState = order_state;
        this.orderId = order_id;
    }
}
