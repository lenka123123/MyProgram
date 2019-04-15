package com.wokun.tysl.smartretail.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.shantoo.widget.recyclerview.MItemDecoration;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.smartretail.adapter.OrderInfoListAdapter;
import com.wokun.tysl.smartretail.bean.OrderInfoListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 智慧零售-商家收益
 */

public class SmartRetailMerchantEarningsActivity extends BaseBindingActivity {

    @BindView(R.id.tv2)TextView tv2;                         //可提现金额
    @BindView(R.id.tv4)TextView tv4;                         //今日累计额
    @BindView(R.id.tv6)TextView tv6;                         //会员人数
    @BindView(R.id.tv8)TextView tv8;                         //累计收益

    @BindView(R.id.recycler_view)RecyclerView mRecyclerView; //订单信息

    @Override
    public int createView() {
        return R.layout.activity_smart_retail_merchant_earnings;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar
                .setWidgetBarTitle("商家收益")
                .setMenu("会员管理","")
                .setOnMenuClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //startActivity();
                    }
                },null);
    }

    @Override
    public void init() {
        tv2.setText("¥10000");
        tv4.setText("¥10000");
        tv6.setText("10000");
        tv8.setText("¥10000");

        List<OrderInfoListBean> list = new ArrayList<>();
        OrderInfoListBean bean1 = new OrderInfoListBean();
        bean1.setName("商品名称: 1111");
        bean1.setPrice("单价: ¥1432423");
        bean1.setPurchaser("购买人: shantoo");
        bean1.setMobile("电话: 13534534534");

        OrderInfoListBean bean2 = new OrderInfoListBean();
        bean2.setName("商品名称: 22222");
        bean2.setPrice("单价: ¥4457456456");
        bean2.setPurchaser("购买人: shantoo");
        bean2.setMobile("电话: 135345456534534");
        list.add(bean1);
        list.add(bean2);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new OrderInfoListAdapter(R.layout.item_smart_retail_order_info,list));
        mRecyclerView.addItemDecoration(new MItemDecoration(this));
    }

    @OnClick(R.id.action_withdraw_deposit)
    public void actionWithdrawDeposit() {
        RxToast.showShort("提现");
    }

    @OnClick(R.id.action_more)
    public void actionMore() {
        RxToast.showShort("更多");
    }
}