package com.wokun.tysl.ucenter.ui;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.login.LoginMgr;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/4/004.
 */

public class NewEditMobileActivity extends BaseBindingActivity {
    @BindString(R.string.tysl_edit_mobile)
    String title;


    @BindView(R.id.et_mobile)
    TextView etMobile;           //手机
    @BindView(R.id.et_yzm)
    EditText etYzm;              //验证码


    @BindView(R.id.action_send_verify_code)
    Button action_send_verify_code;


    private String mobile;
    @Override
    public int createView() {
        return R.layout.activity_ucenter_edit_newmobile;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {
        mobile = TyslApp.getInstance().getUserInfo().getMobile();
        etMobile.setText(mobile);


    }


    /** 下一步*/
    @OnClick(R.id.action_submit)
    public void action_submit(View v){
      if(R.id.action_submit == v.getId()){
          String trim = etYzm.getText().toString().trim();

          if(trim.length()==0){
              RxToast.showShort("验证码为空!");
              return;
          }

          Intent intent = new Intent(NewEditMobileActivity.this, NewEditMobileActivity2.class);
           intent.putExtra("etYzm", etYzm.getText().toString().trim());
            startActivity(intent);
        }
    }




    /** 获取验证码*/
    @OnClick(R.id.action_send_verify_code)
    public void actionSendVerifyCode(View v){
        if(R.id.action_send_verify_code == v.getId()){
            //String mobile = etMobile.getText().toString().trim();
            LoginMgr.getInstance().sendVerifyCode(mobile);
        }
    }
}
