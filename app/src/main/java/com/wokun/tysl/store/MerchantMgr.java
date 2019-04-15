package com.wokun.tysl.store;

import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toast.RxToast;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.model.response.BusinessShowResponse;

public class MerchantMgr {

    private MerchantMgr(){
    }

    private static class StoreMgrHolder{
        private static MerchantMgr instance = new MerchantMgr();
    }

    public static MerchantMgr getInstance(){
        return StoreMgrHolder.instance;
    }

    /**
     * 商家入驻申请
     * @param defaultView 默认显示的页面
     * @param successView 申请成功后显示的页面
     * @param name 联系人姓名
     * @param mobile 联系人电话
     * @param email 联系人邮箱
     * */
    public void doJoinBusiness(final View defaultView, final View successView, String name, String mobile, String email){
        if(TextUtils.isEmpty(name)){
            Toast.makeText(TyslApp.getContext(), "请填写联系人姓名", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(mobile)){
            Toast.makeText(TyslApp.getContext(), "请填写联系人电话", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(email)){
            Toast.makeText(TyslApp.getContext(), "请填写联系人邮箱", Toast.LENGTH_SHORT).show();
            return;
        }
        OkGo.<BaseResponse<BusinessShowResponse>>post(Constants.BASE_URL + Constants.JOIN_BUSINESS_URL)
                .tag(this)
                .params("name", name)
                .params("mobile", mobile)
                .params("email", email)
                .execute(new JsonCallback<BaseResponse<BusinessShowResponse>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<BusinessShowResponse>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;

                        if(body.getMsg().contains("成功")){

                            defaultView.setVisibility(View.GONE);
                            successView.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }




    public void doJoinBusiness2( String name, String mobile, String email){
        if(TextUtils.isEmpty(name)){
            Toast.makeText(TyslApp.getContext(), "请填写联系人姓名", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(mobile)){
            Toast.makeText(TyslApp.getContext(), "请填写联系人电话", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(email)){
            Toast.makeText(TyslApp.getContext(), "请填写联系人邮箱", Toast.LENGTH_SHORT).show();
            return;
        }
        OkGo.<BaseResponse<BusinessShowResponse>>post(Constants.BASE_URL + Constants.JOIN_BUSINESS_URL)
                .tag(this)
                .params("name", name)
                .params("mobile", mobile)
                .params("email", email)
                .execute(new JsonCallback<BaseResponse<BusinessShowResponse>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<BusinessShowResponse>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;

                        if(body.getMsg().contains("成功")){
                            RxToast.showShort(body.getMsg());
                        }
                    }
                });
    }







}