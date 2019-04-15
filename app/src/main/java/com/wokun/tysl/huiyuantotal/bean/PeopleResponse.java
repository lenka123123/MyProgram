package com.wokun.tysl.huiyuantotal.bean;

import java.util.List;

/**
 * Created by Administrator on 2018\10\24 0024.
 */

public class PeopleResponse {
    private List<PeopleBean>  todayUser;

    public List<PeopleBean> getTodayUser() {
        return todayUser;
    }

    public void setTodayUser(List<PeopleBean> todayUser) {
        this.todayUser = todayUser;
    }
}
