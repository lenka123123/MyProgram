package com.wokun.tysl.smartretail.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018\9\21 0021.
 */

public class ZhihuiGoodsDetailBean  implements Serializable {
     private   GoodsInfo  goodsInfo;
     private   String  phone;

    public GoodsInfo getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(GoodsInfo goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNums() {
        return nums;
    }

    public void setNums(String nums) {
        this.nums = nums;
    }

    public Eval getEval() {
        return eval;
    }

    public void setEval(Eval eval) {
        this.eval = eval;
    }

    public String getHaoping() {
        return haoping;
    }

    public void setHaoping(String haoping) {
        this.haoping = haoping;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    private   String  nums;
     private    Eval   eval;
     private  String haoping;
     private   String  share_url;


}
