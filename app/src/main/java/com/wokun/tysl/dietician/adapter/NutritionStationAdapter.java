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

public class NutritionStationAdapter extends BaseQuickAdapter<RecGoodsBean,BaseViewHolder> {

    public NutritionStationAdapter(@LayoutRes int layoutResId, @Nullable List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RecGoodsBean item) {
        ImageLoader.loadImage(item.getGoodsImage(), (ImageView) helper.getView(R.id.goods_image));
        helper.setText(R.id.goods_name,item.getGoodsName())
        .setText(R.id.goods_price,"￥"+item.getGoodsPrice());
    }
}
