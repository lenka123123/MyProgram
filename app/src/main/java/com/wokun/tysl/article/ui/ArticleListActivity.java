package com.wokun.tysl.article.ui;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.article.adapter.ArticleListAdapter;
import com.wokun.tysl.article.bean.ArticleBean;
import com.wokun.tysl.article.controller.ArticleMgr;
import com.wokun.tysl.article.response.ArticleListResponse;
import com.wokun.tysl.base.SimpleRefreshAndLoadMoreActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.model.response.BaseResponse;

//文章列表
public class ArticleListActivity extends SimpleRefreshAndLoadMoreActivity<ArticleBean> {

    private int type;

    @Override
    public WidgetBar createToolBar() {
        type = getIntent().getIntExtra(Constants.TYPE, 1);
        String title = "";
        if (1 == type) {title = "营养知识";}
        else if (2 == type) {title = "健康365";}
        else if (3 == type) {title = "大众营养";}
        else if (4 == type) {title = "病人营养";}
        else if (5 == type) {title = "营养科普";}
        else if (6 == type) {title = "膳食指南";}
        else if (7 == type) {title = "营养师专栏";}
        return mWidgetBar.setWidgetBarTitle(title);
    }

    @Override
    public BaseQuickAdapter<ArticleBean, BaseViewHolder> initAdapter() {
        return new ArticleListAdapter(R.layout.item_article_list, null);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ArticleMgr.getInstance().jumpToArticleDetailActivity(this,mDataList.get(position));
    }

    @Override
    public Request initRequest() {
        return OkGo.<BaseResponse<ArticleListResponse>>post(Constants.BASE_URL + Constants.ARTICLE_LIST_URL)
                .tag(this).params(Constants.TYPE, type);
    }

    @Override
    public void loadData(final boolean isRefresh) {
        mRequest.execute(new JsonCallback<BaseResponse<ArticleListResponse>>() {
            @Override
            public void onSuccess(Response<BaseResponse<ArticleListResponse>> response) {
                BaseResponse body = response.body();
                if(body == null)return;

                if(body.isState()){
                    ArticleListResponse data = (ArticleListResponse) body.getData();
                    if(data == null){return;}
                    setData(isRefresh,data.getArticleList());
                }
            }
        });
    }
}
