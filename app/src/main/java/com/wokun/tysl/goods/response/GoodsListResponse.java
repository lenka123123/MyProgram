package com.wokun.tysl.goods.response;


import com.wokun.tysl.goods.bean.GoodsListBean;

import java.util.List;

public class GoodsListResponse {

    private List<GoodsListBean> goods_list;

    public List<GoodsListBean> getGoods_list() {
        return goods_list;
    }

    public void setGoods_list(List<GoodsListBean> goods_list) {
        this.goods_list = goods_list;
    }
}
