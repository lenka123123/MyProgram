package com.wokun.tysl.article.controller;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.article.bean.ArticleBean;
import com.wokun.tysl.article.bean.ArticleFavoritesBean;
import com.wokun.tysl.article.ui.ArticleDetailActivity;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.login.ui.LoginActivity;
import com.wokun.tysl.model.response.BaseResponse;

public class ArticleMgr {

    private BaseBindingActivity mContext;
    private WidgetBar mWidgetBar;

    private ArticleMgr(){

    }

    private static class ArticleMgrHolder{
        private static ArticleMgr instance = new ArticleMgr();
    }

    public static ArticleMgr getInstance(){
        return ArticleMgrHolder.instance;
    }

    /**
     * 文章收藏
     * */
    public void articleFavorites(BaseBindingActivity activity, boolean isFavorites, String sourceId, WidgetBar widgetBar){

        this.mContext = activity;
        this.mWidgetBar = widgetBar;

        if (isFavorites) {//如果已收藏则取消收藏
            deleteFavorites(Constants.FAVORITES_TYPE_ARTICLE, sourceId);
        } else {//如果未收藏则点击收藏
            addFavorites(Constants.FAVORITES_TYPE_ARTICLE, sourceId);
        }
    }

    //添加收藏
    private void addFavorites(int type, String sourceId) {
        if (!TyslApp.getInstance().isLogin()) {
            mContext.startActivity(LoginActivity.class);
            return;
        }
        OkGo.<BaseResponse<Void>>post(Constants.BASE_URL + Constants.FAVORITES_ADD_URL)//
                .tag(this)
                .params(Constants.TYPE, type)
                .params(Constants.SOURCE_ID, sourceId)
                .execute(new JsonCallback<BaseResponse<Void>>(Constants.WITH_TOKEN,Constants.FAVORITES_ADD_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<Void>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        Toast.makeText(TyslApp.getContext(), body.getMsg(), Toast.LENGTH_SHORT).show();
                        if(body.isState()){
                            mArticleFavoritesLister.updateHasFavorites(true);
                            mWidgetBar.setMenu(null,mContext.getResources().getDrawable(R.drawable.ic_faved));
                        }
                    }
                });
    }

    //取消收藏
    private void deleteFavorites(int type,String sourceId){
        if(!TyslApp.getInstance().isLogin()){
            mContext.startActivity(LoginActivity.class);
            return;
        }
        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.FAVORITES_DELETE_URL)//
                .tag(this)
                .params(Constants.TYPE, type)
                .params(Constants.SOURCE_ID, sourceId)
                .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN,Constants.FAVORITES_DELETE_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        Toast.makeText(TyslApp.getContext(), body.getMsg(), Toast.LENGTH_SHORT).show();
                        if(body.isState()){
                            mArticleFavoritesLister.updateHasFavorites(false);
                            mWidgetBar.setMenu(null,mContext.getResources().getDrawable(R.drawable.ic_fav));
                        }
                    }
                });
    }

    public void jumpToArticleDetailActivity(Activity activity,ArticleFavoritesBean data){
        if(data==null){return;}
        Intent intent = new Intent();
        intent.putExtra(Constants.SOURCE_ID, data.getArticleId());
        intent.setClass(activity, ArticleDetailActivity.class);
        activity.startActivity(intent);
    }

    public void jumpToArticleDetailActivity(Activity activity,ArticleBean data){
        if(data==null){return;}
        Intent intent = new Intent();
        intent.putExtra(Constants.SOURCE_ID, data.getArticleId());
        intent.setClass(activity, ArticleDetailActivity.class);
        activity.startActivity(intent);
    }

    private OnArticleFavoritesLister mArticleFavoritesLister;

    public interface OnArticleFavoritesLister {

        void updateHasFavorites(boolean isFavorites);
    }

    public void setArticleFavoritesLister(OnArticleFavoritesLister lister){
        this.mArticleFavoritesLister = lister;
    }
}
