package com.wokun.tysl.goods.bean;

import com.wokun.tysl.dietician.bean.EvalDataBean;

import java.io.Serializable;
import java.util.List;

public class EvalutionsBean implements Serializable {

    private String nums;
    private List<EvalDataBean> content;

    public String getNums() {
        return nums;
    }

    public void setNums(String nums) {
        this.nums = nums;
    }

    public List<EvalDataBean> getContent() {
        return content;
    }

    public void setContent(List<EvalDataBean> content) {
        this.content = content;
    }
}
