package com.wokun.tysl.goodslinshou.adapter;

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

public class OrderDetailGoodsListAdapter2 extends BaseQuickAdapter<ZhihuiDetaiBean,BaseViewHolder> {

    public OrderDetailGoodsListAdapter2(@LayoutRes int layoutResId, @Nullable List<ZhihuiDetaiBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ZhihuiDetaiBean item) {
        ImageLoader.loadImage(item.getGoods_image(), (ImageView) helper.getView(R.id.goods_picture));
        helper.setText(R.id.goods_name,item.getGoods_name())
        .setText(R.id.exchange_integral,"￥"+item.getGoods_price())
        .setText(R.id.integral_gid,"x"+item.getGoods_num());
    }



}
