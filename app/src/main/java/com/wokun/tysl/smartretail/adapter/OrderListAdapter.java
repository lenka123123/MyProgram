package com.wokun.tysl.smartretail.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.tysl.R;
import com.wokun.tysl.smartretail.bean.OrderListBean;

import java.util.List;

public class OrderListAdapter extends BaseQuickAdapter<OrderListBean, BaseViewHolder> {

    public OrderListAdapter(@LayoutRes int layoutResId, @Nullable List<OrderListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderListBean item) {
        helper.setText(R.id.tv_time, item.getTime())
              .setText(R.id.tv_name, item.getName())
              .setText(R.id.tv_state, item.getState())
              .setText(R.id.tv_price, item.getPrice())
              .addOnClickListener(R.id.action_check_the_logistics);
    }
}