package com.wokun.tysl.goods.response;

import com.wokun.tysl.goods.bean.SubCategoryBean;

import java.io.Serializable;
import java.util.List;

public class SubCategoryResponse implements Serializable {

    private List<SubCategoryBean> subCategory;
    private   String  banner;

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public List<SubCategoryBean> getGoodsClass() {
        return goodsClass;
    }

    public void setGoodsClass(List<SubCategoryBean> goodsClass) {
        this.goodsClass = goodsClass;
    }

    private List<SubCategoryBean>  goodsClass;

    public List<SubCategoryBean> getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(List<SubCategoryBean> subCategory) {
        this.subCategory = subCategory;
    }
}
