package com.wokun.tysl.order.bean;

import com.wokun.tysl.huiyuantotal.bean.ChuankeDetaiBean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 2018\10\25 0025.
 */

public class ZhihuiOrderBean  extends ZhihuiOrderEntiyBean  implements Serializable {
    private ArrayList<ZhihuiOrderDetaiBean> subOrder;

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_sn() {
        return order_sn;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getStore_order_reward() {
        return store_order_reward;
    }

    public void setStore_order_reward(String store_order_reward) {
        this.store_order_reward = store_order_reward;
    }

    public String getOrder_state() {
        return order_state;
    }

    public void setOrder_state(String order_state) {
        this.order_state = order_state;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNums() {
        return nums;
    }

    public void setNums(String nums) {
        this.nums = nums;
    }


    public ZhihuiOrderBean(int type, ZhihuiOrderDetaiBean zhihuiOrderDetaiBean) {
        super(type, zhihuiOrderDetaiBean);
    }

    public ZhihuiOrderBean(int type, String order_sn,String create_time ,String order_state) {
        super(type, order_sn, create_time, order_state);
    }

    public ZhihuiOrderBean(int type, String username, String mobile, String order_id, String nums, String store_order_reward) {
        super(type, username, mobile, order_id, nums, store_order_reward);
    }


    public ArrayList<ZhihuiOrderDetaiBean> getSubOrder() {
        return subOrder;
    }

    public void setSubOrder(ArrayList<ZhihuiOrderDetaiBean> subOrder) {
        this.subOrder = subOrder;
    }
}
