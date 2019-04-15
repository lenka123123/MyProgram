package com.wokun.tysl.earnings.ui;

import android.view.View;

import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseBindingActivity;

import butterknife.BindString;
import butterknife.OnClick;

//选择账户类型
public class SelectAccountTypeActivity extends BaseBindingActivity {

    @BindString(R.string.tysl_select_account_type) String title;

    @Override
    public int createView() {
        return R.layout.activity_select_account_type;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {

    }

    @OnClick({R.id.action_alipay, R.id.action_deposit_card})
    public void action(View v) {
        if (R.id.action_alipay == v.getId()) {//添加支付宝账户
            startActivity(AddAlipayAccountActivity.class);
        } else if (R.id.action_deposit_card == v.getId()) {//添加储蓄卡账户
            startActivity(AddDepositCardActivity.class);
        }
    }
}
