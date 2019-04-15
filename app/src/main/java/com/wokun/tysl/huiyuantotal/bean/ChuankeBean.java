package com.wokun.tysl.huiyuantotal.bean;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 2018\10\24 0024.
 */

public class ChuankeBean  extends ChangKeEntiyBean  implements Serializable {


    private ArrayList<ChuankeDetaiBean> subOrder;

    public ChuankeBean(int type, ChuankeDetaiBean chuankeDetaiBean, String order_id) {
        super(type, chuankeDetaiBean,order_id);
        this.order_id = order_id;
    }

    public ChuankeBean(int type, String order_sn, String pay_time) {
        super(type, order_sn,pay_time);
        this.order_sn = order_sn;
        this.pay_time = pay_time;
    }


    public ChuankeBean(int type, String username, String mobile,String order_id,String nums,String reward) {
        super(type, username,mobile,order_id,nums,reward);
        this.username = username;
        this.mobile = mobile;
        this.order_id = order_id;
        this.nums = nums;
        this.reward = reward;

    }


    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getOrder_sn() {
        return order_sn;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }

    public String getPay_time() {
        return pay_time;
    }

    public void setPay_time(String pay_time) {
        this.pay_time = pay_time;
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

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public String getNums() {
        return nums;
    }

    public void setNums(String nums) {
        this.nums = nums;
    }

    public ArrayList<ChuankeDetaiBean> getSubOrder() {
        return subOrder;
    }

    public void setSubOrder(ArrayList<ChuankeDetaiBean> subOrder) {
        this.subOrder = subOrder;
    }
}
