package com.wokun.tysl.goods.controller;

import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.common.utils.AppManager;
import com.shantoo.widget.imageview.SelectorImageView;
import com.shantoo.widget.toast.RxToast;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.store.ui.StoreIndexActivity;
import com.wokun.tysl.login.ui.LoginActivity;
import com.wokun.tysl.model.response.BaseResponse;

public class GoodsDetailMgr {

    private GoodsDetailMgr(){

    }

    private static class GoodsDetailMgrHolder{
        private static GoodsDetailMgr instance = new GoodsDetailMgr();
    }

    public static GoodsDetailMgr getInstance(){
        return GoodsDetailMgrHolder.instance;
    }

    /**
     * 收藏商品
     * */
    public void actionFavorites(BaseBindingActivity activity, int type, int source_id, final SelectorImageView sivCollect, final TextView favoritesTV){
        if(!TyslApp.getInstance().isLogin()){
            activity.startActivity(LoginActivity.class);
            return;
        }
        if(sivCollect.isChecked()){//已收藏
            OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.FAVORITES_DELETE_URL)
                .tag(this)
                .params(Constants.TYPE, type)
                .params(Constants.SOURCE_ID, source_id)
                .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN,Constants.FAVORITES_DELETE_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body==null)return;
                        RxToast.showShort(body.getMsg());
                        if(body.isState()){
                            sivCollect.toggle(false);
                            favoritesTV.setText("收藏");
                        }
                    }
                });
        }else{//未收藏
            OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.FAVORITES_ADD_URL)
                .tag(this)
                .params(Constants.TYPE, type)
                .params(Constants.SOURCE_ID, source_id)
                .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN,Constants.FAVORITES_ADD_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body==null)return;
                        RxToast.showShort(body.getMsg());
                        if(body.isState()){
                            sivCollect.toggle(true);
                            favoritesTV.setText("已收藏");
                        }
                    }
                });
        }
    }

    /**
     * 跳转购物车
     * */
    public void actionGoShopCart(){

        AppManager.getAppManager().finishAllActivity();
        TyslApp.getInstance().setRefreshShopCart(true);
        TyslApp.getInstance().setTabPosition(Constants.TAB_POSITION_SHOP_CART);

    }

    /**
     * 进入店铺
     * */
    public void actionGoStore(Activity activity,String storeId){
        Intent intent = new Intent();
        intent.putExtra(Constants.STORE_ID, storeId);
        intent.setClass(activity, StoreIndexActivity.class);
        activity.startActivity(intent);
        AppManager.getAppManager().finishActivity(activity);
    }





}