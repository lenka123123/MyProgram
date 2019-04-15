package com.wokun.tysl.goods.ui.activity;

import android.support.v4.app.Fragment;

import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseTabActivity;
import com.wokun.tysl.base.BaseTabActivity1;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.goods.ui.fragment.GoodsOrderStateAll;
import com.wokun.tysl.goods.ui.fragment.GoodsOrderStateTakeDeliveryOf;
import com.wokun.tysl.goods.ui.fragment.GoodsOrderStateWaitEvaluate;
import com.wokun.tysl.goods.ui.fragment.GoodsOrderStateWaitPay;
import com.wokun.tysl.goods.ui.fragment.GoodsOrderStateWaitShipments;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;

//商品订单
public class GoodsOrderActivity extends BaseTabActivity {

    @BindString(R.string.tysl_my_order)
    String title;
    @BindString(R.string.tysl_state_all)
    String all;
    @BindString(R.string.tysl_state_wait_pay)
    String wait_pay;
    @BindString(R.string.tysl_state_wait_shipments)
    String wait_shipments;
    @BindString(R.string.tysl_state_wait_for_receiving)
    String take_delivery_of_goods;
    @BindString(R.string.tysl_state_wait_evaluate)
    String wait_evaluate;


    @Override
    public void init() {
        super.init();
    }

    @Override
    protected String initTitle() {
        return title;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(title);
    }


    @Override
    protected int initState() {
        return getIntent().getIntExtra(Constants.STATE, 0);
    }

    @Override
    protected List<String> initTabTitles() {
        mTitles = new ArrayList<>();
        mTitles.add(all);
        mTitles.add(wait_pay);
        mTitles.add(wait_shipments);
        mTitles.add(take_delivery_of_goods);
        mTitles.add(wait_evaluate);
        return mTitles;
    }

    @Override
    protected List<Fragment> initFragments() {
        mFragments = new ArrayList<>();
        mFragments.add(new GoodsOrderStateAll());
        mFragments.add(new GoodsOrderStateWaitPay());
        mFragments.add(new GoodsOrderStateWaitShipments());
        mFragments.add(new GoodsOrderStateTakeDeliveryOf());
        mFragments.add(new GoodsOrderStateWaitEvaluate());
        return mFragments;
    }
}