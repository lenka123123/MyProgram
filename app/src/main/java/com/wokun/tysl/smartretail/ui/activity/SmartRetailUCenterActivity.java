package com.wokun.tysl.smartretail.ui.activity;

import android.view.View;
import android.widget.TextView;

import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseBindingActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 智慧零售-个人中心
 */

public class SmartRetailUCenterActivity extends BaseBindingActivity {

    @BindView(R.id.tv1)TextView tv1;
    @BindView(R.id.tv2)TextView tv2;

    @Override
    public WidgetBar createToolBar() {
        mWidgetBar.setVisibility(View.GONE);
        return null;
    }

    @Override
    public int createView() {
        return R.layout.activity_smart_retail_ucenter;
    }

    @Override
    public void init() {
        tv1.setText("我的订单");
        tv2.setText("收货地址");
    }

    @OnClick(R.id.action_order)
    public void actionOrder(View view){
        RxToast.showShort("我的订单");
    }

    @OnClick(R.id.action_address)
    public void actionAddress(View view){
        RxToast.showShort("收货地址");
    }
}