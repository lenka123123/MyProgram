package com.wokun.tysl.model.bean;

import com.wokun.tysl.goodslinshou.bean.GoodsZHBean;
import com.wokun.tysl.huiyuantotal.bean.ChuankeDetaiBean;

import java.io.Serializable;

public class HeadAndFootEntity<T> implements Serializable {

    public int currentType;
    public T t;

    public HeadAndFootEntity(int type, T t) {
        this.currentType = type;
        this.t = t;
    }


}
