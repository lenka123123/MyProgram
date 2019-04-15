package com.wokun.tysl.smartretail.ui.activity;

import android.widget.EditText;

import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseBindingActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 新增提现账号
 */

public class AccountNumberActivity extends BaseBindingActivity {

    @BindView(R.id.et_name)EditText etName;
    @BindView(R.id.et_id_cart)EditText etIdCart;

    @Override
    public int createView() {
        return R.layout.activity_account_number;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle("新增提现账号");
    }

    @Override
    public void init() {

    }

    /** 提交*/
    @OnClick(R.id.action_commit)
    public void actionCommit(){

    }

    /** 设为默认银行*/
    @OnClick(R.id.action_switch_button)
    public void actionSwitchButton(){

    }
}
