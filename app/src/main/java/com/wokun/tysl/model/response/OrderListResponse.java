package com.wokun.tysl.model.response;

import com.wokun.tysl.dietician.bean.ServiceOrderBean;

import java.util.List;

public class OrderListResponse {

    private List<ServiceOrderBean> orderList;

    public List<ServiceOrderBean> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<ServiceOrderBean> orderList) {
        this.orderList = orderList;
    }
}
