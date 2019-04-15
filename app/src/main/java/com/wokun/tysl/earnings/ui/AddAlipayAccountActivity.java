package com.wokun.tysl.earnings.ui;

import android.view.View;
import android.widget.EditText;

import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseTransparencyBindingActivity;
import com.wokun.tysl.earnings.controller.EarningsMgr;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

//添加支付宝账户
public class AddAlipayAccountActivity extends BaseTransparencyBindingActivity {

    @BindString(R.string.tysl_add_alipay_account)
    String title;
    @BindView(R.id.toolbar)
    WidgetBar widgetBar;
    @BindView(R.id.alipay_name)
    EditText alipayName;
    @BindView(R.id.alipay_id)
    EditText alipayId;

    @Override
    public int createView() {
        return R.layout.activity_add_alipay_account;
    }

    @Override
    public WidgetBar createToolBar() {
        return widgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {
        super.init();
    }

    @OnClick(R.id.action_immediately_add)
    public void action(View v) {
        String alipay_name = alipayName.getText().toString().trim();
        String alipay_id = alipayId.getText().toString().trim();
        EarningsMgr.getInstance().addAlipayAccount(this,alipay_name, alipay_id);
    }
}
