package com.wokun.tysl.dietician.response;


import com.wokun.tysl.article.bean.ArticleBean;

import java.util.List;

public class DieticianArticleListResponse {

    private List<ArticleBean> article_list;

    public List<ArticleBean> getArticleList() {
        return article_list;
    }

    public void setArticleList(List<ArticleBean> articleList) {
        this.article_list = articleList;
    }
}
