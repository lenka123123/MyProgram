package com.wokun.tysl.stock.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shantoo.common.utils.UIUtil;
import com.wokun.tysl.R;
import com.wokun.tysl.order.bean.MyOrderBean;
import com.wokun.tysl.utils.ImageLoader;


import java.util.List;

public class MyReceiptAdapter extends BaseQuickAdapter<MyOrderBean, BaseViewHolder> {

    public MyReceiptAdapter(@LayoutRes int layoutResId, @Nullable List<MyOrderBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final MyOrderBean item) {
        TextView state = helper.getView(R.id.txtClick);
        TextView txtState = helper.getView(R.id.txtState);


        ImageLoader.loadImage(item.getAvatar(), (ImageView) helper.getView(R.id.goods_picture));

        helper.setText(R.id.txtTime, "下单时间： " + item.getStartTime())
        .setText(R.id.txtState, "代发货")
        .setText(R.id.goods_name, item.getUserName())
        .setText(R.id.exchange_integral2, "x" + item.getOrderAmount())
        .addOnClickListener(R.id.txtClick);

         if ("0".equals(item.getOrderState())) {
            state.setText("查看物流");
            txtState.setText("已发货");
        }else if ("1".equals(item.getOrderState())) {
            state.setText("取消订单");
            txtState.setText("代发货");
        }
    }
}
