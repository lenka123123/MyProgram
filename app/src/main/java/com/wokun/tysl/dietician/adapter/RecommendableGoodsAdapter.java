package com.wokun.tysl.dietician.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.tysl.R;
import com.wokun.tysl.dietician.bean.RecGoodsBean;
import com.wokun.tysl.utils.ImageLoader;

import java.util.List;

public class RecommendableGoodsAdapter extends BaseQuickAdapter<RecGoodsBean, BaseViewHolder> {

    public RecommendableGoodsAdapter(@LayoutRes int layoutResId, @Nullable List<RecGoodsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final RecGoodsBean item) {
        ImageView goods_picture = helper.getView(R.id.goods_picture);
        ImageLoader.loadImage(item.getGoodsImage(), goods_picture);

        helper.setText(R.id.goods_name, item.getGoodsName())
        .setText(R.id.goods_price, "¥"+item.getGoodsPrice())
        .setText(R.id.goods_commission, "¥"+item.getGoodsCommission());
    }
}
