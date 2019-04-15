package com.wokun.tysl.ucenter.ui;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.login.LoginMgr;
import com.wokun.tysl.ucenter.controler.UcenterMgr;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/4/004.
 */

public class NewEditMobileActivity2 extends BaseBindingActivity {
    @BindString(R.string.tysl_edit_mobile2)
    String title;

    @BindView(R.id.et_mobile)
    EditText etMobile;           //手机hao
    @BindView(R.id.et_yzm)
    EditText etYzm;
    @BindView(R.id.action_send_verify_code)//验证码
    Button action_send_verify_code;


    @Override
    public int createView() {
        return R.layout.activity_ucenter_edit_newmobile2;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {




    }


    /** 绑定*/
    @OnClick(R.id.action_submit)
    public void actionSubmit(View v) {
    //    String pwd = etPwd.getText().toString().trim();
        String mobile = etMobile.getText().toString().trim();
        String yzm = etYzm.getText().toString().trim();
        UcenterMgr.getInstance().editBindMobile(mobile,yzm);
    }




    /** 获取验证码*/
    @OnClick(R.id.action_send_verify_code)
    public void actionSendVerifyCode(View v){
        if(R.id.action_send_verify_code == v.getId()){
            String mobile = etMobile.getText().toString().trim();
              LoginMgr.getInstance().sendVerifyCode(mobile);
        }
    }
}
