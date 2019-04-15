package com.wokun.tysl.article.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shantoo.widget.textview.LetterSpacingTextView;
import com.wokun.tysl.R;
import com.wokun.tysl.article.bean.ArticleBean;
import com.wokun.tysl.utils.ImageLoader;

import java.util.List;

public class ArticleListAdapter extends BaseQuickAdapter<ArticleBean, BaseViewHolder> {

    public ArticleListAdapter(@LayoutRes int layoutResId, @Nullable List<ArticleBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleBean item) {
        ImageLoader.loadImage(item.getArticleImage(), (ImageView) helper.getView(R.id.article_image));
        LetterSpacingTextView des = helper.getView(R.id.des);
        des.setSpacing(1.2f);
        helper.setText(R.id.title, item.getTitle())
              .setText(R.id.des, item.getDes());
    }
}
