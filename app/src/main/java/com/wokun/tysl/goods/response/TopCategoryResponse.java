package com.wokun.tysl.goods.response;


import com.wokun.tysl.goods.bean.TopCategoryBean;

import java.io.Serializable;
import java.util.List;

public class TopCategoryResponse implements Serializable {

    private List<TopCategoryBean> topCategory;

    public List<TopCategoryBean> getTopCategory() {
        return topCategory;
    }

    public void setTopCategory(List<TopCategoryBean> topCategory) {
        this.topCategory = topCategory;
    }
}
