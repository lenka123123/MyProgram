package com.wokun.tysl.huiyuantotal.bean;

import com.google.gson.annotations.SerializedName;
import com.wokun.tysl.model.bean.HeadAndFootEntity;

/**
 * Created by Administrator on 2018\10\24 0024.
 */

public class ChangKeEntiyBean extends HeadAndFootEntity<ChuankeDetaiBean> {
    @SerializedName("order_id")
    protected  String order_id;
    @SerializedName("uid")
    protected  String uid;
    @SerializedName("order_sn")
    protected  String order_sn;
    @SerializedName("pay_time")
    protected  String pay_time;
    @SerializedName("username")
    protected  String username;
    @SerializedName("mobile")
    protected  String mobile;
    @SerializedName("reward")
    protected  String reward;
    @SerializedName("nums")
    protected  String nums;


    public ChangKeEntiyBean(int type, ChuankeDetaiBean chuankeDetaiBean,String order_id) {
        super(type, chuankeDetaiBean);
        this.order_id =order_id;

    }

      public ChangKeEntiyBean(int type, String order_sn, String pay_time) {
        super(type, null);
        this.order_sn = order_sn;
        this.pay_time = pay_time;
    }

    public ChangKeEntiyBean(int type, String username, String mobile,String order_id,String nums,String reward) {
        super(type, null);
        this.username = username;
        this.mobile = mobile;
        this.order_id = order_id;
        this.nums = nums;
        this.reward = reward;

    }





}
