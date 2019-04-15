package com.wokun.tysl.dietician.response;


import com.wokun.tysl.dietician.bean.DieticianFavoritesBean;

import java.util.List;

public class DietitianFavoritesResponse {

    private List<DieticianFavoritesBean> likeList;

    public List<DieticianFavoritesBean> getLikeList() {
        return likeList;
    }

    public void setLikeList(List<DieticianFavoritesBean> likeList) {
        this.likeList = likeList;
    }
}
