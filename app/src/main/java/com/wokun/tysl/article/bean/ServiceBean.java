package com.wokun.tysl.article.bean;

import com.wokun.tysl.dietician.bean.EvalDataBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/9/3 0003.
 */

public class ServiceBean  {

   private List<EvalDataBean> eval;

    public List<EvalDataBean> getEval() {
        return eval;
    }

    public void setEval(List<EvalDataBean> eval) {
        this.eval = eval;
    }
}
