package com.wokun.tysl.article.ui;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.article.adapter.ArticleFavoritesAdapter;
import com.wokun.tysl.article.bean.ArticleFavoritesBean;
import com.wokun.tysl.article.controller.ArticleMgr;
import com.wokun.tysl.article.response.ArticleFavoritesResponse;
import com.wokun.tysl.base.SimpleRefreshAndLoadMoreActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.model.response.BaseResponse;

import java.util.ArrayList;

import butterknife.BindString;

//文章收藏页面
public class ArticleFavoritesActivity extends SimpleRefreshAndLoadMoreActivity<ArticleFavoritesBean> {

  //  @BindString(R.string.tysl_article_favorites)String title;

    @Override
    public WidgetBar createToolBar() {
        mWidgetBar.setVisibility(View.GONE);

        return mWidgetBar;
    }

    @Override
    public BaseQuickAdapter<ArticleFavoritesBean, BaseViewHolder> initAdapter() {
        return new ArticleFavoritesAdapter(R.layout.item_article_favorites, new ArrayList<ArticleFavoritesBean>());
    }

    @Override
    public Request initRequest() {
        return OkGo.<BaseResponse<ArticleFavoritesResponse>>post(Constants.BASE_URL + Constants.FAVORITES_LIKE_LIST_URL)
                .tag(this).params(Constants.TYPE, Constants.FAVORITES_TYPE_ARTICLE);
    }

    @Override
    public void loadData(final boolean isRefresh) {
        mRequest.execute(new JsonCallback<BaseResponse<ArticleFavoritesResponse>>(Constants.WITH_TOKEN,Constants.FAVORITES_LIKE_LIST_URL) {
            @Override
            public void onSuccess(Response<BaseResponse<ArticleFavoritesResponse>> response) {
                BaseResponse body = response.body();
                if(body == null)return;
                if(body.isState()){
                    ArticleFavoritesResponse data = (ArticleFavoritesResponse) body.getData();
                    if(data==null){return;}
                    setData(isRefresh,data.getLikeList());
                }
            }
        });
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ArticleMgr.getInstance().jumpToArticleDetailActivity(this,mDataList.get(position));
    }
}