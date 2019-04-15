package com.wokun.tysl.smartretail.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018\10\9 0009.
 */

public class SureGoodsListResponse   implements Serializable {
    private   int  haveSelfLifting;
    private   ZitiBean selfLifting;
    private    ShouhuoBean defaultAddress;

    public int getHaveSelfLifting() {
        return haveSelfLifting;
    }

    public void setHaveSelfLifting(int haveSelfLifting) {
        this.haveSelfLifting = haveSelfLifting;
    }

    public ZitiBean getSelfLifting() {
        return selfLifting;
    }

    public void setSelfLifting(ZitiBean selfLifting) {
        this.selfLifting = selfLifting;
    }

    public ShouhuoBean getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(ShouhuoBean defaultAddress) {
        this.defaultAddress = defaultAddress;
    }
}
