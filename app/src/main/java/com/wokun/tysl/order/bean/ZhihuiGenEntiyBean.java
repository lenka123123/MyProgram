package com.wokun.tysl.order.bean;

import com.google.gson.annotations.SerializedName;
import com.wokun.tysl.model.bean.HeadAndFootEntity;

/**
 * Created by Administrator on 2018\10\25 0025.
 */

public class ZhihuiGenEntiyBean  extends HeadAndFootEntity<ZhihuiGenDetailBean> {
    @SerializedName("sub_oid")
    protected  String sub_oid;
    @SerializedName("order_id")
    protected  String order_id;
    @SerializedName("mobile")
    protected  String mobile;
    @SerializedName("store_follow")
    protected  String store_follow;
    @SerializedName("create_time")
    protected  String create_time;
    @SerializedName("username")
    protected  String username;


    public ZhihuiGenEntiyBean(int type, ZhihuiGenDetailBean zhihuiGenDetaiBean) {
        super(type, zhihuiGenDetaiBean);

    }

    public ZhihuiGenEntiyBean(int type, String store_follow, String create_time) {
        super(type, null);
        this.store_follow = store_follow;
        this.create_time = create_time;
    }

    public ZhihuiGenEntiyBean(int type, String username, String mobile,String order_id) {
        super(type, null);
        this.username = username;
        this.mobile = mobile;
        this.order_id = order_id;


    }

}
