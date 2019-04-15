package com.wokun.tysl.login;


import android.app.Activity;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.common.utils.MD5Util;
import com.shantoo.widget.imageview.SelectorImageView;
import com.shantoo.widget.toast.RxToast;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.model.response.BaseResponse;

public class LoginMgr {

    private LoginMgr(){

    }


    private static class LoginMgrHolder{
        private static LoginMgr instance = new LoginMgr();
    }

    public static LoginMgr getInstance(){
        return LoginMgrHolder.instance;
    }

    /**
     * 注册账号
     * @param mobile 手机号码
     * @param password 密码
     * @param code 验证码
     * */
    public void register(String mobile, String password, String code,String inviteCode) {
        if(TextUtils.isEmpty(mobile)){
            RxToast.showShort(Constants.NULL_MOBILE_MESSAGE);
            return;
        }
        if(TextUtils.isEmpty(password)){
            RxToast.showShort(Constants.NULL_PWD_MESSAGE);
            return;
        }
        if(TextUtils.isEmpty(code)){
            RxToast.showShort(Constants.NULL_CODE_MESSAGE);
            return;
        }
        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.REGISTER_URL)
                .tag(this)
                .params(Constants.CODE, code)
                .params(Constants.MOBILE, mobile)
                .params(Constants.INVITE_CODE, inviteCode)
                .params(Constants.PASSWORD, MD5Util.encrypt(password))
                .params(Constants.LOGIN_TYPE, Constants.ANDROID)
                .execute(new JsonCallback<BaseResponse<Object>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        RxToast.showShort(body.getMsg());
                    }
                });
    }

    /**
     * 获取验证码
     * @param mobile 手机号码
     * */
    public void sendVerifyCode(String mobile) {
        if(TextUtils.isEmpty(mobile)){
            RxToast.showShort(Constants.NULL_MOBILE_MESSAGE);
            return;
        }
        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.GET_CODE_URL)
                .tag(this)
                .params(Constants.MOBILE, mobile)
                .execute(new JsonCallback<BaseResponse<Object>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        RxToast.showShort(body.getMsg());
                    }
                });
    }

    /**
     * 忘记密码
     * @param mobile 手机号码
     * @param newPwd 新密码
     * @param code 验证码
     * */
    public void alterPassword(final Activity activity, String mobile, String newPwd, String code) {
        if(TextUtils.isEmpty(mobile)){
            RxToast.showShort(Constants.NULL_MOBILE_MESSAGE);
            return;
        }
        if(TextUtils.isEmpty(newPwd)){
            RxToast.showShort(Constants.NULL_PWD_MESSAGE);
            return;
        }
        if(TextUtils.isEmpty(code)){
            RxToast.showShort(Constants.NULL_CODE_MESSAGE);
            return;
        }
        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.ALTER_PASSWORD_URL)//
                .tag(this)
                .params(Constants.MOBILE, mobile)
                .params(Constants.PASSWORD, MD5Util.encrypt(newPwd))
                .params(Constants.CODE, code)
                .execute(new JsonCallback<BaseResponse<Object>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if(body.isState()){
                            activity.finish();
                        }
                        RxToast.showShort(body.getMsg());
                    }
                });
    }

    /**
     * 是否显示密码
     * @param etPassword EditText
     * @param sivIsShowPwd SelectorImageView
     * */
    public void isShowPassword(EditText etPassword, SelectorImageView sivIsShowPwd){
        if (sivIsShowPwd.isChecked()) {
            sivIsShowPwd.toggle(false);
            //设置EditText文本为隐藏的
            etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        } else {
            sivIsShowPwd.toggle(true);
            //设置EditText文本为可见的
            etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
        etPassword.postInvalidate();
        //切换后将EditText光标置于末尾
        CharSequence charSequence = etPassword.getText();
        if (charSequence instanceof Spannable) {
            Spannable spanText = (Spannable) charSequence;
            Selection.setSelection(spanText, charSequence.length());
        }
    }

    /**
     * 退出登录
     * @param activity Activity
     * */
    public void logout(final Activity activity) {
        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.LOGOUT_URL)//
                .tag(activity)
                .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN,Constants.LOGOUT_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if (body.isState()) {
                            TyslApp.getInstance().clear();
                            activity.setResult(Constants.SETTING_RESULT_CODE);
                            activity.finish();
                        }
                   //     Toast.makeText(activity, body.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                });
    }





}
