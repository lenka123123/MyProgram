package com.wokun.tysl.model.response;


import com.wokun.tysl.ucenter.bean.UserServiceOrder;

import java.util.List;

public class MyHealthyResponse {

    private String aa;
    private List<UserServiceOrder> healthy;

    public String getAa() {
        return aa;
    }

    public void setAa(String aa) {
        this.aa = aa;
    }

    public List<UserServiceOrder> getHealthy() {
        return healthy;
    }

    public void setHealthy(List<UserServiceOrder> healthy) {
        this.healthy = healthy;
    }
}
