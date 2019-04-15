package com.wokun.tysl.goods.adapter;

import android.app.ActionBar;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.tysl.R;
import com.wokun.tysl.goods.bean.GoodsListBean;
import com.wokun.tysl.utils.ImageLoader;

import java.util.List;

public class GoodsListAdapter extends BaseQuickAdapter<GoodsListBean, BaseViewHolder> {

    public GoodsListAdapter(@LayoutRes int layoutResId, @Nullable List<GoodsListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsListBean item) {

        if (getData().indexOf(item) % 2 == 0) {
            helper.getView(R.id.left_linearlayout).setVisibility(View.VISIBLE);
            helper.getView(R.id.right_linearlayout).setVisibility(View.GONE);
            helper.getView(R.id.left_linearlayout_item).setVisibility(View.GONE);
        } else {
            helper.getView(R.id.left_linearlayout).setVisibility(View.GONE);
            helper.getView(R.id.right_linearlayout).setVisibility(View.VISIBLE);
            helper.getView(R.id.left_linearlayout_item).setVisibility(View.VISIBLE);
        }

        ImageLoader.loadImage(item.getGoodsImage(), (ImageView) helper.getView(R.id.iv_goods_image));
        helper.setText(R.id.tv_goods_name, item.getGoodsName())
                .setText(R.id.tv_goods_price, "￥" + item.getGoodsPrice())
                .setText(R.id.tv_month_sales_num, "已售 " + item.getMonthSalesNum());
    }
}