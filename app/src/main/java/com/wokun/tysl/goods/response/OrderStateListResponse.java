package com.wokun.tysl.goods.response;


import com.wokun.tysl.goods.bean.GoodsOrderBean;

import java.util.List;

public class OrderStateListResponse {

    private List<GoodsOrderBean> orderStateList;

    public List<GoodsOrderBean> getOrderStateList() {
        return orderStateList;
    }

    public void setOrderStateList(List<GoodsOrderBean> orderStateList) {
        this.orderStateList = orderStateList;
    }
}
