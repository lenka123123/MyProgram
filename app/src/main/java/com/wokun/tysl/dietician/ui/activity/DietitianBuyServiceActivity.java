package com.wokun.tysl.dietician.ui.activity;

import android.view.View;
import android.widget.TextView;

import com.shantoo.widget.imageview.SelectorImageView;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.utils.PayUtil;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

public class DietitianBuyServiceActivity extends BaseBindingActivity {

    @BindString(R.string.tysl_buy_service)
    String title;

    @BindView(R.id.alipay_selector)
    SelectorImageView alipaySelectorImageView;
    @BindView(R.id.weixing_selector)
    SelectorImageView weixingSelectorImageView;

    @BindView(R.id.order_number)
    TextView orderNumber;
    @BindView(R.id.service_price)
    TextView servicePrice;
    @BindView(R.id.service_time)
    TextView serviceTime;
    @BindView(R.id.service_total_price)
    TextView serviceTotalPrice;
    @BindView(R.id.true_price)
    TextView trueServicePrice;

    private String pay_type = Constants.ALIPAY;
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    @Override
    public int createView() {
        return R.layout.activity_buy_service;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {
        orderNumber.setText(getIntent().getStringExtra(Constants.ORDER_NUMBER));
        servicePrice.setText("¥" + getIntent().getStringExtra(Constants.SERVICE_PRICE) + "/30天");
        serviceTime.setText(""+getIntent().getStringExtra(Constants.SERVICE_TIME) + "天");
        serviceTotalPrice.setText("¥" + getIntent().getStringExtra(Constants.SERVICE_TOTAL_PRICE)+"");
        trueServicePrice.setText("¥" + getIntent().getStringExtra(Constants.SERVICE_TOTAL_PRICE)+"");
    }

    @OnClick({R.id.action_alipay, R.id.action_wxpay, R.id.action_buy_service})
    public void action(View v) {
        if(R.id.action_alipay == v.getId()){//支付宝支付
            alipaySelectorImageView.toggle(true);
            weixingSelectorImageView.toggle(false);
            pay_type = Constants.ALIPAY;
        }else if(R.id.action_wxpay == v.getId()){//微信支付
            alipaySelectorImageView.toggle(false);
            weixingSelectorImageView.toggle(true);
            pay_type = Constants.WXPAY;
        }else if(R.id.action_buy_service == v.getId()){//确认支付
            if (Constants.ALIPAY.equals(pay_type)) {

                PayUtil.alipay(this,getIntent().getStringExtra(Constants.ORDER_NUMBER), pay_type);
            } else if (Constants.WXPAY.equals(pay_type)) {

                PayUtil.wxpay(this,getIntent().getStringExtra(Constants.ORDER_NUMBER), pay_type);
            }
        }
    }
}