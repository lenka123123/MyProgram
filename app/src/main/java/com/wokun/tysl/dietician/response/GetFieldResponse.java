package com.wokun.tysl.dietician.response;


import com.wokun.tysl.dietician.bean.DieticianFieldTypeBean;

import java.util.List;

public class GetFieldResponse {

    private List<DieticianFieldTypeBean> field;

    public List<DieticianFieldTypeBean> getField() {
        return field;
    }

    public void setField(List<DieticianFieldTypeBean> field) {
        this.field = field;
    }
}
