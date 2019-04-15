package com.wokun.tysl.login.ui;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.annotations.NonNull;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.common.utils.Logger;
import com.shantoo.common.utils.MD5Util;
import com.shantoo.widget.imageview.SelectorImageView;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.shantoo.widget.utils.ManagerTool;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.home.bean.DeviceBean;
import com.wokun.tysl.login.ClearEditText;
import com.wokun.tysl.login.LoginMgr;
import com.wokun.tysl.model.bean.SingleParam;
import com.wokun.tysl.model.bean.User;
import com.wokun.tysl.model.bean.UserInfo;
import com.wokun.tysl.model.response.BaseResponse;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

//登陆
public class LoginActivity extends BaseBindingActivity {
    @BindString(R.string.tysl_login)String title;
    @BindView(R.id.et_mobile)
    ClearEditText etMobile;
    @BindView(R.id.et_password)
    EditText etPassword;

    @BindView(R.id.action_siv_show_pwd)
    SelectorImageView actionSivShowPwd;

    private boolean flag = false;

    @Override
    public int createView() {
        return R.layout.activity_login;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {
        initPermission();//针对6.0以上版本做权限适配
        etMobile.setHint("请输入手机号");
        etPassword.setHint("密码(6-20位字母，数字或者符号)");
        etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }

    /**
     * 登录
     * */
    @OnClick(R.id.action_login)
    public void actionLogin(View view){
        String mobile = etMobile.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        login(mobile, password);
    }

    /**
     * 注册
     * */
    @OnClick(R.id.action_register)
    public void actionRegister(View view){
        startActivity(RegisterActivity.class);
    }

    /**
     * 修改密码
     * */
    @OnClick(R.id.action_alert_pwd)
    public void actionAlertPwd(View view){
        startActivity(AlterPwdActivity.class);
    }

    /**
     * 显示密码
     * */
    @OnClick(R.id.action_siv_show_pwd)
    public void actionSivShowPwd(View view){
        Log.e("点击显示密码","点击显示密码");
        LoginMgr.getInstance().isShowPassword(etPassword,actionSivShowPwd);
    }

    /**
     * 登录
     * */
    public void login(String mobile, String password) {
        if(TextUtils.isEmpty(mobile)){
            RxToast.showShort(Constants.NULL_MOBILE_MESSAGE);
            return;
        }
        if(TextUtils.isEmpty(password)){
            RxToast.showShort(Constants.NULL_PWD_MESSAGE);
            return;
        }
        showLP();
        String ANDROID_ID = Settings.System.getString(getContentResolver(), Settings.System.ANDROID_ID);
    /*    TelephonyManager TelephonyMgr = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
        String szImei = TelephonyMgr.getDeviceId(); // Requires READ_PHONE_STATE*/
        OkGo.<BaseResponse<User>>post(Constants.BASE_URL + Constants.CHECK_LOGIN_URL)
            .tag(this)
            .params(Constants.MOBILE, mobile)
            .params(Constants.LOGIN_TYPE, Constants.ANDROID)
            .params(Constants.PASSWORD, MD5Util.encrypt(password))
            .params(Constants.MOBILE_CODE, ANDROID_ID)//
            //.isMultipart(true)         //强制使用 multipart/form-data 表单上传（只是演示，不需要的话不要设置。默认就是false）
            .execute(new JsonCallback<BaseResponse<User>>() {
                @Override
                public void onSuccess(Response<BaseResponse<User>> response) {
                    BaseResponse body = response.body();
                    if(body == null)return;
                    if (body.isState()) {
                        User user = (User) body.getData();
                        if(user == null){return;}
                        TyslApp.getInstance().setUser(user);
                        loadUserInfo();

                    }
                }
                @Override
                public void onError(Response response) {
                    dismissLP();
                    super.onError(response);
                    TyslApp.getInstance().setRefreshShopCart(false);
                }
            });
    }

    /**
     * 获取普通用户信息
     * */
    public void loadUserInfo() {
        OkGo.<BaseResponse<UserInfo>>post(Constants.BASE_URL + Constants.UCENTER_URL)
            .tag(this)
            .execute(new JsonCallback<BaseResponse<UserInfo>>(Constants.WITH_TOKEN,Constants.UCENTER_URL) {
                @Override
                public void onSuccess(Response<BaseResponse<UserInfo>> response) {
                    BaseResponse body = response.body();
                    if(body == null)return;
                    if (body.isState()) {
                        UserInfo userInfo = (UserInfo) body.getData();
                        if(userInfo == null){return;}
                        TyslApp.getInstance().setUserInfo(userInfo);
                        getIMToken();
                    }
                }
                @Override
                public void onError(Response response) {
                    dismissLP();
                    super.onError(response);
                    TyslApp.getInstance().setRefreshShopCart(false);
                }
            });
    }

    /**
     * 获取即时聊天Token
     * */
    private void getIMToken(){
        OkGo.<BaseResponse<SingleParam>>post(Constants.BASE_URL + Constants.IM_GET_TOKEN_URL)
            .tag(this)
            .execute(new JsonCallback<BaseResponse<SingleParam>>(Constants.WITH_TOKEN,Constants.IM_GET_TOKEN_URL) {
                @Override
                public void onSuccess(Response<BaseResponse<SingleParam>> response) {
                    BaseResponse body = response.body();
                    if(body == null)return;
                    if (body.isState()) {
                        SingleParam data = (SingleParam) body.getData();
                        if(data == null){return;}
                        TyslApp.getInstance().setImToken(data.getToken());
                        Log.e("融云获取token","融云获取token"+data.getToken());
                        connect(data.getToken());
                    }
                }
                @Override
                public void onError(Response response) {
                    dismissLP();
                    super.onError(response);
                    TyslApp.getInstance().setRefreshShopCart(false);
                }
            });
    }

    /**
     * <p>连接服务器，在整个应用程序全局，只需要调用一次，需在 {init(Context)} 之后调用。</p>
     * <p>如果调用此接口遇到连接失败，SDK 会自动启动重连机制进行最多10次重连，分别是1, 2, 4, 8, 16, 32, 64, 128, 256, 512秒后。
     * 在这之后如果仍没有连接成功，还会在当检测到设备网络状态变化时再次进行重连。</p>
     *
     * @param token    从服务端获取的用户身份令牌（Token）。
     * @return RongIM  客户端核心类的实例。
     */
    private void connect(final String token) {
        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext()))) {
            RongIM.connect(token, new RongIMClient.ConnectCallback() {

                /**
                 * Token 错误。可以从下面两点检查
                 * 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
                 * 2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
                 */
                @Override
                public void onTokenIncorrect() {
                    dismissLP();
                    RxToast.showShort(Constants.LOGIN_FAILURE_MESSAGE);
                    TyslApp.getInstance().setRefreshShopCart(false);
                }

                /**
                 * 连接融云成功
                 * @param userId 当前 token 对应的用户 id
                 */
                @Override
                public void onSuccess(String userId) {
                   Logger.e(TAG, "onSuccess" + userId);
                    dismissLP();
                    TyslApp.getInstance().setLogin(true);
                    TyslApp.getInstance().setRefreshShopCart(true);
                    int position = getIntent().getIntExtra(Constants.TAB_POSITION,-1);
                    if(-1!=position){
                        TyslApp.getInstance().setTabPosition(position);
                    }
                    //确认回掉
                    setResult(Constants.LOGIN_RESULT_CODE);
                    //String uuid = UUID.randomUUID().toString();
                    Set<String> set = new HashSet<>();
                    //原来        set.add(ManagerTool.getTelephonyManager().getDeviceId());
                    set.add(Settings.System.getString(getContentResolver(), Settings.System.ANDROID_ID));
                    //set.add(DeviceUtil.getDeviceId());
                //    Logger.e("JPushTAG", IMIE);
                    JPushInterface.setTags(LoginActivity.this,1,set);
                //    loadDevice(userId);
                    finish();
                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    dismissLP();
                    RxToast.showShort(Constants.LOGIN_FAILURE_MESSAGE);
                    TyslApp.getInstance().setRefreshShopCart(false);
                }
            });
        }
    }

/*    private void loadDevice(String userId) {
        OkGo.<BaseResponse<DeviceBean>>post(Constants.BASE_URL + Constants.OTHER_IS_SAME_DEVICE_URL)
                .params("user_id",userId)
                .params("uuid", UUID.randomUUID().toString())
                .execute(new JsonCallback<BaseResponse<DeviceBean>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<DeviceBean>> response) {
                        BaseResponse body = response.body();
                        Log.e("登录的设备","登录的设备");
                        if(body == null)return;
                        if (body.isState()) {
                            Log.e("登录的设备1",body+"");
                            DeviceBean data = (DeviceBean) body.getData();
                            Log.e("登录的设备2",data.getIs_same()+"");

                        } else {
                            RxToast.showShort(body.getMsg());
                        }
                    }
                });
    }*/

    private String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    private void initPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //检查权限
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                //请求权限
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
            } else {
                flag = true;
            }
        } else {
            flag = true;
        }
    }

    /**
     * 权限的结果回调函数
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            flag = grantResults[0] == PackageManager.PERMISSION_GRANTED;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);
    }
}