package com.wokun.tysl.goods.response;


import com.wokun.tysl.model.bean.Store;

import java.util.List;

public class StoreFavoritesListResponse {

    private List<Store> likeList;

    public List<Store> getLikeList() {
        return likeList;
    }

    public void setLikeList(List<Store> likeList) {
        this.likeList = likeList;
    }

}
