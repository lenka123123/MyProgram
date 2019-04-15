package com.wokun.tysl.callback;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.classic.common.MultipleStatusView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.exception.HttpException;
import com.lzy.okgo.exception.StorageException;
import com.lzy.okgo.request.base.Request;
import com.shantoo.common.utils.AppManager;
import com.shantoo.widget.toast.RxToast;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.home.bean.DeviceBean;
import com.wokun.tysl.login.LoginMgr;
import com.wokun.tysl.login.ui.LoginActivity;
import com.wokun.tysl.model.bean.User;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.utils.SignUtil;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.UUID;

import okhttp3.Response;

import static android.content.Context.ACTIVITY_SERVICE;

public abstract class JsonCallback<T> extends AbsCallback<T> {

    private String TAG = JsonCallback.class.getSimpleName();

    private Type type;
    private String url;
    private Class<T> clazz;
    private MultipleStatusView mMultipleStatusView;
    private boolean isInitCache = false; //是否使用缓存初始化数据
    private int state = Constants.NO_TOKEN; // 是否携带Token信息，默认为不带Token信息


    public JsonCallback() {
    }

    public JsonCallback(MultipleStatusView mMultipleStatusView) {
        this.mMultipleStatusView = mMultipleStatusView;
    }

    public JsonCallback(int state,String url) {
        this.state = state;
        this.url = url;
    }

    public JsonCallback(int state,String url,MultipleStatusView mMultipleStatusView) {
        this.state = state;
        this.url = url;
        this.mMultipleStatusView = mMultipleStatusView;
    }

    public JsonCallback(int state,String url,Type type) {
        this.url = url;
        this.type = type;
    }

    public JsonCallback(int state,String url,Class<T> clazz) {
        this.url = url;
        this.clazz = clazz;
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        super.onStart(request);
        // 主要用于在所有请求之前添加公共的请求头或请求参数
        // 例如登录授权的 token
        // 使用的设备信息
        // 可以随意添加,也可以什么都不传
        // 还可以在这里对所有的参数进行加密，均在这里实现

        //有三种情况，一种是不带Token信息的
        // 一种是必须带Token信息的
        // 还有一种是检测是否已经登录，如果登录了就携带Token,如果没有登录，则不带Token
        if(Constants.NO_TOKEN == state){

            //Logger.e(TAG,"不带Token信息");

        }else if(Constants.WITH_TOKEN == state){

            //Logger.e(TAG,"必须带Token信息");
            requestWithToken(request);

        }else if(Constants.LOGIN_WITH_TOKEN == state){
            //Logger.e(TAG,"检测是否已经登录，如果登录了就携带Token,如果没有登录，则不带Token");
            if(TyslApp.getInstance().isLogin()){//登录过
                requestWithToken(request);
                //Logger.e(TAG,"已经登录，携带Token");
            }
        }
    }

   /* @Override
    public void onCacheSuccess(com.lzy.okgo.model.Response<T> response) {
        super.onCacheSuccess(response);
        //一般来说,只需要第一次初始化界面的时候需要使用缓存刷新界面,以后不需要,所以用一个变量标识
        if (!isInitCache) {
            //一般来说,缓存回调成功和网络回调成功做的事情是一样的,所以这里直接回调onSuccess
            onSuccess(response);
            isInitCache = true;
        }
    }*/

    /**
     * 携带Token请求
     * */
    private void requestWithToken(Request<T, ? extends Request> request){
        if(TextUtils.isEmpty(url)){return;}
        User user = TyslApp.getInstance().getUser();
        //Log.e(TAG,user+"");
        if(user == null)return;
        String userId = user.getUserId();
        String token = user.getAccessToken();
        long time_stamp = System.currentTimeMillis();
        String sign = SignUtil.getSign(Constants.BASE_URL + url, userId, token, time_stamp);
        //Logger.e(TAG,Constants.BASE_URL + url);
        request.params(Constants.USER_ID, userId)
               .params(Constants.TIME_STAMP, time_stamp)
               .params(Constants.SIGN, sign);
    }

