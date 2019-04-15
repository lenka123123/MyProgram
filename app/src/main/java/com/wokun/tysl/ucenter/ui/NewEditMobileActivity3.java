package com.wokun.tysl.ucenter.ui;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.login.LoginMgr;
import com.wokun.tysl.ucenter.controler.UcenterMgr;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/4/004.
 */

public class NewEditMobileActivity3 extends BaseBindingActivity {
    @BindString(R.string.tysl_edit_mobile3)
    String title;



    @Override
    public int createView() {
        return R.layout.activity_ucenter_edit_newmobilefinish;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {


    }

    /** 完成*/
    @OnClick(R.id.action_submit)
    public void actionSubmit(View v) {
        Intent intent = new Intent(NewEditMobileActivity3.this, SettingsActivity.class);
        startActivity(intent);
    }





}
