package com.wokun.tysl.article.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.tysl.R;
import com.wokun.tysl.article.bean.ArticleFavoritesBean;
import com.wokun.tysl.utils.ImageLoader;

import java.util.List;

//文章收藏数据适配器
public class ArticleFavoritesAdapter extends BaseQuickAdapter<ArticleFavoritesBean, BaseViewHolder> {

    public ArticleFavoritesAdapter(@LayoutRes int layoutResId, @Nullable List<ArticleFavoritesBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleFavoritesBean item) {
        ImageView imageView = helper.getView(R.id.article_image);
        ImageLoader.loadImage(item.getArticleImage(), imageView);
        helper.setText(R.id.title, item.getTitle())
        .setText(R.id.truename, item.getTrueName())
        .setText(R.id.click_num, item.getClickNum());
    }
}