    /**
     * 该方法是子线程处理，不能做ui相关的工作
     * 主要作用是解析网络返回的 response 对象,生产onSuccess回调中需要的数据对象
     * 这里的解析工作不同的业务逻辑基本都不一样,所以需要自己实现,以下给出的时模板代码,实际使用根据需要修改
     */
    @Override
    public T convertResponse(Response response) throws Throwable {
     //   loadDevice();//反正就是在每个网络请求之前调用这个接口
        if (type == null) {
            if (clazz == null) {
                Type genType = getClass().getGenericSuperclass();
                type = ((ParameterizedType) genType).getActualTypeArguments()[0];
            } else {
                JsonConvert<T> convert = new JsonConvert<>(clazz);
                return convert.convertResponse(response);
            }
        }


        JsonConvert<T> convert = new JsonConvert<>(type);



        return convert.convertResponse(response,url);
    }
    private void loadDevice() {
        User user = TyslApp.getInstance().getUser();
        //Log.e(TAG,user+"");
        if(user == null)return;
        String userId = user.getUserId();
        OkGo.<BaseResponse<DeviceBean>>post(Constants.BASE_URL + Constants.OTHER_IS_SAME_DEVICE_URL)
                .params("user_id",userId)
                .params("uuid", UUID.randomUUID().toString())
                .execute(new JsonCallback<BaseResponse<DeviceBean>>() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<BaseResponse<DeviceBean>> response) {
                        BaseResponse body = response.body();
                        Log.e("登录的设备","登录的设备");
                        if(body == null)return;
                        if (body.isState()) {
                            Log.e("登录的设备1",body+"");
                            DeviceBean data = (DeviceBean) body.getData();
                            if (data.getIs_same().equals("0")){
                               //跳转登录界面
                                Log.e("登录的设备2",data.getIs_same()+"");
                          //      LoginMgr.getInstance().logout(((Activity) TyslApp.getContext()));
                                Activity activity = AppManager.getAppManager().currentActivity();
                                Intent intent = new Intent(activity, LoginActivity.class);
                                activity.startActivity(intent);
                                return;
                            }
                        } else {
                            RxToast.showShort(body.getMsg());
                        }
                    }
                });
    }

    @Override
    public void onError(com.lzy.okgo.model.Response<T> response) {
        super.onError(response);

        Throwable exception = response.getException();
        if(exception!=null) exception.printStackTrace();

        if(exception instanceof UnknownHostException || exception instanceof ConnectException){
            //RxToast.showShort("网络连接失败，请连接网络后重试！");
            if(mMultipleStatusView!=null)
            mMultipleStatusView.showNoNetwork();
        }else if(exception instanceof SocketTimeoutException){
            //RxToast.showShort("网络连接超时！");
            if(mMultipleStatusView!=null)
            mMultipleStatusView.showError();
        }else if(exception instanceof HttpException){
            //RxToast.showShort("服务器出错！");
            if(mMultipleStatusView!=null)
            mMultipleStatusView.showError();
        }else if(exception instanceof StorageException){
            RxToast.showShort("SD卡不存在或者没有权限！");
        }else if(exception instanceof IllegalStateException){
            RxToast.showShort(exception.getMessage());
          if(exception.getMessage().equals("非法请求！")){
                Log.e("feifa进来了","feifa进来了");
                OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.LOGOUT_URL)//
                        .tag(this)
                        .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN,Constants.LOGOUT_URL) {
                            @Override
                            public void onSuccess(com.lzy.okgo.model.Response<BaseResponse<Object>> response) {
                                BaseResponse body = response.body();
                                if(body == null)return;
                                if (body.isState()) {
                                    TyslApp.getInstance().clear();
                                    AppManager.getAppManager().currentActivity().setResult(Constants.SETTING_RESULT_CODE);
                                    AppManager.getAppManager().currentActivity().finish();
                                }
                                //     Toast.makeText(activity, body.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        }
    }
}
