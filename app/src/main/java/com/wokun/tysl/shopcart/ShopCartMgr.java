package com.wokun.tysl.shopcart;


import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toast.RxToast;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.login.ui.LoginActivity;
import com.wokun.tysl.model.response.BaseResponse;

public class ShopCartMgr {

    private ShopCartMgr(){
    }

    private static class ShopCartMgrHolder{
        private static ShopCartMgr instance = new ShopCartMgr();
    }

    public static ShopCartMgr getInstance(){
        return ShopCartMgrHolder.instance;
    }


    private boolean canJoinShopCart = true;

    public boolean isCanJoinShopCart() {
        return canJoinShopCart;
    }

    //商品加入购物车
    public void cartAddGoods(Activity activity,int goods_id, String invite_code) {
        if(!TyslApp.getInstance().isLogin()){
            Intent intent = new Intent();
            intent.setClass(activity,LoginActivity.class);
            activity.startActivity(intent);
            return;
        }

        invite_code = TextUtils.isEmpty(invite_code) ? "" : invite_code;
        canJoinShopCart = false;
        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.CART_ADD_GOODS_URL)
                .tag(activity)
                .params(Constants.GOODS_ID, goods_id)
                .params("invite_code", invite_code)
                .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN,Constants.CART_ADD_GOODS_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;

                        if(body.isState()){
                            canJoinShopCart = true;
                        }
                        RxToast.showShort(body.getMsg());
                    }
                });
    }

    //删除商品
    public void cartDeleteGoods(Activity activity,String cartId) {
        if(TextUtils.isEmpty(cartId)) return;
        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.CART_DELETE_GOODS_URL)
                .tag(activity)
                .params(Constants.CART_ID_STR, cartId)
                .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN,Constants.CART_DELETE_GOODS_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;

                        RxToast.showShort(body.getMsg());
                    }
                });
    }
}
