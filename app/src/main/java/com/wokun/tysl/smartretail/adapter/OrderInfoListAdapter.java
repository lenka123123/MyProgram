package com.wokun.tysl.smartretail.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.tysl.R;
import com.wokun.tysl.smartretail.bean.OrderInfoListBean;
import com.wokun.tysl.utils.ImageLoader;

import java.util.List;

public class OrderInfoListAdapter extends BaseQuickAdapter<OrderInfoListBean, BaseViewHolder> {

    public OrderInfoListAdapter(@LayoutRes int layoutResId, @Nullable List<OrderInfoListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderInfoListBean item) {
        ImageLoader.loadImage(item.getImage(), (ImageView) helper.getView(R.id.iv_image));
        helper.setText(R.id.tv_goods_name, item.getName())
              .setText(R.id.tv_goods_price, item.getPrice())
              .setText(R.id.tv_purchaser, item.getPurchaser())
              .setText(R.id.tv_mobile, item.getMobile());
    }
}