package com.wokun.tysl.asset.bean;

import java.io.Serializable;
import java.util.List;

public class DealResponse implements Serializable {

    private List<MyDealBean> deal;

    public List<MyDealBean> getDeal() {
        return deal;
    }

    public void setDeal(List<MyDealBean> deal) {
        this.deal = deal;
    }
}
