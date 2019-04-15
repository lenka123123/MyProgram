package com.wokun.tysl.login.ui;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.login.LoginMgr;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

//忘记密码
public class AlterPwdActivity extends BaseBindingActivity {

    @BindString(R.string.tysl_alter_password)String title;

    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.et_mobile)
    EditText etMobile;
    @BindView(R.id.et_yzm)
    EditText etYzm;
    private TimeCount timeCount;
    @BindView(R.id.action_send_verify_code)
    Button actionSendverifycode;


    @Override
    public int createView() {
        return R.layout.activity_login_alter_password;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {
        etPwd.setHint("新密码（6-20位字母，数字或符号）");
        etMobile.setHint("请输入手机号");
        etYzm.setHint("请输入收到的手机验证码");
    }

    /** 修改密码*/
    @OnClick(R.id.action_submit)
    public void action_submit(View v){
        if(R.id.action_submit == v.getId()){
            String pwd = etPwd.getText().toString().trim();
            String mobile = etMobile.getText().toString().trim();
            String yzm = etYzm.getText().toString().trim();
            LoginMgr.getInstance().alterPassword(this,mobile,pwd,yzm);
        }
    }

    /** 获取验证码*/
    @OnClick(R.id.action_send_verify_code)
    public void actionSendVerifyCode(View v){
        if(R.id.action_send_verify_code == v.getId()){
            String mobile = etMobile.getText().toString().trim();

            timeCount = new TimeCount(60000, 1000);
            LoginMgr.getInstance().sendVerifyCode(mobile);
            timeCount.start();
        }
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





}
