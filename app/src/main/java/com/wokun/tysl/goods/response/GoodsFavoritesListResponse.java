package com.wokun.tysl.goods.response;


import com.wokun.tysl.goods.bean.GoodsBean;

import java.util.List;

public class GoodsFavoritesListResponse {

    private List<GoodsBean> likeList;

    public List<GoodsBean> getLikeList() {
        return likeList;
    }

    public void setLikeList(List<GoodsBean> likeList) {
        this.likeList = likeList;
    }

}
