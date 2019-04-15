package com.wokun.tysl.home.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.tysl.R;
import com.wokun.tysl.home.bean.ArticleTypeBean;
import com.wokun.tysl.utils.ImageLoader;

import java.util.List;

public class XueTangAdapter extends BaseQuickAdapter<ArticleTypeBean,BaseViewHolder> {

    public XueTangAdapter(@LayoutRes int layoutResId, @Nullable List<ArticleTypeBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleTypeBean item) {
        ImageView imageView = helper.getView(R.id.recommend_picture);
        ImageLoader.loadImage(item.getPicture(), imageView);
    }
}
