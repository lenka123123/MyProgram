package com.wokun.tysl.article.response;


import com.wokun.tysl.article.bean.ArticleBean;

import java.io.Serializable;
import java.util.List;

public class ArticleListResponse implements Serializable {

    private List<ArticleBean> articleList;

    public List<ArticleBean> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<ArticleBean> articleList) {
        this.articleList = articleList;
    }
}
