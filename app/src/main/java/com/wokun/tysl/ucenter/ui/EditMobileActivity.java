package com.wokun.tysl.ucenter.ui;

import android.view.View;
import android.widget.EditText;

import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.login.LoginMgr;
import com.wokun.tysl.ucenter.controler.UcenterMgr;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

//修改手机
public class EditMobileActivity extends BaseBindingActivity {
    @BindString(R.string.tysl_edit_mobile)
    String title;

    @BindView(R.id.et_pwd)
    EditText etPwd;              //密码
    @BindView(R.id.et_mobile)
    EditText etMobile;           //手机
    @BindView(R.id.et_yzm)
    EditText etYzm;              //验证码

    @Override
    public int createView() {
        return R.layout.activity_ucenter_edit_mobile;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {
        etPwd.setHint("请输入密码");
        etMobile.setHint("请输入手机号");
        etYzm.setHint("请输入验证码");
    }

    @OnClick(R.id.action_submit)
    public void actionSubmit(View v) {
        String pwd = etPwd.getText().toString().trim();
        String mobile = etMobile.getText().toString().trim();
        String yzm = etYzm.getText().toString().trim();
    //    UcenterMgr.getInstance().editBindMobile(pwd,mobile,yzm);
    }

    @OnClick(R.id.action_send_verify_code)
    public void actionSendVerifyCode(View v) {
        String mobile = etMobile.getText().toString().trim();
        LoginMgr.getInstance().sendVerifyCode(mobile);
    }
}
