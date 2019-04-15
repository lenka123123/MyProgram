package com.wokun.tysl.model.response;

import com.wokun.tysl.ucenter.bean.UserServiceOrder;

import java.util.List;

public class MyApplyResponse {

    private List<UserServiceOrder> myApply ;

    public List<UserServiceOrder> getMyApply() {
        return myApply;
    }

    public void setMyApply(List<UserServiceOrder> myApply) {
        this.myApply = myApply;
    }
}
