package com.wokun.tysl.order.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018\10\25 0025.
 */

public class ZhihuiGenBean  extends ZhihuiGenEntiyBean  implements Serializable {


    private  ZhihuiGenDetailBean goods_info;

    public ZhihuiGenBean(int type, ZhihuiGenDetailBean zhihuiGenDetaiBean) {
        super(type, zhihuiGenDetaiBean);
    }

    public ZhihuiGenBean(int type, String store_follow, String create_time) {
        super(type, store_follow, create_time);
    }

    public ZhihuiGenBean(int type, String username, String mobile, String order_id) {
        super(type, username, mobile, order_id);
    }


    public ZhihuiGenDetailBean getGoods_info() {
        return goods_info;
    }

    public void setGoods_info(ZhihuiGenDetailBean goods_info) {
        this.goods_info = goods_info;
    }


    public String getSub_oid() {
        return sub_oid;
    }

    public void setSub_oid(String sub_oid) {
        this.sub_oid = sub_oid;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStore_follow() {
        return store_follow;
    }

    public void setStore_follow(String store_follow) {
        this.store_follow = store_follow;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
