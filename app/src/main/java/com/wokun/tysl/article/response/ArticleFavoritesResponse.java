package com.wokun.tysl.article.response;


import com.wokun.tysl.article.bean.ArticleFavoritesBean;

import java.io.Serializable;
import java.util.List;

public class ArticleFavoritesResponse implements Serializable {

    private List<ArticleFavoritesBean> likeList;

    public List<ArticleFavoritesBean> getLikeList() {
        return likeList;
    }

    public void setLikeList(List<ArticleFavoritesBean> likeList) {
        this.likeList = likeList;
    }

}
