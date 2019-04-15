package com.wokun.tysl.huiyuantotal.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.shantoo.widget.recyclerview.MItemDecoration;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.smartretail.adapter.MerchantAccountManagementAdapter3;
import com.wokun.tysl.smartretail.bean.OrderInfoBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;

/**
 * Created by Administrator on 2018/7/25/025.
 */

public class TixianTotalActivity extends BaseBindingActivity{
    @BindString(R.string.tysl_user_tixian)String title;
    @BindView(R.id.recyclerView)RecyclerView mRecyclerView;
    @Override
    public int createView() {
        return R.layout.activity_merchant_account_huiyuan;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {

        List<OrderInfoBean> list = new ArrayList<>();
        OrderInfoBean bean6 = new OrderInfoBean();
        bean6.setUserName("不曾逗留");
        bean6.setTotalPrice("147.00");
        bean6.setProductName("中国银行");
        bean6.setProductPrice("1553x1");
        bean6.setProductEarnings("14.54");
        OrderInfoBean bean7 = new OrderInfoBean();
        bean7.setUserName("不曾留2 ");
        bean7.setTotalPrice("147.00");
        bean7.setProductName("建设银行");
        bean7.setProductPrice("1553x12");
        bean7.setProductEarnings("122.12");
        OrderInfoBean bean8 = new OrderInfoBean();
        bean8.setUserName("曾逗留");
        bean8.setTotalPrice("147.00");
        bean8.setProductName("招商银行");
        bean8.setProductPrice("1553x1");
        bean8.setProductEarnings("23.54");
        list.add(bean6);
        list.add(bean7);
        list.add(bean8);


        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.addItemDecoration(new MItemDecoration(this));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new MerchantAccountManagementAdapter3(R.layout.item_zhihui_tixian,list));






    }
}
