package com.wokun.tysl.smartretail.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018\10\10 0010.
 */

public class GoodsSetteleBean implements Serializable{
    private GoodsDataBean bean;
    private int goodsNumber;

    public GoodsSetteleBean(GoodsDataBean bean, int goodsNumber) {
        this.bean = bean;
        this.goodsNumber = goodsNumber;
    }

    public GoodsDataBean getBean() {
        return bean;
    }

    public void setBean(GoodsDataBean bean) {
        this.bean = bean;
    }

    public int getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(int goodsNumber) {
        this.goodsNumber = goodsNumber;
    }
}
