package com.wokun.tysl.order.bean;

import com.google.gson.annotations.SerializedName;
import com.wokun.tysl.model.bean.HeadAndFootEntity;

/**
 * Created by Administrator on 2018\10\25 0025.
 */

public class ZhihuiOrderEntiyBean extends HeadAndFootEntity<ZhihuiOrderDetaiBean> {
    @SerializedName("order_id")
    protected  String order_id;
    @SerializedName("order_sn")
    protected  String order_sn;
    @SerializedName("create_time")
    protected  String create_time;
    @SerializedName("store_order_reward")
    protected  String store_order_reward;
    @SerializedName("order_state")
    protected  String order_state;
    @SerializedName("username")
    protected  String username;
    @SerializedName("mobile")
    protected  String mobile;
    @SerializedName("nums")
    protected  String nums;


    public ZhihuiOrderEntiyBean(int type, ZhihuiOrderDetaiBean zhihuiOrderDetaiBean) {
        super(type, zhihuiOrderDetaiBean);
    }

    public ZhihuiOrderEntiyBean(int type, String order_sn,String create_time, String order_state) {
        super(type, null);
        this.order_sn = order_sn;
        this.order_state = order_state;
        this.create_time =create_time;
    }

    public ZhihuiOrderEntiyBean(int type, String username, String mobile,String order_id,String nums,String store_order_reward) {
        super(type, null);
        this.username = username;
        this.mobile = mobile;
        this.order_id = order_id;
        this.nums = nums;
        this.store_order_reward = store_order_reward;

    }
}
