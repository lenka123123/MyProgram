package com.wokun.tysl.model.response;

import java.util.List;

public class FavoritesResponse<T> {

    private List<T> likeList;

    public List<T> getLikeList() {
        return likeList;
    }

    public void setLikeList(List<T> likeList) {
        this.likeList = likeList;
    }
}
