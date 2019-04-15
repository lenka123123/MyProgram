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

public class MerchantAccountManagementAdapter2 extends BaseQuickAdapter<OrderInfoBean, BaseViewHolder> {

    public MerchantAccountManagementAdapter2(@LayoutRes int layoutResId, @Nullable List<OrderInfoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderInfoBean item) {
        ImageLoader.loadImage(item.getProductImageUrl(), (ImageView) helper.getView(R.id.iv_product_image));

        helper.setText(R.id.tv_user_name,item.getUserName())
              .setText(R.id.tv_total_price,item.getTotalPrice())
              .setText(R.id.tv_product_name,item.getProductName())
              .setText(R.id.tv_product_price,item.getProductPrice())
              .setText(R.id.tv_product_earnings,item.getProductEarnings());
    }
}