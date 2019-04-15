package com.wokun.tysl.goodslinshou.bean;

import com.google.gson.annotations.SerializedName;
import com.wokun.tysl.goods.bean.GoodsOrderEntityBean;
import com.wokun.tysl.model.bean.HeadAndFootEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018\9\26 0026.
 */

public class GoodsZhihuibean extends GoodsOrderEntityBean2 implements Serializable {
/*    private   String  order_id;
    private   String  order_state;
    private   String  pay_price;
    private   String  express_name;
    private   String  express_code;*/
    private   List    <GoodsZHBean> subOrder;
   // private   String  store_name;
/*   @SerializedName("order_state")
   protected String orderState;*/


 public GoodsZhihuibean(int type, GoodsZHBean subOrder, String order_id) {
        super(type, subOrder,order_id);
    }

    public GoodsZhihuibean(int type, String store_name, String order_state, String order_id) {
        super(type, store_name,order_state,order_id);
    }

    public GoodsZhihuibean(int type, String pay_price,  String order_state,String store_name , String order_id,ArrayList<GoodsZHBean> subOrder) {
        super(type, pay_price, order_state,store_name ,order_id);
        this.subOrder = subOrder;
    }


    public String getOrder_id() {
        return orderId;
    }

    public void setOrder_id(String order_id) {
        this.orderId = order_id;
    }


  public String getOrder_state() {
        return orderState;
    }

    public void setOrder_state(String order_state) {
        this.orderState = order_state;
    }

    public String getPay_price() {
        return payPrice;
    }

    public void setPay_price(String pay_price) {
        this.payPrice = pay_price;
    }

    public String getExpress_name() {
        return expressName;
    }

    public void setExpress_name(String express_name) {
        this.expressName = express_name;
    }

    public String getExpress_code() {
        return expressCode;
    }

    public void setExpress_code(String express_code) {
        this.expressCode = express_code;
    }

    public List<GoodsZHBean> getSubOrder() {
        return subOrder;
    }

    public void setSubOrder(List<GoodsZHBean> subOrder) {
        this.subOrder = subOrder;
    }

    public String getStore_name() {
        return storeName;
    }

    public void setStore_name(String store_name) {
        this.storeName = store_name;
    }
}
