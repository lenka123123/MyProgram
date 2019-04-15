package com.wokun.tysl.login.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.imageview.SelectorImageView;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.base.SimpleWebViewActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.login.LoginMgr;
import com.wokun.tysl.model.bean.SingleParam;
import com.wokun.tysl.model.response.BaseResponse;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

//注册
public class RegisterActivity extends BaseBindingActivity {

    @BindString(R.string.tysl_register)String title;

    @BindView(R.id.et_mobile) EditText etMobile;
    @BindView(R.id.et_password) EditText etPassword;
    @BindView(R.id.et_yzm) EditText etYzm;
    @BindView(R.id.et_invite_code) EditText etInviteCode;
    @BindView(R.id.action_send_verify_code)
    Button actionSendverifycode;
    @BindView(R.id.action_siv_show_pwd) SelectorImageView sivShowPwd;
    private TimeCount timeCount;
    @Override
    public int createView() {
        return R.layout.activity_login_register;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {
        etMobile.setHint("请输入手机号");
        etPassword.setHint("请设置登录密码");
        etYzm.setHint("请输入验证码");
        etInviteCode.setHint("请输入邀请码");
    }

    /**
     * 发送验证码
     * */
    @OnClick(R.id.action_send_verify_code)
    public void actionSendVerifyCode(View view){
        String mobile = etMobile.getText().toString().trim();

                 timeCount = new TimeCount(60000, 1000);
                  LoginMgr.getInstance().sendVerifyCode(mobile);
                  timeCount.start();

    }


    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            actionSendverifycode.setSelected(false);//灰色边框
            actionSendverifycode.setClickable(false);
            actionSendverifycode.setText( millisUntilFinished / 1000 +"秒后重发");
        }

        @Override
        public void onFinish() {
            actionSendverifycode.setSelected(true);//蓝色边框
            actionSendverifycode.setClickable(true);
            actionSendverifycode.setText("重获验证码");
         //   actionSendverifycode.setTextColor(Color.parseColor("#056196"));
            actionSendverifycode.setTextColor(Color.parseColor(String.valueOf(R.color.white)));
        }
    }






    /**
     * 注册
     * */
    @OnClick(R.id.action_register)
    public void actionRegister(View view){
        String phone = etMobile.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String code = etYzm.getText().toString().trim();
        String inviteCode = etInviteCode.getText().toString().trim();
        LoginMgr.getInstance().register(phone, password, code ,inviteCode);
    }

    /**
     * 密码的显示与隐藏
     * */
    @OnClick(R.id.action_siv_show_pwd)
    public void actionSivShowPwd(View view){
        LoginMgr.getInstance().isShowPassword(etPassword,sivShowPwd);
    }

    /**
     * 太宜食聊用户服务协议
     * */
    @OnClick(R.id.action_tysl_xy)
    public void actionTyslXy(View view){
        OkGo.<BaseResponse<SingleParam>>post(Constants.BASE_URL + Constants.XIE_YI_URL)
                .tag(this)
                .execute(new JsonCallback<BaseResponse<SingleParam>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<SingleParam>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        SingleParam data = (SingleParam) body.getData();
                        if(data==null)return;
                        Intent intent = new Intent();
                        intent.putExtra(Constants.TITLE, "太宜食聊用户服务协议");
                        intent.putExtra(Constants.URL, data.getUrl());
                        intent.setClass(RegisterActivity.this, SimpleWebViewActivity.class);
                        startActivity(intent);
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);
    }
}
