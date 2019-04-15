package com.wokun.tysl.ucenter.controler;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.common.utils.MD5Util;
import com.shantoo.widget.toast.RxToast;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.dietician.bean.DieticianInfoBean;
import com.wokun.tysl.login.LoginMgr;
import com.wokun.tysl.model.bean.QiandaoResponse;
import com.wokun.tysl.model.bean.SingleParam;
import com.wokun.tysl.model.bean.UserInfo;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.ucenter.ui.NewEditMobileActivity3;

public class UcenterMgr {

    private UcenterMgr(){

    }

    private static class EditUserInfoMgrHolder{
        private static UcenterMgr instance = new UcenterMgr();
    }

    public static UcenterMgr getInstance(){
        return EditUserInfoMgrHolder.instance;
    }

    /**
     * 修改密码
     * @param oldPwd 旧密码
     * @param newPwd 新密码
     * @param reNewPwd 确认密码
     * */
    public void editPwd(String oldPwd,String newPwd,String reNewPwd){
        if (TextUtils.isEmpty(oldPwd)) {
            Toast.makeText(TyslApp.getContext(), "旧密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(newPwd)) {
            Toast.makeText(TyslApp.getContext(), "新密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(reNewPwd)) {
            Toast.makeText(TyslApp.getContext(), "确认密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (oldPwd.equals(newPwd)) {
            Toast.makeText(TyslApp.getContext(), "旧密码和新密码不能一致", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!reNewPwd.equals(newPwd)) {
            Toast.makeText(TyslApp.getContext(), "新密码和确认密码不一致", Toast.LENGTH_SHORT).show();
            return;
        }

        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.UCENTER_PASSWORD_CHANGE_URL)
                .tag(this)
                .params(Constants.OLD_PASSWORD, MD5Util.encrypt(oldPwd))
                .params(Constants.NEW_PASSWORD, MD5Util.encrypt(newPwd))
                .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN,Constants.UCENTER_PASSWORD_CHANGE_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;

                        RxToast.showShort(body.getMsg());
                    }
                });
    }

    /**
     * 修改绑定手机号
     * @param newMobile 新手机号码
     * @param
     * @param code 验证码
     * */
    public void editBindMobile( String newMobile, String code) {
        if(TextUtils.isEmpty(newMobile)){
            RxToast.showShort(Constants.NULL_MOBILE_MESSAGE);
            return;
        }
       /* if(TextUtils.isEmpty(password)){
            RxToast.showShort(Constants.NULL_PWD_MESSAGE);
            return;
        }*/
        if(TextUtils.isEmpty(code)){
            RxToast.showShort(Constants.NULL_CODE_MESSAGE);
            return;
        }
        OkGo.<BaseResponse<SingleParam>>post(Constants.BASE_URL + Constants.UCENTER_CHANGE_MOBILE_URL)
                .tag(this)
           //     .params(Constants.PASSWORD, MD5Util.encrypt(password))
                .params(Constants.NEW_MOBILE, newMobile)
                .params(Constants.CODE, code)
                .execute(new JsonCallback<BaseResponse<SingleParam>>(Constants.WITH_TOKEN,Constants.UCENTER_CHANGE_MOBILE_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<SingleParam>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if(body.isState()){
                            UserInfo userInfo =  TyslApp.getInstance().getUserInfo();
                            DieticianInfoBean dieticianInfo =  TyslApp.getInstance().getDieticianInfo();

                            SingleParam data = (SingleParam) body.getData();
                            if(userInfo!=null){
                                userInfo.setMobile(data.getMobile());
                                TyslApp.getInstance().setUserInfo(userInfo);
                            }
                            if(dieticianInfo!=null){
                                dieticianInfo.setMobile(data.getMobile());
                                TyslApp.getInstance().setDieticianInfo(dieticianInfo);
                            }

                        }
                        RxToast.showShort(body.getMsg());

                    }
                });
    }


    /**
     *签到
     *
     * */


    public void   getQianDao( ) {

        OkGo.<BaseResponse<QiandaoResponse>>post(Constants.BASE_URL + Constants.INTEGRAL_SIGN_IN_URL)
                .tag(this)
                .execute(new JsonCallback<BaseResponse<QiandaoResponse>>(Constants.WITH_TOKEN,Constants.INTEGRAL_SIGN_IN_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<QiandaoResponse>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if(body.isState()){


                        }
                        RxToast.showShort(body.getMsg());

                    }
                });
    }











    /**
     * 修改个人简介
     * */
    public void alterProfile(String profile) {
        DieticianInfoBean dieticianInfo = TyslApp.getInstance().getDieticianInfo();
        if (dieticianInfo == null) {return;}

        if (TextUtils.isEmpty(profile)) {
            Toast.makeText(TyslApp.getContext(), "请提交个人信息后再修改！", Toast.LENGTH_SHORT).show();
            return;
        }

        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.UCENTER_ALTER_PROFILE_URL)
                .tag(this)
                .params(Constants.DIETITIAN_ID, dieticianInfo.getDietitianId())
                .params(Constants.PROFILE, profile)
                .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN,Constants.UCENTER_ALTER_PROFILE_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;

                        RxToast.showShort(body.getMsg());
                    }
                });
    }

    /**
     * 提交意见反馈
     * @param content 留言
     * @param mobile 电话号码
     * */
    public void feedBack(String content, String mobile, final EditText feedBackET, final EditText mobileEt) {
        if (TextUtils.isEmpty(content)) {
            Toast.makeText(TyslApp.getContext(), "请留言后再提交！", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(mobile)) {
            Toast.makeText(TyslApp.getContext(), "电话号码为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.UCENTER_ADD_FEEDBACK_URL).tag(this)
                .params(Constants.MOBILE, mobile)
                .params(Constants.CONTENT, content)
                .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN,Constants.UCENTER_ADD_FEEDBACK_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;

                        if (body.isState()) {
                            feedBackET.setText("");
                            mobileEt.setText("");
                        }
                        RxToast.showShort(body.getMsg());
                    }
                });
    }
}
