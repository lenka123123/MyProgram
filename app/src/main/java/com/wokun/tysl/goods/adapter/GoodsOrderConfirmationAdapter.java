package com.wokun.tysl.goods.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.tysl.R;
import com.wokun.tysl.goods.bean.GoodsBean;
import com.wokun.tysl.utils.ImageLoader;

import java.util.List;

public class GoodsOrderConfirmationAdapter extends BaseQuickAdapter<GoodsBean, BaseViewHolder> {

    public GoodsOrderConfirmationAdapter(@LayoutRes int layoutResId, @Nullable List<GoodsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsBean item) {
        int position = helper.getAdapterPosition();

        RelativeLayout header = helper.getView(R.id.header);
        if (position > 0) {
            if (mData.get(position).getStoreId() == mData.get(position - 1).getStoreId()) {
                header.setVisibility(View.GONE);
            } else {
                header.setVisibility(View.VISIBLE);
            }
        } else {
            header.setVisibility(View.VISIBLE);
        }

        ImageView imageView = helper.getView(R.id.goods_picture);
        ImageLoader.loadImage(item.getGoodsImage(), imageView);
        helper.setText(R.id.goods_name, item.getGoodsName())
        .setText(R.id.goods_pay_price, "￥" + item.getGoodsPrice())
        .setText(R.id.goods_num, "x" + item.getGoodsNum());
    }
}
