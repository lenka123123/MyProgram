package com.wokun.tysl.article.ui;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.article.adapter.XueTangArticleListAdapter;
import com.wokun.tysl.article.bean.ArticleBean;
import com.wokun.tysl.article.controller.ArticleMgr;
import com.wokun.tysl.base.SimpleRefreshAndLoadMoreActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.dietician.response.DieticianArticleListResponse;
import com.wokun.tysl.model.response.BaseResponse;

//营养师文章列表
public class DieticianArticleListActivity extends SimpleRefreshAndLoadMoreActivity<ArticleBean> {

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle("发布的文章");
    }

    @Override
    public BaseQuickAdapter<ArticleBean, BaseViewHolder> initAdapter() {
        return new XueTangArticleListAdapter(R.layout.item_article_list_xuetang, null);
    }

    @Override
    public Request initRequest() {
        int dietitianUserId = getIntent().getIntExtra(Constants.DIETITIAN_USER_ID,0);
        return OkGo.<BaseResponse<DieticianArticleListResponse>>post(Constants.BASE_URL + Constants.ARTICLE_DIETITIAN_RELEASE_URL)
                .tag(this).params(Constants.DIETITIAN_USER_ID, dietitianUserId);
    }

    @Override
    public void loadData(final boolean isRefresh) {
        mRequest.execute(new JsonCallback<BaseResponse<DieticianArticleListResponse>>() {
            @Override
            public void onSuccess(Response<BaseResponse<DieticianArticleListResponse>> response) {
                BaseResponse body = response.body();
                if(body == null)return;

                if(body.isState()){
                    DieticianArticleListResponse data = (DieticianArticleListResponse) body.getData();
                    if(data == null){return;}
                    setData(isRefresh,data.getArticleList());
                }
            }
        });
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ArticleMgr.getInstance().jumpToArticleDetailActivity(this,mDataList.get(position));
    }
}
