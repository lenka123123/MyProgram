package com.wokun.tysl.goodslinshou.ui;

import android.support.v4.app.Fragment;

import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseTabActivity;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.goodslinshou.fragment.GoodsOrderlinshouStateAll;
import com.wokun.tysl.goodslinshou.fragment.GoodsOrderlinshouStateTakeDeliveryOf;
import com.wokun.tysl.goodslinshou.fragment.GoodsOrderlinshouStateWaitEvaluate;
import com.wokun.tysl.goodslinshou.fragment.GoodsOrderlinshouStateWaitPay;
import com.wokun.tysl.goodslinshou.fragment.GoodsOrderlinshouStateWaitShipments;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;

//智慧商品订单
public class GoodslinshouOrderActivity extends BaseTabActivity {

    @BindString(R.string.tysl_my_order2)
    String title;
    @BindString(R.string.tysl_state_all)
    String all;
    @BindString(R.string.tysl_state_wait_shipments)
    String wait_shipments;
    @BindString(R.string.tysl_state_wait_for_receiving)
    String take_delivery_of_goods;
    @BindString(R.string.tysl_state_wait_self)
    String state_wait_self;

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
        mTitles.add(state_wait_self);
        mTitles.add(wait_shipments);
        mTitles.add(take_delivery_of_goods);
        mTitles.add(wait_evaluate);
        return mTitles;
    }

    @Override
    protected List<Fragment> initFragments() {
        mFragments = new ArrayList<>();
        mFragments.add(new GoodsOrderlinshouStateAll());//全部
        mFragments.add(new GoodsOrderlinshouStateWaitPay());//智慧商品待自提
        mFragments.add(new GoodsOrderlinshouStateWaitShipments());//智慧商品订单待发货
        mFragments.add(new GoodsOrderlinshouStateTakeDeliveryOf());//智慧商品订单状态待收货
        mFragments.add(new GoodsOrderlinshouStateWaitEvaluate());////智慧商品订单待评价
        return mFragments;
    }
}