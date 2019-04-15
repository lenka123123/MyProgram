package com.wokun.tysl.goods.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.tysl.R;
import com.wokun.tysl.goods.bean.GoodsBean;
import com.wokun.tysl.goodslinshou.bean.ZhihuiDetaiBean;
import com.wokun.tysl.utils.ImageLoader;

import java.util.List;

public class OrderDetailGoodsListAdapter extends BaseQuickAdapter<GoodsBean,BaseViewHolder> {

    public OrderDetailGoodsListAdapter(@LayoutRes int layoutResId, @Nullable List<GoodsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsBean item) {
        ImageLoader.loadImage(item.getGoodsImage(), (ImageView) helper.getView(R.id.goods_picture));
        helper.setText(R.id.goods_name,item.getGoodsName())
        .setText(R.id.exchange_integral,"¥"+item.getGoodsPayPrice())
        .setText(R.id.integral_gid,"x"+item.getGoodsNum());
    }


}
