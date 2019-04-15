package com.wokun.tysl.home.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.tysl.R;
import com.wokun.tysl.home.bean.StoreTypeBean;
import com.wokun.tysl.utils.ImageLoader;

import java.util.List;

//首页营养师推荐
public class RecommendAdapter extends BaseQuickAdapter<StoreTypeBean, BaseViewHolder> {

    public RecommendAdapter(@LayoutRes int layoutResId, @Nullable List<StoreTypeBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StoreTypeBean item) {
        ImageView imageView = helper.getView(R.id.recommend_picture);
        ImageLoader.loadImage(item.getPicture(), imageView);
    }
}
