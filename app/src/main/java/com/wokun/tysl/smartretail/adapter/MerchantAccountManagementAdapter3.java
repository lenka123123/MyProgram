package com.wokun.tysl.smartretail.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.tysl.R;
import com.wokun.tysl.smartretail.bean.OrderInfoBean;
import com.wokun.tysl.utils.ImageLoader;

import java.util.List;

public class MerchantAccountManagementAdapter3 extends BaseQuickAdapter<OrderInfoBean, BaseViewHolder> {

    public MerchantAccountManagementAdapter3(@LayoutRes int layoutResId, @Nullable List<OrderInfoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderInfoBean item) {

        helper.setText(R.id.zhihui_it_call,item.getUserName())
              .setText(R.id.zhihui_it_title,item.getTotalPrice())
              .setText(R.id.zhihui_it_time,item.getProductName());

    }
}