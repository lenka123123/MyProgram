package com.wokun.tysl.article.bean;

import com.wokun.tysl.dietician.bean.EvalDataBean;

import java.util.List;

/**
 * Created by Administrator on 2018\12\10 0010.
 */

public class GoodsServiceBean {

    private List<EvalDataBean> evalList;

    public List<EvalDataBean> getEvalList() {
        return evalList;
    }

    public void setEvalList(List<EvalDataBean> evalList) {
        this.evalList = evalList;
    }
}
