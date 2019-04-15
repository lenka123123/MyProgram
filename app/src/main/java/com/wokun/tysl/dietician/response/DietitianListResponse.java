package com.wokun.tysl.dietician.response;


import com.wokun.tysl.dietician.bean.DietitianListBean;

import java.util.List;

public class DietitianListResponse {

    private List<DietitianListBean> dietitianList;

    public void setDietitianList(List<DietitianListBean> dietitianList) {
        this.dietitianList = dietitianList;
    }

    public List<DietitianListBean> getDietitianList() {
        return dietitianList;
    }
}
