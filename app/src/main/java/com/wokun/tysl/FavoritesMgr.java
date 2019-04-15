package com.wokun.tysl;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.imageview.SelectorImageView;
import com.shantoo.widget.toast.RxToast;
import com.wokun.tysl.base.SimpleRefreshAndLoadMoreActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.login.ui.LoginActivity;
import com.wokun.tysl.model.response.BaseResponse;

public class FavoritesMgr {

    private FavoritesMgr(){

    }

    private static class FavoritesMgrHolder{
        private static FavoritesMgr instance = new FavoritesMgr();
    }

    public static FavoritesMgr getInstance(){
        return FavoritesMgr.FavoritesMgrHolder.instance;
    }

    public void actionFavorites(Activity activity,int type,String source_id,final SelectorImageView sivCollect){
        if(!TyslApp.getInstance().isLogin()){
            Intent intent = new Intent();
            intent.setClass(activity,LoginActivity.class);
            activity.startActivity(intent);
            return;
        }
        if(sivCollect!=null&&sivCollect.isChecked()){//已收藏
            OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.FAVORITES_DELETE_URL)
                    .tag(this)
                    .params(Constants.TYPE, type)
                    .params(Constants.SOURCE_ID, source_id)
                    .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN, Constants.FAVORITES_DELETE_URL) {
                        @Override
                        public void onSuccess(Response<BaseResponse<Object>> response) {
                            BaseResponse body = response.body();
                            if(body==null)return;
                            RxToast.showShort(body.getMsg());
                            if(body.isState()){
                                sivCollect.toggle(false);
                            }
                        }
                    });
        }else{//未收藏
            OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.FAVORITES_ADD_URL)
                    .tag(this)
                    .params(Constants.TYPE, type)
                    .params(Constants.SOURCE_ID, source_id)
                    .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN, Constants.FAVORITES_ADD_URL) {
                        @Override
                        public void onSuccess(Response<BaseResponse<Object>> response) {
                            BaseResponse body = response.body();
                            if(body==null)return;
                            RxToast.showShort(body.getMsg());
                            if(body.isState()&&sivCollect!=null){
                                sivCollect.toggle(true);
                            }
                        }
                    });
        }
    }

    //添加收藏
    public void addFavorites(final int type, String source_id, final SelectorImageView sivCollect){
        if(!TyslApp.getInstance().isLogin()){
            Toast.makeText(TyslApp.getContext(), "亲，您还未登录", Toast.LENGTH_SHORT).show();
            return;
        }
        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.FAVORITES_ADD_URL)
                .tag(this)
                .params(Constants.TYPE, type)
                .params(Constants.SOURCE_ID, source_id)
                .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN, Constants.FAVORITES_ADD_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body==null)return;
                        RxToast.showShort(body.getMsg());
                        if(body.isState()&&sivCollect!=null){
                            sivCollect.toggle(true);
                        }
                    }
                });
    }

    /**
     * 收藏列表 取消收藏
     * @param activity 环境
     * @param type 收藏类型
     * @param source_id 收藏id
     * */
    public void deleteFavorites(final SimpleRefreshAndLoadMoreActivity activity, int type, String source_id){
        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.FAVORITES_DELETE_URL)
                .tag(this)
                .params(Constants.TYPE, type)
                .params(Constants.SOURCE_ID, source_id)
                .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN, Constants.FAVORITES_DELETE_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body==null)return;
                        RxToast.showShort(body.getMsg());
                        if(body.isState()){
                            activity.onRefresh();
                        }
                    }
                });
    }
}